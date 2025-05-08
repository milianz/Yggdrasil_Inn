package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Rotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface iRotationRepository extends iGenericRepository<Rotation, UUID> {

    // 1. Función JPA: Rotaciones entre fechas (para planificación mensual)
    List<Rotation> findByRotationDateBetween(LocalDate startDate, LocalDate endDate);

    // 2. Query Híbrida: Historial de rotaciones de un empleado (para evaluación)
    @Query("SELECT r FROM Rotation r WHERE r.employee.id = :employeeId " +
            "ORDER BY r.rotationDate DESC")
    List<Rotation> findRotationsForEmployee(@Param("employeeId") UUID employeeId);

    // 3. Query Nativa: Rotaciones por nombre de usuario (para supervisión)
    @Query(value = "SELECT r.* FROM rotation r " +
            "JOIN user_data u ON r.employee_id = u.id " +
            "WHERE u.username = :username " +
            "ORDER BY r.rotation_date DESC", nativeQuery = true)
    List<Rotation> findRotationsByUsername(@Param("username") String username);
}