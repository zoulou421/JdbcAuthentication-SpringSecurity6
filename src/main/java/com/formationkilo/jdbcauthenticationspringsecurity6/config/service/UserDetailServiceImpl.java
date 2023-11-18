package com.formationkilo.jdbcauthenticationspringsecurity6.config.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements IUserDetailService { //UserDetailsService
    private IAccountService accountService;


   /* it works for ROLES
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

    }*/
    //it works for AUTHORITIES
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser appUser= accountService.loadUserByUsername(username);
       if(appUser==null) throw new  UsernameNotFoundException(String.format("User %s not found",username));
      // String []roles=appUser.getRoles().stream().map(r->r.getRole()).toArray(String[]::new);
       // list a type of GrantedAuthority
       List<SimpleGrantedAuthority>authorities= appUser.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getRole()))
               .collect(Collectors.toList());

       UserDetails userDetails= User
               .withUsername(username)
               .password(appUser.getPassword())
               .authorities(authorities)
             //  .roles(roles)
               .build();
       // return null;
       return userDetails;//

   }

}
