package com.example.CineNew_backend.admins;

import org.springframework.stereotype.Service;

import com.example.CineNew_backend.security.JwtUtil;
import com.example.CineNew_backend.users.AuthResponse;

@Service
public class AdminService {
    
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;

    public AdminService(AdminRepository adminRepository,JwtUtil jwtUtil){
        this.adminRepository=adminRepository;
        this.jwtUtil=jwtUtil;
    }

    public AuthResponse loginAdmin(AdminRequestDTO adminRequestDTO){
        String email=adminRequestDTO.getEmail();
        String password=adminRequestDTO.getPassword();
        Admin admin=adminRepository.findByEmail(email);
        if(admin==null) return new AuthResponse("Admin id does'nt exists");
        String token="";
        if(password.equals("GatewayToCinemas")){
            token=jwtUtil.generateToken(email,"ADMIN");
            return new AuthResponse("Login successful",token);
        }
        return new AuthResponse("Invalid credentials");
    }
}
