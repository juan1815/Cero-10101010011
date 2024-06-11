package com.fotograbados.springv1.domain.service;


import com.fotograbados.springv1.persistence.entities.Users;


import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
	List<Users> findAll();
	Optional<Users> findById(Long id);
	Users save (Users user);
	Users updateRole(Long userId, String newRole);
	Optional<Users> findByEmail(String email);

}
