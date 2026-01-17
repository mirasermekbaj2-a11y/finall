package com.example.library.config;

import com.example.library.entity.Permission;
import com.example.library.repository.PermissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(PermissionRepository permissionRepository) {
        return args -> {
            if (permissionRepository.findByPermission("ROLE_USER") == null) {
                Permission userRole = new Permission();
                userRole.setPermission("ROLE_USER");
                permissionRepository.save(userRole);
            }
        };
    }
}