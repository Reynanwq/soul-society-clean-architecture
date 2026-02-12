package com.soul_society.application.usecases;

import com.soul_society.application.exceptions.BusinessRuleException;
import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.repositories.ShinigamiRepository;

/**
 * Caso de uso: Criar um novo Shinigami
 * POJO puro - sem dependências de frameworks
 */
public class CreateShinigamiUseCase {

    private final ShinigamiRepository shinigamiRepository;

    public CreateShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        this.shinigamiRepository = shinigamiRepository;
    }

    public Shinigami execute(Shinigami shinigami) {
        // Valida regra de negócio: email único
        if (shinigamiRepository.existsByEmail(shinigami.getEmail())) {
            throw new BusinessRuleException(
                    "Já existe um Shinigami com o email: " + shinigami.getEmail()
            );
        }

        Cargo cargo = shinigami.getCargo();

        if (cargo.isCargoUnicoGlobal()) {
            if (shinigamiRepository.existsByCargo(cargo)) {
                throw new BusinessRuleException(
                        "Já existe um " + cargo.getDescricao() + " na Soul Society"
                );
            }
        }

        // Outros cargos (exceto Shinigami Comum): únicos por divisão
        else if (cargo.isCargoUnico()) {
            if (shinigamiRepository.existsByCargoAndDivisao(cargo, shinigami.getDivisao())) {
                throw new BusinessRuleException(
                        "Já existe um " + cargo.getDescricao() +
                                " na " + shinigami.getDivisao().getDescricao()
                );
            }
        }

        return shinigamiRepository.save(shinigami);
    }
}