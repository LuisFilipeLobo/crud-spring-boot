package com.springboot.crud.controller;

import com.springboot.crud.entities.Usuario;
import com.springboot.crud.repository.UsuarioRepository;
import com.springboot.crud.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {
	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepo;

	@GetMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public Page<Usuario> listar(Pageable pageable) {
		return usuarioService.listar(pageable);
	}

	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Usuario> buscar(@PathVariable Long id) {
		return usuarioRepo.findById(id);
	}

	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public String adicionarUsuario(@RequestBody Usuario usuario) {
		usuarioRepo.save(usuario);
		return usuario.getNome() + " foi adicionado.";
	}

	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuarioAtual = usuarioRepo.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioAtual, "id");
		return usuarioRepo.save(usuarioAtual);
	}

	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		usuarioRepo.deleteById(id);
	}
}
