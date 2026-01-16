package com.salesianostriana.dam.Eventify.Lite.eventifylite.repository;

import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Entrada;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.EstadoEntrada;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.awt.print.Pageable;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    List<Entrada> findByAsistenteIdAndEstado(Long asistenteId, EstadoEntrada estado);

    @Query("SELECT e FROM Entrada e JOIN FETCH e.asistente WHERE e.evento.id = :eventoId")
    Page<Entrada> findByEventoIdWithAsistente(Long eventoId, Pageable pageable);


    @EntityGraph(attributePaths = {"asistente", "evento"})
    Page<Entrada> findAll(Pageable pageable);
}