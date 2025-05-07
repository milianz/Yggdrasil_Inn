package org.milianz.yggdrasil_inn.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iGenericRepository <T, ID> extends JpaRepository<T, ID> {
}
