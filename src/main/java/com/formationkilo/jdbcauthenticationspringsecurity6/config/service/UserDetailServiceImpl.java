package com.formationkilo.jdbcauthenticationspringsecurity6.config.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements IUserDetailService { //UserDetailsService
    private IAccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= accountService.loadUserByUsername(username);
        if(appUser==null) throw new  UsernameNotFoundException(String.format("User %s not found",username));
        String []roles=appUser.getRoles().stream().map(r->r.getRole()).toArray(String[]::new);
        UserDetails userDetails= User
                .withUsername(username)
                .password(appUser.getPassword())
                .roles(roles)
                .build();
       // return null;
        return userDetails;//

    }
}
