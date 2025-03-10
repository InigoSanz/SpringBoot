package com.iem.roles.database.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.roles.database.entity.Usuario;
import com.iem.roles.database.repository.PermisoRepository;
import com.iem.roles.database.repository.RolRepository;
import com.iem.roles.database.repository.UsuarioRepository;

@Service
public class GestionUsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RolRepository rolRepository;
	
	@Autowired
	PermisoRepository permisoRepository;
	
	public Long crearUsuario(Usuario usuario) {
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		
		return usuarioGuardado.getId();
	}
	
	public void actualizarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	// El optional significa que puede venir nullo y podemos validarlo luego al utilizar el método.
	public Optional<Usuario> obtenerUsuario(Long id) {
		return usuarioRepository.findById(id);
	}
}