package com.hems.project.Virtual_Power_Plant.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignAuthInterceptor implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null){
            String token=attributes.getRequest().getHeader("Authorization");
            if(token!=null && token.startsWith("Bearer ")){
                template.header("Authorization", token);
                System.out.println("in request access token is attached ");
                System.out.println("token"+token);
            }else{
                System.out.println("no token is avaliable for this request");
            }
        }
    }


    
    
    
}
