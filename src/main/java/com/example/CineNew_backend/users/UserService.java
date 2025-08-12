package com.example.CineNew_backend.users;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CineNew_backend.security.JwtUtil;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.jwtUtil=jwtUtil;
        this.passwordEncoder=passwordEncoder;
    }

    public String registerUser(UserRequestDTO userRequestDTO){
        String hashedPassword=passwordEncoder.encode(userRequestDTO.getPassword());
        Users opt=userRepository.findByEmail(userRequestDTO.getEmail());
        if(opt!=null){
            return "Email already exists";
        }
        userRepository.save(new Users(userRequestDTO.getName(),userRequestDTO.getEmail(),hashedPassword));
        return "User registered successfully";
    }

    public AuthResponse loginUser(UserRequestDTO userRequestDTO){
        String email=userRequestDTO.getEmail();
        String rawPassword=userRequestDTO.getPassword();
        
        Users opt=userRepository.findByEmail(email);
        if(opt==null){
            return new AuthResponse("Email does'nt exists");
        }
        if(passwordEncoder.matches(rawPassword,opt.getPassWord())){
            return new AuthResponse("Login successfull",jwtUtil.generateToken(email,"USER"));
        }
        return new AuthResponse("Invalid credentials");
    }
}
