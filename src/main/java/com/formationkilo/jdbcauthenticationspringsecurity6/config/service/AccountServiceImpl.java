package com.formationkilo.jdbcauthenticationspringsecurity6.config.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppRole;
import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppUser;
import com.formationkilo.jdbcauthenticationspringsecurity6.config.repo.AppRoleRepository;
import com.formationkilo.jdbcauthenticationspringsecurity6.config.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
       // AppUser appUser=appUserService.findByUsername(username);
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser!=null)throw new  RuntimeException("This User already exists");
        if(!password.equals(confirmPassword))throw new  RuntimeException("Password not match");
        appUser =AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser saveAppUser= appUserRepository.save(appUser);
        return saveAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if(appRole!=null) throw new RuntimeException("This Role already exists");
         appRole= AppRole.builder()
                 .role(role)
                 .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
      AppUser appUser = appUserRepository.findByUsername(username);
      AppRole appRole =appRoleRepository.findById(role).get();
      appUser.getRoles().add(appRole);
     // appUserRepository.save(appUser); explicitly, but no need, because it is TRANSACTIONNAL//Committed done automatically

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole =appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
