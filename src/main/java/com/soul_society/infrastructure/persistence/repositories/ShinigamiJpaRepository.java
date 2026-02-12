package com.soul_society.infrastructure.persistence.repositories;

import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.infrastructure.persistence.entities.ShinigamiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * JPA Repository para acesso aos dados de Shinigami
 */
@Repository
public interface ShinigamiJpaRepository extends JpaRepository<ShinigamiEntity, UUID> {

    Optional<ShinigamiEntity> findByEmail(String email);

    List<ShinigamiEntity> findByDivisao(Divisao divisao);

    List<ShinigamiEntity> findByCargo(Cargo cargo);

    boolean existsByEmail(String email);

    boolean existsByCargoAndDivisao(Cargo cargo, Divisao divisao);

    boolean existsByCargo(Cargo cargo);
}
