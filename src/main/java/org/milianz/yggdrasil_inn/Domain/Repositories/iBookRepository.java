package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface iBookRepository extends iGenericRepository<Book, Long> {

    // 1. Función JPA: Reservas con un estado específico y fecha de entrada posterior a cierta fecha
    List<Book> findByStatusAndCheckInAfter(String status, LocalDate date);

    // 2. Query Híbrida: Reservas entre dos fechas específicas (para planificación)
    @Query("SELECT b FROM Book b WHERE b.checkIn BETWEEN :startDate AND :endDate ORDER BY b.checkIn")
    List<Book> findBookingsBetweenDates(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    // 3. Query Nativa: Reservas de un huésped específico por nombre de usuario
    @Query(value = "SELECT b.* FROM book b JOIN user_data u ON b.user_id = u.id " +
            "WHERE LOWER(u.username) = LOWER(:username) ORDER BY b.check_in DESC", nativeQuery = true)
    List<Book> findBookingsByUsername(@Param("username") String username);
}