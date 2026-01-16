package com.salesianostriana.dam.Eventify.Lite.eventifylite.repository;

import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventoRepository extends JpaRepository<Evento,Long> {

}
