package br.com.itau.eventdash.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.itau.eventdash.model.Evento;

public interface EventoDAO extends CrudRepository<Evento, Integer>{
    public List<Evento> findByDataevtBetween(Date iniDate, Date finalDate);

    @Query(value = "SELECT itmn_alarme.descricao, COUNT(itmn_alarme.id_alarme) as total FROM itmn_evento INNER JOIN itmn_alarme ON itmn_evento.id_alarme = itmn_alarme.id_alarme WHERE itmn_evento.data_evt between STR_TO_DATE(?1,'%Y-%m-%d') and STR_TO_DATE(?2,'%Y-%m-%d') GROUP BY itmn_alarme.id_alarme;", nativeQuery = true)
    public List<?> countAlarmes(String dataini, String datafim);
}