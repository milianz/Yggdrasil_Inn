package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface iBillRepository extends iGenericRepository<Bill, UUID> {

    // 1. Función de JPA (derivada del nombre del método)
    List<Bill> findByIssueDateBetween(LocalDate startDate, LocalDate endDate);

    // 2. Query Híbrida (JPQL)
    @Query("SELECT b FROM Bill b WHERE b.total > :amount ORDER BY b.total DESC")
    List<Bill> findBillsWithTotalGreaterThan(@Param("amount") double amount);

    // 3. Query Nativa (SQL)
    @Query(value = "SELECT * FROM bill b INNER JOIN book bk ON b.book_id = bk.id " +
            "WHERE bk.status = :status", nativeQuery = true)
    List<Bill> findBillsByBookStatus(@Param("status") String status);
}