package com.soul_society.infrastructure.persistence.repositories;

import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.repositories.ShinigamiRepository;
import com.soul_society.infrastructure.persistence.mappers.ShinigamiEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de Shinigami usando JPA
 * Adapta a interface do domínio para a infraestrutura JPA
 */
@Component
@RequiredArgsConstructor
public class ShinigamiRepositoryImpl implements ShinigamiRepository {

    private final ShinigamiJpaRepository jpaRepository;
    private final ShinigamiEntityMapper mapper;

    @Override
    public Shinigami save(Shinigami shinigami) {
        var entity = mapper.toEntity(shinigami);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Shinigami> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Shinigami> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shinigami> findByDivisao(Divisao divisao) {
        return jpaRepository.findByDivisao(divisao).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shinigami> findByCargo(Cargo cargo) {
        return jpaRepository.findByCargo(cargo).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Shinigami> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByCargoAndDivisao(Cargo cargo, Divisao divisao) {
        return jpaRepository.existsByCargoAndDivisao(cargo, divisao);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByCargo(Cargo cargo) {
        return jpaRepository.existsByCargo(cargo);
    }
}
