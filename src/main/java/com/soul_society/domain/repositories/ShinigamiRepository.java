package com.soul_society.domain.repositories;

import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface de porta de saída (output port) para persistência de Shinigamis
 * Define o contrato que deve ser implementado pela camada de infraestrutura
 */
public interface ShinigamiRepository {
    
    Shinigami save(Shinigami shinigami);
    
    Optional<Shinigami> findById(UUID id);
    
    List<Shinigami> findAll();
    
    List<Shinigami> findByDivisao(Divisao divisao);
    
    List<Shinigami> findByCargo(Cargo cargo);
    
    Optional<Shinigami> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    boolean existsByCargoAndDivisao(Cargo cargo, Divisao divisao);
    
    void deleteById(UUID id);
    
    boolean existsById(UUID id);

    boolean existsByCargo(Cargo cargo);
}
