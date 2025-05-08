package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface iUserRepository extends iGenericRepository<User, UUID> {

    // 1. Función JPA: Buscar usuario por email (para login)
    User findByEmail(String email);

    // 2. Query Híbrida: Usuarios nacidos antes de cierta fecha (para campañas por edad)
    @Query("SELECT u FROM User u WHERE u.birthDate < :date ORDER BY u.birthDate")
    List<User> findUsersOlderThan(@Param("date") Date date);

    // 3. Query Nativa: Top usuarios con más reservas (para programa VIP)
    @Query(value = "SELECT u.*, COUNT(b.id) as booking_count FROM user_data u " +
            "LEFT JOIN book b ON u.id = b.user_id " +
            "GROUP BY u.id ORDER BY booking_count DESC LIMIT :limit", nativeQuery = true)
    List<User> findTopUsersByBookings(@Param("limit") int limit);
}