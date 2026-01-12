package com.project.hems.auth_service_hems.service;

import com.project.hems.auth_service_hems.model.User;
import com.project.hems.auth_service_hems.model.UserIdentitie;
import com.project.hems.auth_service_hems.repository.UserIdentitieRepo;
import com.project.hems.auth_service_hems.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
 //   @Autowired
//    private RedisTemplate<String,String> redisTemplate;//to talk with redis

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserIdentitieRepo userIdentitieRepo;
//
//    public User loginOrRegister(User user,String userSub){
//        //first we find if same user hase database ma with same method so entry nai pade
////        User savedUser=userRepo.findByProviderSub(user.getProviderSub()).
////                map(existingUser ->{
////                    existingUser.setTime(LocalDateTime.now());
////                    return userRepo.save(existingUser);
////                })
////                .orElseGet(() -> {
////                    //new user → save fresh
////                    user.setTime(LocalDateTime.now());
////                    return userRepo.save(user);
////                });
//
//        Optional<UserIdentitie> identity=userIdentitieRepo.findByProviderSub(userSub);
//        if(identity.isPresent()) {
//            User savedUser = identity.get().getUser();
//            user.setLastLogin(LocalDateTime.now());
//            userRepo.save(user);
//            return user;
//        }else{
//
//           User userOpt=userRepo.findByEmail(user.getEmail()).get();
//           userIdentitieRepo.save(new UserIdentitie(userOpt,user.))
//
//
//
//        }
//
//
//
//
//
//   //     ValueOperations<String,String> valueOps=redisTemplate.opsForValue();
//    //    valueOps.set("user:"+user.getUserId(),savedUser.toString(),100, TimeUnit.SECONDS);
//        return savedUser;
//    }
//
//
//    //get user so firat we try in redis if not found then it is called miss then we go to database ..
//    //and if user found in redis then it called hit so apde database jode nai jaisu


        @Transactional
        public User loginOrRegister(String email,String subject) {
            //first we check in user identit table

            String[] parts = subject.split("\\|");
            String provider=parts[0];
            String provider_user_id=parts[1];

            Optional<UserIdentitie> identity=userIdentitieRepo.findByProviderSub(subject);
            if(identity.isPresent()){
                //if present hoy toh only last login change karsu
                User user=identity.get().getUser();
                user.setLastLogin(LocalDateTime.now());
               return userRepo.save(user);
            }

            //user identity table ma present nai hoy it means e login haji first time thayo che e method thi


            //first user table mathi email vade find karsu .
            Random random=new Random();
            long user_id=random.nextLong();
            long bound_id=Math.abs(user_id%10000);//bound to 4 digit


            Optional<User> userOpt=userRepo.findByEmail(email);
            User user;
            if(userOpt.isPresent()){
                user=userOpt.get();
            }else{
                user=User.builder()
                        .email(email)
                        .lastLogin(LocalDateTime.now())
                        .userId(bound_id)
                        .build();
                user=userRepo.save(user);
            }

            UserIdentitie userIdentitie=UserIdentitie.builder()
                    .created_time(LocalDateTime.now())
                    .providerSub(subject)
                    .provider(parts[0])
                    .user(user)
                    .build();
            userIdentitieRepo.save(userIdentitie);
            return user;
        }




}
