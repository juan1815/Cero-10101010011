package com.fotograbados.springv1.domain.service;


import com.fotograbados.springv1.persistence.entities.RolEntity;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.repository.RolRepository;
import com.fotograbados.springv1.persistence.repository.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

	@Service
	public class UsuarioServiceImpl implements IUsuarioService {
		@Autowired
		private UsuarioRepository usuarioRepository;
		@Autowired
		private RolRepository rolRepository;


	@Override
	public List<Users> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Users> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Users save(Users user) {
		// Buscar el rol "USER" en la base de datos
		RolEntity rol = rolRepository.findByTipo("USER");
		if (rol == null) {
			throw new RuntimeException("Rol 'USER' no encontrado en la base de datos");
		}

		// Asigna el rol al usuario
		user.setRolEntity(rol);

		// Guardar el usuario con el rol asignado
		return usuarioRepository.save(user);
	}


		@Override
	@Transactional
	public Users updateRole(Long userId, String newRole) {
		Users user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		RolEntity rol = rolRepository.findByTipo(newRole);
		if (rol == null) {
			throw new RuntimeException("Rol '" + newRole + "' no encontrado en la base de datos");
		}
		user.setRolEntity(rol);
		return usuarioRepository.save(user);
	}

	@Override
	public Optional<Users> findByEmail(String email) {
		return Optional.empty();
	}
}
