package org.milianz.yggdrasil_inn.Domain.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_room")
public class EventRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private int capacity;
    private String ornament;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
}
