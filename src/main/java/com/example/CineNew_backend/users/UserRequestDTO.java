package com.example.CineNew_backend.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDTO {

    @Email
    @NotNull
    private String email;

    private String name;

    @NotNull
    private String password;

    UserRequestDTO(String email,String password){
        this.email=email;
        this.password=password;
    }
}
