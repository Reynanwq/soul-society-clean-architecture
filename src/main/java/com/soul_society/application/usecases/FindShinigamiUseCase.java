package com.soul_society.application.usecases;

import com.soul_society.application.exceptions.ShinigamiNotFoundException;
import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.repositories.ShinigamiRepository;

import java.util.List;
import java.util.UUID;

/**
 * Casos de uso de consulta de Shinigamis
 * POJO puro - sem dependências de frameworks
 */
public class FindShinigamiUseCase {

    private final ShinigamiRepository shinigamiRepository;

    public FindShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        this.shinigamiRepository = shinigamiRepository;
    }

    public Shinigami findById(UUID id) {
        return shinigamiRepository.findById(id)
                .orElseThrow(() -> new ShinigamiNotFoundException(
                        "Shinigami não encontrado com ID: " + id
                ));
    }

    public List<Shinigami> findAll() {
        return shinigamiRepository.findAll();
    }

    public List<Shinigami> findByDivisao(Divisao divisao) {
        return shinigamiRepository.findByDivisao(divisao);
    }

    public List<Shinigami> findByCargo(Cargo cargo) {
        return shinigamiRepository.findByCargo(cargo);
    }

    public Shinigami findByEmail(String email) {
        return shinigamiRepository.findByEmail(email)
                .orElseThrow(() -> new ShinigamiNotFoundException(
                        "Shinigami não encontrado com email: " + email
                ));
    }
}