package com.springboot.crud.service;

import com.springboot.crud.entities.Usuario;
import com.springboot.crud.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
	private UsuarioRepository usuarioRepo;

	public Page<Usuario> listar(Pageable pageable) {
		return usuarioRepo.listar(pageable);
	}
}
