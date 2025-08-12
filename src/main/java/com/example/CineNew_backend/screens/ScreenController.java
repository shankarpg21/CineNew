package com.example.CineNew_backend.screens;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController

@RequestMapping("api/screens/")
public class ScreenController {
    

    private final ScreenService screenService;
    public ScreenController(ScreenService screenService){
        this.screenService=screenService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addScreens")
    public ResponseEntity<String> addScreens(@Valid @RequestBody ScreenRequest screen){
        String res=screenService.addScreens(screen);
        if(res.equals("Screen added successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }    
}
