package org.milianz.yggdrasil_inn.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee extends User{
    @ManyToOne
    @JoinColumn(name = "posada_id")
    private Posada posada;

    private String departamento;

    @ManyToOne
    @JoinColumn(name = "superior_id")
    private Employee superior;  // Relación recursiva

    private boolean esCoordinador;
}
