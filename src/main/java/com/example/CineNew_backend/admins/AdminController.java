package com.example.CineNew_backend.admins;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CineNew_backend.users.AuthResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginAdmin(@Valid @RequestBody AdminRequestDTO adminRequestDTO){
        AuthResponse res=adminService.loginAdmin(adminRequestDTO);
        if(res.getMsg().equals("Login successful")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}