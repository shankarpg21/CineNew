package com.example.CineNew_backend.admins;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {
    
    @Email
    private String email;

    @NotNull
    private String password;
}
