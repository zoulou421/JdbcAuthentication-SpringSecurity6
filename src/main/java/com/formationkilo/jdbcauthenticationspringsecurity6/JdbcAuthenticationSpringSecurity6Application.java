package com.formationkilo.jdbcauthenticationspringsecurity6;

import com.formationkilo.jdbcauthenticationspringsecurity6.config.entities.AppUser;
import com.formationkilo.jdbcauthenticationspringsecurity6.config.service.IAccountService;
import com.formationkilo.jdbcauthenticationspringsecurity6.dao.EmployeeRepository;
import com.formationkilo.jdbcauthenticationspringsecurity6.dto.EmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

 //  @Bean
    CommandLineRunner start2(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder= passwordEncoder();
        return  args-> {
          jdbcUserDetailsManager.createUser(
                          User.withUsername("user1").
                          password(passwordEncoder.encode("1234")).roles("USER").build()

          );

          jdbcUserDetailsManager.createUser(
                    User.withUsername("user2").
                            password(passwordEncoder.encode("1234")).roles("USER").build()

          );

          jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").
                            password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()

          );

        };
    }

    //second method

   // @Bean
    CommandLineRunner start(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder= passwordEncoder();
        return  args-> {
            //us1
            UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u1.equals(null))
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user11").
                            password(passwordEncoder.encode("1234")).roles("USER").build()

            );

            //us2
            UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u2==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").
                                password(passwordEncoder.encode("1234")).roles("USER").build()

                );

            //us1
            UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(u3==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin3").
                                password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()

                );


        };
    }

   // @Bean
    CommandLineRunner commandLineRunnerUserDetails(IAccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","0123","user1@user1.com","0123");
            accountService.addNewUser("user2","0123","user2@user2.com","0123");
            accountService.addNewUser("admin","0123","admin@user.com","0123");

            //Roles affected
            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin","USER");

        };
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
