package com.example.CineNew_backend.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String msg;
    private String token;
    
    public AuthResponse(String msg){
        this.msg=msg;
    }
}
