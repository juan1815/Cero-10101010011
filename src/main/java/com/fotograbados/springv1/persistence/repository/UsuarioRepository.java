package com.fotograbados.springv1.persistence.repository;

import com.fotograbados.springv1.persistence.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Users, Long> {
    // Optional<Users> findByEmail(String email);
}
