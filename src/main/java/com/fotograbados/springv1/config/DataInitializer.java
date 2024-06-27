package com.fotograbados.springv1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fotograbados.springv1.persistence.entities.RolEntity;
import com.fotograbados.springv1.persistence.repository.RolRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear y guardar roles si no existen
        if (rolRepository.findByTipo("USER") == null) {
            RolEntity userRole = new RolEntity();
            userRole.setTipo("USER");
            userRole.setDescripcion("Rol de usuario estándar");
            rolRepository.save(userRole);
        }
        if (rolRepository.findByTipo("ADMIN") == null) {
            RolEntity adminRole = new RolEntity();
            adminRole.setTipo("ADMIN");
            adminRole.setDescripcion("Rol de administrador");
            rolRepository.save(adminRole);
        }
    }
}
