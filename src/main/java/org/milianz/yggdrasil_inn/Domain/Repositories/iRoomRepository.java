package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iRoomRepository extends iGenericRepository<Room, UUID> {

    // 1. Función JPA: Habitaciones de un piso específico (para asignación de limpieza)
    List<Room> findByFloorId(Integer floorId);

    // 2. Query Híbrida: Búsqueda de habitaciones por nombre o característica
    @Query("SELECT r FROM Room r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Room> searchRoomsByKeyword(@Param("keyword") String keyword);

    // 3. Query Nativa: Habitaciones por color de piso (para identificación visual)
    @Query(value = "SELECT r.* FROM room r " +
            "INNER JOIN floor f ON r.floor_id = f.id " +
            "WHERE f.color_id = :colorId", nativeQuery = true)
    List<Room> findRoomsByFloorColor(@Param("colorId") Integer colorId);
}