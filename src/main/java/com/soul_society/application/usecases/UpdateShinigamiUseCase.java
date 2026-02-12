package com.soul_society.application.usecases;

import com.soul_society.application.exceptions.BusinessRuleException;
import com.soul_society.application.exceptions.ShinigamiNotFoundException;
import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.repositories.ShinigamiRepository;

import java.util.UUID;

/**
 * Caso de uso: Atualizar dados de um Shinigami
 * POJO puro - sem dependências de frameworks
 */
public class UpdateShinigamiUseCase {

    private final ShinigamiRepository shinigamiRepository;

    public UpdateShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        this.shinigamiRepository = shinigamiRepository;
    }

    public Shinigami execute(UUID id, Shinigami dadosAtualizados) {
        Shinigami shinigamiExistente = shinigamiRepository.findById(id)
                .orElseThrow(() -> new ShinigamiNotFoundException(
                        "Shinigami não encontrado com ID: " + id
                ));

        // Valida se o email está sendo alterado e se já existe
        if (!shinigamiExistente.getEmail().equals(dadosAtualizados.getEmail())) {
            if (shinigamiRepository.existsByEmail(dadosAtualizados.getEmail())) {
                throw new BusinessRuleException(
                        "Já existe um Shinigami com o email: " + dadosAtualizados.getEmail()
                );
            }
        }

        // Valida se o cargo está sendo alterado
        Cargo cargoAtual = shinigamiExistente.getCargo();
        Cargo cargoNovo = dadosAtualizados.getCargo();

        if (!cargoAtual.equals(cargoNovo)) {

            if (cargoNovo.isCargoUnicoGlobal()) {
                boolean existeOutroCapitaoComandante = shinigamiRepository.findByCargo(cargoNovo)
                        .stream()
                        .anyMatch(s -> !s.getId().equals(id));

                if (existeOutroCapitaoComandante) {
                    throw new BusinessRuleException(
                            "Já existe um " + cargoNovo.getDescricao() + " na Soul Society"
                    );
                }
            }
            else if (cargoNovo.isCargoUnico()) {
                boolean existeOutroNoMesmoCargo = shinigamiRepository
                        .findByDivisao(dadosAtualizados.getDivisao())
                        .stream()
                        .anyMatch(s -> !s.getId().equals(id) && s.getCargo() == cargoNovo);

                if (existeOutroNoMesmoCargo) {
                    throw new BusinessRuleException(
                            "Já existe um " + cargoNovo.getDescricao() +
                                    " na " + dadosAtualizados.getDivisao().getDescricao()
                    );
                }
            }
        }

        Shinigami shinigamiAtualizado = shinigamiExistente.atualizar(
                dadosAtualizados.getNome(),
                dadosAtualizados.getIdade(),
                dadosAtualizados.getSexo(),
                dadosAtualizados.getEmail(),
                dadosAtualizados.getDivisao(),
                dadosAtualizados.getCargo()
        );

        return shinigamiRepository.save(shinigamiAtualizado);
    }
}