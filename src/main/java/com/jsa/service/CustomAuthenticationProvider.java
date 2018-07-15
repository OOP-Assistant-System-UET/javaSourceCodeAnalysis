//package com.jsa.service;
//
//import com.jsa.model.UserAccount;
//import com.jsa.repository.UserAccRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserAccRepository userAccRepository;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name  = authentication.getName();
//        Object credentials = authentication.getCredentials();
//        if (!(credentials instanceof String)) {
//            return null;
//        }
//        String password = credentials.toString();
//        UserAccount userAccount = userAccRepository.findByUserName(name);
//
//        if(!password.equals(userAccount.getPassword())){
//            throw new BadCredentialsException("Authentication failed for " + name);
//        }
//
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(userAccount.getRole()));
//
//        Authentication auth  = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
//        return auth;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
