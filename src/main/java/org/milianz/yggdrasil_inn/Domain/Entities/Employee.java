package org.milianz.yggdrasil_inn.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // Añade esta línea para solucionar el warning
@Table(name = "employee")
public class Employee extends User {
    private String department;
    private String branch;

    @Column(name = "supervisor_id")
    private UUID supervisor;

    private boolean isCoordinator;
    private boolean isSupervisor;


    public void setIsCoordinator(boolean isCoordinator) {
        this.isCoordinator = isCoordinator;
    }



    public void setIsSupervisor(boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }


}