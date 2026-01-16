package com.salesianostriana.dam.Eventify.Lite.eventifylite.controller;

import com.salesianostriana.dam.Eventify.Lite.eventifylite.dto.EntradaDto;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Entrada;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.repository.EntradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
class EventoController {
    private final EntradaRepository entradaRepository;

    @GetMapping("/{id}/entradas")
    public ResponseEntity<Page<EntradaDto>> getEntradasByEvento(
            @PathVariable Long id,
            Pageable pageable) {

        Page<Entrada> entradas = entradaRepository.findByEventoIdWithAsistente(id, pageable);

        Page<EntradaDto> dtos = entradas.map(e -> new EntradaDto(
                e.getId(),
                e.getEvento().getTitulo(),
                e.getFechaCompra(),
                e.getEstado().name()
        ));

        return ResponseEntity.ok(dtos);
    }
}