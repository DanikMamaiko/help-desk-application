//package com.example.OrdersIntership.controllers.mvc;
//
//import com.example.OrdersIntership.dto.UserDto;
//import com.example.OrdersIntership.entities.User;
//import com.example.OrdersIntership.mappers.UserMapper;
//import com.example.OrdersIntership.security.UserDetailsImpl;
//import com.example.OrdersIntership.services.implementation.UserServiceImpl;
//import com.example.OrdersIntership.validator.UserValidator;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
//
//
//@Controller
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
//
//    @Autowired
//    private final UserServiceImpl userService;
//
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    private final UserValidator userValidator;
//
//    @Autowired
//    private final UserMapper userMapper;
//
//    @GetMapping("/login")
//    public String loginPage(@ModelAttribute("userDto") UserDto userDto) {
//
//        return "login";
//
//    }
//
//    @PostMapping("/login-process")
//    public String processLogin(HttpServletRequest req,
//                               @Valid @ModelAttribute("userDto") UserDto userDto,
//                               BindingResult bindingResult) {
//
//        logger.info("Processing login...");
//
//        userValidator.validate(userDto, bindingResult);
//
//         //Parse validation Errors
//         if (bindingResult.hasErrors()) {
//             logger.info("With Binding Errors");
//             return "login";
//         }
//
//        logger.info("Without Binding Errors");
//        logger.info("Attempting login with email: {}", userDto.getEmail());
//
//
//        if (userService.userIsExists(userDto.getEmail(), userDto.getPassword())) {
//
//            Optional<UserDto> userFromDb = userService.getUserByEmail(userDto.getEmail());
//            User userFromDbCust = userFromDb.map(userMapper::toUser).get();
//
//            UserDetailsImpl userDetails = new UserDetailsImpl(userFromDbCust);
//
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//
//            logger.info("Email from DB: {}", userFromDbCust.getEmail());
//            logger.info("Password from DB: {}", userFromDbCust.getPassword());
//
//            try {
//                authenticationManager.authenticate(authenticationToken);
//
//                SecurityContext sc = SecurityContextHolder.getContext();
//                sc.setAuthentication(authenticationToken);
//                HttpSession session = req.getSession(true);
//                session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
//
//                logger.info("Login successful for user: {}", userDto.getEmail());
//                return "redirect:/allTickets";
//            } catch (AuthenticationException e) {
//                logger.info("Login Unsuccessful for user: {}", userDto.getEmail());
//                return "redirect:/login?error";
//            }
//
//        } else {
//            logger.info("ERROR IN CREATING AUTH TOKEN");
//        }
//
//        return "redirect:/login?error";
//    }
//
//}