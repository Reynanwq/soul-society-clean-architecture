package com.soul_society.application.usecases;

import com.soul_society.application.exceptions.BusinessRuleException;
import com.soul_society.application.exceptions.ShinigamiNotFoundException;
import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.repositories.ShinigamiRepository;

import java.util.UUID;

/**
 * Caso de uso: Deletar um Shinigami
 * POJO puro - sem dependências de frameworks
 */
public class DeleteShinigamiUseCase {

    private final ShinigamiRepository shinigamiRepository;

    public DeleteShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        this.shinigamiRepository = shinigamiRepository;
    }

    public void execute(UUID id) {
        Shinigami shinigami = shinigamiRepository.findById(id)
                .orElseThrow(() -> new ShinigamiNotFoundException(
                        "Shinigami não encontrado com ID: " + id
                ));

        if (shinigami.getCargo().isCargoUnicoGlobal() || shinigami.getCargo().isCargoUnico()) {
            throw new BusinessRuleException(
                    "O " + shinigami.getCargo().getDescricao() +
                            " não pode ser removido da Soul Society"
            );
        }

        shinigamiRepository.deleteById(id);
    }
}