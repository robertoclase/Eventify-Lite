package com.salesianostriana.dam.Eventify.Lite.eventifylite.dto;

import java.time.LocalDateTime;

public record EntradaDto(Long id, String tituloEvento, LocalDateTime fechaCompra, String estado) {}

