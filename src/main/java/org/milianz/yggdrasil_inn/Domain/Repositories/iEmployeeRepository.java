package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iEmployeeRepository extends iGenericRepository<Employee, UUID> {

    // 1. Función JPA: Buscar empleados por nombre o apellido (para búsqueda rápida)
    List<Employee> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    // 2. Query Híbrida: Empleados con correo de cierto dominio (para comunicaciones)
    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:domain%")
    List<Employee> findEmployeesByEmailDomain(@Param("domain") String domain);

    // 3. Query Nativa: Top empleados con más asignaciones de fidelidad (para recompensas)
    @Query(value = "SELECT e.* FROM user_data e JOIN fidelity f ON e.id = f.employee_id " +
            "GROUP BY e.id ORDER BY COUNT(f.id) DESC LIMIT :limit", nativeQuery = true)
    List<Employee> findTopEmployeesByFidelityAssignments(@Param("limit") int limit);
}