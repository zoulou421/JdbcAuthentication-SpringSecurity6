package com.formationkilo.jdbcauthenticationspringsecurity6.service;

import com.formationkilo.jdbcauthenticationspringsecurity6.dao.EmployeeRepository;
import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Optional<EmployeeDTO> optional=employeeRepository.findById(id);
        EmployeeDTO employeeDTO=null;
        if (optional.isPresent()){
            employeeDTO=optional.get();
        }else{
            throw new RuntimeException("Employee not found for id::"+id);
        }
        return employeeDTO;
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }
}
