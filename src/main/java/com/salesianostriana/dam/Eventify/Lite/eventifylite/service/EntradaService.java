package com.salesianostriana.dam.Eventify.Lite.eventifylite.service;

import com.salesianostriana.dam.Eventify.Lite.eventifylite.dto.CreateEntradaRequest;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Asistente;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Entrada;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.EstadoEntrada;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.model.Evento;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.repository.AsistenteRepository;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.repository.EntradaRepository;
import com.salesianostriana.dam.Eventify.Lite.eventifylite.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EntradaService {
    private final EntradaRepository entradaRepository;
    private final EventoRepository eventoRepository;
    private final AsistenteRepository asistenteRepository;

    @Transactional
    public Entrada comprarEntrada(CreateEntradaRequest req) {
        Evento evento = eventoRepository.findById(req.eventoId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));


        if (evento.getEntradasVendidas() >= evento.getAforoMaximo()) {
            throw new IllegalStateException("Aforo máximo alcanzado");
        }

        Asistente asistente = asistenteRepository.getReferenceById(req.asistenteId());

        Entrada entrada = new Entrada();
        entrada.setEvento(evento);
        entrada.setAsistente(asistente);
        entrada.setFechaCompra(LocalDateTime.now());
        entrada.setEstado(EstadoEntrada.ACTIVA);

        evento.setEntradasVendidas(evento.getEntradasVendidas() + 1);
        return entradaRepository.save(entrada);
    }

    @Transactional
    public void cancelarEntrada(Long id) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada"));

        if (entrada.getEstado() == EstadoEntrada.CANCELADA) return;

        entrada.setEstado(EstadoEntrada.CANCELADA);
        Evento evento = entrada.getEvento();
        evento.setEntradasVendidas(evento.getEntradasVendidas() - 1);
    }
}
