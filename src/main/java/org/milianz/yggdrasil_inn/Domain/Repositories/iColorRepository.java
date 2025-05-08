package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Color;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iColorRepository extends iGenericRepository<Color, Integer> {

    // 1. Función JPA: Buscar un color exacto por nombre (ignorando mayúsculas/minúsculas)
    Color findByColorNameIgnoreCase(String colorName);

    // 2. Query Híbrida: Colores que contienen cierto patrón (para buscador)
    @Query("SELECT c FROM Color c WHERE c.colorName LIKE %:pattern%")
    List<Color> findColorsContaining(@Param("pattern") String pattern);

    // 3. Query Nativa: Colores ordenados por frecuencia de uso en pisos (para mantenimiento)
    @Query(value = "SELECT c.*, COUNT(f.id) as floor_count FROM color c JOIN floor f ON c.id = f.color_id " +
            "GROUP BY c.id ORDER BY floor_count DESC", nativeQuery = true)
    List<Color> findColorsOrderedByFrequencyInFloors();
}