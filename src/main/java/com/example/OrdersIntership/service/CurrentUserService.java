//package com.example.OrdersIntership.services;
//
//import com.example.OrdersIntership.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.core.oidc.OidcIdToken;
//import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class CurrentUserService {
//
//    final UserServiceImpl userService;
//
//    public UserDto getCurrentUser(){
//
//        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
//                            .getAuthentication()
//                            .getPrincipal();
//
//        OidcIdToken token = user.getIdToken();
//
//        String email = "";
//
//        Map<String, Object> customClaims = token.getClaims();
//
//        if(customClaims.containsKey("email")){
//            email = String.valueOf(customClaims.get("email"));
//        }
//
//        UserDto userDto = userService.getUserByEmail(email);
//
//        return userDto;
//
//    }
//
//}
