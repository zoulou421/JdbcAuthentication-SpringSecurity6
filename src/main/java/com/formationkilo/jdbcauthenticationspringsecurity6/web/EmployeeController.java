package com.formationkilo.jdbcauthenticationspringsecurity6.web;

import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import com.formationkilo.jdbcauthenticationspringsecurity6.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;
    @RequestMapping("/")
    public String homePage(Model model){
       model.addAttribute("list_employees",employeeService.getAllEmployees());
       return "index";
    }

    @RequestMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        EmployeeDTO employeeDTO=new EmployeeDTO();
        model.addAttribute("employee", employeeDTO);
        return "new_employee";
    }
}
