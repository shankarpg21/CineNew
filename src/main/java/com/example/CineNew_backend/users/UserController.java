package com.example.CineNew_backend.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        String res=userService.registerUser(userRequestDTO);
        if(res.equals("User registered successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        AuthResponse res=userService.loginUser(userRequestDTO);
        if(res.getMsg().equals("Login successfull")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
}
