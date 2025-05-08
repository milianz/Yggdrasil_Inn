package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Fidelity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface iFidelityRepository extends iGenericRepository<Fidelity, UUID> {

    // 1. Función JPA: Programas de fidelidad actualizados después de cierta fecha
    List<Fidelity> findByFechaUltimaAsignacionAfter(LocalDate date);

    // 2. Query Híbrida: Clientes con más puntos de fidelidad (para VIPs)
    @Query("SELECT f FROM Fidelity f WHERE f.puntosAcumulados > :minPoints " +
            "ORDER BY f.puntosAcumulados DESC")
    List<Fidelity> findByPointsGreaterThan(@Param("minPoints") int minPoints);

    // 3. Query Nativa: Puntos de fidelidad por patrón de correo (para campañas específicas)
    @Query(value = "SELECT f.* FROM fidelity f JOIN user_data u ON f.user_id = u.id " +
            "WHERE LOWER(u.email) LIKE LOWER(:emailPattern) " +
            "ORDER BY f.puntos_acumulados DESC", nativeQuery = true)
    List<Fidelity> findFidelityByUserEmailPattern(@Param("emailPattern") String emailPattern);
}