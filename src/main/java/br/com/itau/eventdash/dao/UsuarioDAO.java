package br.com.itau.eventdash.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.eventdash.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
	public Usuario findByEmailOrRacf(String email, String racf);
	
}
