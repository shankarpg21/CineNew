package com.example.CineNew_backend.security;

import com.example.CineNew_backend.admins.Admin;
import com.example.CineNew_backend.admins.AdminRepository;
import com.example.CineNew_backend.users.UserRepository;
import com.example.CineNew_backend.users.Users;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary // VERY IMPORTANT → avoids cycle
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return User.withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .authorities("ROLE_ADMIN")
                    .build();
        }

        Users user = userRepository.findByEmail(email);
        if (user != null) {
            return User.withUsername(user.getEmail())
                    .password(user.getPassWord())
                    .authorities("ROLE_USER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
