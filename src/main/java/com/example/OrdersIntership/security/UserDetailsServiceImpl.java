//package com.example.OrdersIntership.security;
//
//import com.example.OrdersIntership.mappers.UserMapper;
//import com.example.OrdersIntership.services.UserServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private final UserServiceImpl userService;
//
//    @Autowired
//    private final UserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        return Optional.of(userService.getUserByEmail(username))
//                .map(user -> new User(
//                        user.getEmail(),
//                        user.getPassword(),
//                        Collections.singleton(user.getRole())
//                ))
//                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
//
//        // Other way
////        Optional <UserDto> user = userService.getUserByEmail(username);
////        if(user.isEmpty()){
////            throw new UsernameNotFoundException("User not found: " + username);
////        }
////        return new UserDetailsImpl(user.map(userMapper::toUser).get());
//
//    }
//}
