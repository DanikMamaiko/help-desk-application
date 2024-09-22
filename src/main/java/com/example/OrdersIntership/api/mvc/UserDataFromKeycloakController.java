package com.example.OrdersIntership.api.mvc;

import com.example.OrdersIntership.dto.UserDto;
import com.example.OrdersIntership.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserDataFromKeycloakController {

    final UserServiceImpl userService;

    @GetMapping("/userInfo")
    public String getUserInfo(){

        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String email = "";

        OidcIdToken token = user.getIdToken();

        Map<String, Object> customClaims = token.getClaims();

        if(customClaims.containsKey("email")){
            email = String.valueOf(customClaims.get("email"));
        }

        String response = "Username: " + user.getName() + " // Email: " + email;

        UserDto userDto = userService.getUserByEmail(email);

        return userDto.toString();

    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }

    @GetMapping("/getDanik")
    public String getDanik(){

        UserDto userDto = userService.getUserByEmail("danikmamaiko@gmail.com");

        return userDto.toString();

    }


}
