package com.formationkilo.jdbcauthenticationspringsecurity6.config.security;


import com.formationkilo.jdbcauthenticationspringsecurity6.config.service.IUserDetailService;
import com.formationkilo.jdbcauthenticationspringsecurity6.config.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityConfig  {


   // @Autowired
    private PasswordEncoder passwordEncoder;

   // @Autowired
    //private UserDetailServiceImpl userDetailServiceImpl;
    private IUserDetailService userDetailService;

   // @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //If this endpoint is a Spring MVC endpoint,
        // please use requestMatchers(MvcRequestMatcher);
        httpSecurity

                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/user/**")).hasRole("USER")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                   .formLogin(withDefaults());//default form spring used
             /*   .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                );*/
        httpSecurity.rememberMe(withDefaults());

       /* httpSecurity.logout((logout) ->
                logout.deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutUrl("/custom-logout").permitAll()
                        .logoutSuccessUrl("/logout-success").permitAll()
        );*/

        httpSecurity
                // sample exception handling customization
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedPage("/access-denied")
                );

        //httpSecurity.userDetailsService(userDetailServiceImpl);
        httpSecurity.userDetailsService(userDetailService);
       // httpSecurity.userDetailsService(userDetailServiceImpl);//calling of userDetails Strategy to be used

        return httpSecurity.build();
    }

}