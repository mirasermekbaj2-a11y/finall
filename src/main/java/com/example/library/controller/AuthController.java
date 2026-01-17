package com.example.library.controller;

import com.example.library.dto.AuthDto;
import com.example.library.entity.Permission;
import com.example.library.entity.UserModel;
import com.example.library.repository.PermissionRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody AuthDto authDto) {
        if (userRepository.findByEmail(authDto.getEmail()) != null) {
            return "Ошибка: Пользователь с таким email уже существует!";
        }

        UserModel newUser = new UserModel();
        newUser.setEmail(authDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(authDto.getPassword()));

        Permission userRole = permissionRepository.findByPermission("ROLE_USER");
        if (userRole != null) {
            newUser.setPermissions(Collections.singletonList(userRole));
        }

        userRepository.save(newUser);
        return "Пользователь успешно зарегистрирован!";
    }
}