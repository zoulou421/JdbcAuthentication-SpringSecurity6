package com.formationkilo.jdbcauthenticationspringsecurity6.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.dao.EmployeeRepository;
import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements IEmployeeService{
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
   // @Autowired
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeDTO> getAllEmployees() {
            return employeeRepository.findAll();

    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeDTO);
    }
}
