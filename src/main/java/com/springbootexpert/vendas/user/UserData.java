package com.springbootexpert.vendas.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    @NotEmpty(message = "{login.field-required}")
    private String login;
    @Column
    @NotEmpty(message = "{password.field-required}")
    private String password;
    @Column
    private boolean admin;
}
