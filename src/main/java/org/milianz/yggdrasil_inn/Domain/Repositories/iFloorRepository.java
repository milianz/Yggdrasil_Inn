package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Floor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iFloorRepository extends iGenericRepository<Floor, Integer> {

    // 1. Función JPA: Pisos de una posada específica (para gestión por edificio)
    List<Floor> findByPosadaId(UUID posadaId);

    // 2. Query Híbrida: Pisos por color (para mantenimiento y organización visual)
    @Query("SELECT f FROM Floor f WHERE f.color.colorName = :colorName")
    List<Floor> findFloorsByColorName(@Param("colorName") String colorName);

    // 3. Query Nativa: Pisos ordenados por cantidad de habitaciones (para asignación de personal)
    @Query(value = "SELECT f.*, COUNT(r.id) as room_count FROM floor f " +
            "LEFT JOIN room r ON f.id = r.floor_id " +
            "GROUP BY f.id ORDER BY room_count DESC", nativeQuery = true)
    List<Floor> findFloorsOrderedByRoomCount();
}