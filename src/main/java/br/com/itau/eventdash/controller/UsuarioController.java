package br.com.itau.eventdash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.eventdash.dao.UsuarioDAO;
import br.com.itau.eventdash.model.Usuario;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody Usuario userIncomplete) {
		Usuario userFinded = dao.findByEmailOrRacf(userIncomplete.getEmail(), userIncomplete.getRacf());

		if (userFinded != null) {
			if (userIncomplete.getSenha().equals(userFinded.getSenha())) {
                userFinded.setSenha("*******");
                return ResponseEntity.ok(userFinded);
			}
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(404).build(); // usuário não existe
	}

}
