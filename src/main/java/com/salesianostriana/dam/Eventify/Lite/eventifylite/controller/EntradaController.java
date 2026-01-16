package com.salesianostriana.dam.Eventify.Lite.eventifylite.controller;

import com.salesianostriana.dam.Eventify.Lite.eventifylite.dto.CreateEntradaRequest;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Entrada;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.repository.EntradaRepository;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/entradas")
@RequiredArgsConstructor
public class EntradaController {
    private final EntradaService entradaService;
    private final EntradaRepository entradaRepository;

    @PostMapping
    public ResponseEntity<Entrada> comprar(@RequestBody CreateEntradaRequest request) {
        return ResponseEntity.ok(entradaService.comprarEntrada(request));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        entradaService.cancelarEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
