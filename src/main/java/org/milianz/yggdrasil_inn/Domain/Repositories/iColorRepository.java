package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.milianz.yggdrasil_inn.Domain.Entities.Color;

public interface iColorRepository extends iGenericRepository<Color, Integer> {
    // Additional methods specific to Color can be defined here
    // For example:
    // List<Color> findByShade(String shade);
    // Color findByHexCode(String hexCode);
}
