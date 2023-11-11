package com.formationkilo.jdbcauthenticationspringsecurity6.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDTO> getAllEmployees();
    void saveEmployee(EmployeeDTO employeeDTO);
}
