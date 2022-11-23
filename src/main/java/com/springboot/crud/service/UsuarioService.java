package com.springboot.crud.service;

import com.springboot.crud.entities.Usuario;
import com.springboot.crud.repository.UsuarioRepository;
import com.springboot.crud.service.ServiceException.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {
	private UsuarioRepository usuarioRepo;

	public Page<Usuario> listar(Pageable pageable) {
		return usuarioRepo.listar(pageable);
	}

	public Optional<Usuario> findById(Long id) {
		return usuarioRepo.findById(id);
	}

	public Usuario addUsuario(Usuario entity) {
		Usuario usuario = usuarioRepo.findByEmail(entity.getEmail());

		if (usuario != null) {
			throw new ServiceException("Email já cadastrado.");
		}

		return usuarioRepo.save(entity);
	}

	public Usuario atualizarUsuario(Long id, Usuario entity) {
		Usuario usuarioAtual = usuarioRepo.findById(id).get();

		// Impedir email duplicado
		if (!Objects.equals(usuarioAtual.getEmail(), entity.getEmail())) {

			Usuario verificarEmail = usuarioRepo.findByEmail(entity.getEmail());

			if (verificarEmail != null) {
				throw new ServiceException("Email já cadastrado");
			}
		}

		BeanUtils.copyProperties(entity, usuarioAtual, "id");

		return usuarioRepo.save(usuarioAtual);
	}

	public void deletarUsuario(Long id) {
		usuarioRepo.deleteById(id);
	}
}
