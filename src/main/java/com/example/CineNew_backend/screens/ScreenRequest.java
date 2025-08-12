package com.example.CineNew_backend.screens;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScreenRequest {

    @NotNull
    private String screenName;

    public ScreenRequest(String screenName){
        this.screenName=screenName;
    }
}
