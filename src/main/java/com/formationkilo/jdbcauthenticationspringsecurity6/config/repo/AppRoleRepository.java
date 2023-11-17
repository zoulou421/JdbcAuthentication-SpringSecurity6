package com.formationkilo.jdbcauthenticationspringsecurity6.config.repo;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {

}
