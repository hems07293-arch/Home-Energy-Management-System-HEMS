package com.project.hems.auth_service_hems.service;

import com.project.hems.auth_service_hems.model.User;
import com.project.hems.auth_service_hems.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
 //   @Autowired
//    private RedisTemplate<String,String> redisTemplate;//to talk with redis

    @Autowired
    private UserRepo userRepo;

    public User save(User user){
        //first we find if same user hase database ma with same method so entry nai pade
        User savedUser=userRepo.findByProviderSub(user.getProviderSub()).
                map(existingUser ->{
                    existingUser.setTime(LocalDateTime.now());
                    return userRepo.save(existingUser);
                })
                .orElseGet(() -> {
                    //new user → save fresh
                    user.setTime(LocalDateTime.now());
                    return userRepo.save(user);
                });
   //     ValueOperations<String,String> valueOps=redisTemplate.opsForValue();
    //    valueOps.set("user:"+user.getUserId(),savedUser.toString(),100, TimeUnit.SECONDS);
        return savedUser;
    }


    //get user so firat we try in redis if not found then it is called miss then we go to database ..
    //and if user found in redis then it called hit so apde database jode nai jaisu

}
