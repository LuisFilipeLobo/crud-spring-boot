package com.springboot.crud.controller;

import com.springboot.crud.entities.Usuario;
import com.springboot.crud.service.ServiceException.ServiceException;
import com.springboot.crud.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {
	private UsuarioService usuarioService;

	@GetMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public Page<Usuario> listar(Pageable pageable) {
		return usuarioService.listar(pageable);
	}

	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Usuario> buscar(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@PostMapping("/usuario")
	public ResponseEntity<Usuario> adicionarUsuario(@RequestBody Usuario usuario) {
		try {
			usuario = usuarioService.addUsuario(usuario);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(usuario.getId()).toUri();

			return ResponseEntity.created(uri).body(usuario);
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		try {
			Usuario obj = usuarioService.atualizarUsuario(id, usuario);

			return ResponseEntity.ok(obj);
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar(@PathVariable Long id) {
		usuarioService.deletarUsuario(id);
	}
}
