package com.formationkilo.jdbcauthenticationspringsecurity6.dao;

import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO,Long> {

}
