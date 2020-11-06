package br.com.itau.eventdash.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.eventdash.dao.EventoDAO;
import br.com.itau.eventdash.model.Evento;

@RestController
@CrossOrigin("*")
public class EventoController {
    
    @Autowired
    private EventoDAO dao;

    @GetMapping("/evento/{id}")
    public ResponseEntity <Evento> buscarEvento(@PathVariable int id){
        Evento evento = dao.findById(id).orElse(null);
        
        if(evento != null){
            return ResponseEntity.ok(evento);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/evento/data")
    public ResponseEntity <List<Evento>> buscarPorData(@RequestBody ObjectNode json) throws ParseException{      
        
        Date ini = new SimpleDateFormat("yyyy-MM-dd").parse(json.get("dt1").asText());
        Date fim = new SimpleDateFormat("yyyy-MM-dd").parse(json.get("dt2").asText());

        System.out.println(ini.toString());

        List<Evento> eventos = dao.findByDataevtBetweenOrderByDataevt(ini, fim);
        
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/evento/alarmes")
    public ResponseEntity <List<?>> buscarAlarmes(@RequestBody ObjectNode json) throws ParseException{       
        
        List<?> eventos = dao.countAlarmes(json.get("dt1").asText(), json.get("dt2").asText());
        
        return ResponseEntity.ok(eventos);
    }

}