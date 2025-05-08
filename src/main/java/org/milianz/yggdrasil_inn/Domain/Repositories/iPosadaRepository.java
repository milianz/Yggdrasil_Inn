package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Posada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iPosadaRepository extends iGenericRepository<Posada, UUID> {

    // 1. Función JPA: Buscar posada por nombre (para búsqueda rápida)
    Posada findByNameContainingIgnoreCase(String name);

    // 2. Query Híbrida: Posadas por patrón de dirección (para ubicaciones específicas)
    @Query("SELECT p FROM Posada p WHERE p.address LIKE %:addressPattern%")
    List<Posada> findPosadasByAddressPattern(@Param("addressPattern") String addressPattern);

    // 3. Query Nativa: Posadas con más de X pisos (para clasificación por tamaño)
    @Query(value = "SELECT p.*, COUNT(f.id) as floor_count FROM posada p " +
            "LEFT JOIN floor f ON p.id = f.posada_id " +
            "GROUP BY p.id HAVING COUNT(f.id) > :minFloors", nativeQuery = true)
    List<Posada> findPosadasWithMoreThanXFloors(@Param("minFloors") int minFloors);
}