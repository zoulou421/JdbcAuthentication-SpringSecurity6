package com.formationkilo.jdbcauthenticationspringsecurity6.web;

import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import com.formationkilo.jdbcauthenticationspringsecurity6.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("employeeDTO", employeeDTO);
        return "new_employee";
    }

    @PostMapping("/saveNewEmployee")
    public String saveNewEmployee(@ModelAttribute("employeeDTO")EmployeeDTO employeeDTO){
        //Save employee to database
        employeeService.saveEmployee(employeeDTO);
        return "redirect:/";
    }


    @RequestMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        //get Employee from the service
        EmployeeDTO employeeDTO= employeeService.getEmployeeById(id);

        //Set Employee as a model attribute to pre-populate the form
        model.addAttribute("employeeDTO",employeeDTO);
        return "update_employee";
    }



    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id){
        //get Employee from the service
        EmployeeDTO employeeDTO= employeeService.getEmployeeById(id);

        //call a delete Employee method from the service
        employeeService.deleteEmployeeById(id);

        return "redirect:/?id="+id;
    }



}
