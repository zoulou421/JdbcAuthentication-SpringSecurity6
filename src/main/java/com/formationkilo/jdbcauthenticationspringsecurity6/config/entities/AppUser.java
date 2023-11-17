package com.formationkilo.jdbcauthenticationspringsecurity6.config.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class AppUser {
    @Id
    private String userId;
    @Column(unique=true)
    private String username;
    private String password;
    private  String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
