package org.milianz.yggdrasil_inn.Domain.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rotation")
public class Rotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String month;
    private String turno;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "emlployee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
}
