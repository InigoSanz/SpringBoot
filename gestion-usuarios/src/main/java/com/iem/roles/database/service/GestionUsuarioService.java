package com.iem.roles.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.roles.database.entity.Rol;
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

	public boolean actualizarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioOpt = obtenerUsuario(usuario.getId());

		if (usuarioOpt.isEmpty()) {
			return false;
		}

		if (usuario.getNombre() == null) {
			usuario.setNombre(usuarioOpt.get().getNombre());
		}

		if (usuario.getCorreo() == null) {
			usuario.setCorreo(usuarioOpt.get().getCorreo());
		}

		usuario.setFechaCreacion(usuarioOpt.get().getFechaCreacion());

		usuarioRepository.save(usuario);
		return true;
	}

	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	// El optional significa que puede venir nullo y podemos validarlo luego al
	// utilizar el método.
	public Optional<Usuario> obtenerUsuario(Long id) {
		return usuarioRepository.findById(id);
	}

	public List<Usuario> obtenerUsuarios() {
		return usuarioRepository.findAll();
	}

	public Rol crearRol(Rol nuevoRol) {
		// Validamos si el usuario existe antes de asignarle un rol
		Optional<Usuario> usuarioOpt = obtenerUsuario(nuevoRol.getIdUsuario());

		if (usuarioOpt.isEmpty()) {
			return null;
		}

		return rolRepository.save(nuevoRol);
	}

	public List<Rol> obtenerRoles() {
		return rolRepository.findAll();
	}

	public List<Rol> obtenerRolesPorUsuario(Long idUsuario) {
		return rolRepository.findByIdUsuario(idUsuario);
	}
}