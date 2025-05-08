package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.EventRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iEventRoomRepository extends iGenericRepository<EventRoom, UUID> {

    // 1. Función JPA: Salones con capacidad mínima (para planificación de eventos)
    List<EventRoom> findByCapacityGreaterThanEqual(int minCapacity);

    // 2. Query Híbrida: Salones por tipo de decoración (para eventos temáticos)
    @Query("SELECT e FROM EventRoom e WHERE e.ornament LIKE %:ornamentStyle%")
    List<EventRoom> findEventRoomsByOrnamentStyle(@Param("ornamentStyle") String style);

    // 3. Query Nativa: Salones más reservados (para promociones y planificación)
    @Query(value = "SELECT er.*, COUNT(b.id) as booking_count FROM event_room er " +
            "LEFT JOIN book b ON er.id = b.event_room_id " +
            "GROUP BY er.id ORDER BY booking_count DESC LIMIT :limit", nativeQuery = true)
    List<EventRoom> findMostBookedEventRooms(@Param("limit") int limit);
}