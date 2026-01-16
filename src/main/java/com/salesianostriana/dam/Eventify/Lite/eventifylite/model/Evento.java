package com.salesianostriana.dam.Eventify.Lite.eventifylite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private LocalDateTime fecha;
    private int aforoMaximo;
    private int entradasVendidas;

    @OneToMany
    @ToString.Exclude
    List<Entrada> listaEntradas = new ArrayList<>();

    @OneToMany(mappedBy = "evento")
    @ToString.Exclude
    private List<Entrada> entradas = new ArrayList<>();

    public void addEntrada(Entrada entrada) {
        entradas.add(entrada);
        entrada.setEvento(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Evento evento = (Evento) o;
        return getId() != null && Objects.equals(getId(), evento.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}


