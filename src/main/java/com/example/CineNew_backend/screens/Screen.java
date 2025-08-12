package com.example.CineNew_backend.screens;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long screenId;

    @NotNull
    private String screenName;

    public Screen(String screenName){
        this.screenName=screenName;
    }
}
