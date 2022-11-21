package com.springboot.crud.controller;

import com.springboot.crud.entities.Usuario;
import com.springboot.crud.repository.UsuarioRepository;
import com.springboot.crud.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {
	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepo;

	@GetMapping("/alunos")
	public Page<Usuario> listar(Pageable pageable) {
		return usuarioService.listar(pageable);
	}

	@GetMapping("/aluno/{id}")
	public Optional<Usuario> buscar(@PathVariable Long id) {
		return usuarioRepo.findById(id);
	}
}
