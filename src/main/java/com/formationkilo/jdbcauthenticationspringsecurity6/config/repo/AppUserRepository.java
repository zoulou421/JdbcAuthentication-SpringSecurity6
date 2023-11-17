package com.formationkilo.jdbcauthenticationspringsecurity6.config.repo;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
