package br.com.itau.eventdash.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.eventdash.model.Alarme;

public interface AlarmeDAO extends CrudRepository<Alarme, Integer>{
    
}