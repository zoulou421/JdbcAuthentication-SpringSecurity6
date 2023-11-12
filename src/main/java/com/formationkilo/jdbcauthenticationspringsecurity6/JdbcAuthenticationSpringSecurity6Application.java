package com.formationkilo.jdbcauthenticationspringsecurity6;

import com.formationkilo.jdbcauthenticationspringsecurity6.dao.EmployeeRepository;
import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JdbcAuthenticationSpringSecurity6Application {

    public static void main(String[] args) {
        SpringApplication.run(JdbcAuthenticationSpringSecurity6Application.class, args);
    }

    // @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
        return  args-> {
            employeeRepository.save(new
                    EmployeeDTO(0,"oko","Manou","okoma@ma.com"));

            employeeRepository.findAll().forEach(e->{
                System.out.println(e.getFirstName());
            });

        };
    }

}
