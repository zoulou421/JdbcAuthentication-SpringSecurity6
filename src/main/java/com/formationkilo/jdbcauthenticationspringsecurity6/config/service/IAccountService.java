package com.formationkilo.jdbcauthenticationspringsecurity6.config.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppRole;
import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppUser;


public interface IAccountService {
    AppUser addNewUser(String username,String password, String email,String confirmPassword);
    AppRole addNewRole(String role);

    void addRoleToUser(String  username, String role);

    void removeRoleFromUser(String username, String role);

    AppUser loadUserByUsername(String username);


}
