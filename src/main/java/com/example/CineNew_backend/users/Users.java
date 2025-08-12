package com.example.CineNew_backend.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class Users {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long userId;

    @NotNull
    private String userName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min=8,message = "Password should be minimum 8 characters")
    private String passWord;

    Users(String userName,String email,String passWord){
        this.userName=userName;
        this.email=email;
        this.passWord=passWord;
    }

}
