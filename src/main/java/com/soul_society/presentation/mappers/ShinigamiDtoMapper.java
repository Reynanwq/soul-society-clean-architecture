package com.soul_society.presentation.mappers;

import com.soul_society.domain.entities.Shinigami;
import com.soul_society.presentation.dto.request.CreateShinigamiRequest;
import com.soul_society.presentation.dto.response.ShinigamiResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper para conversão entre DTOs e entidades de domínio
 */
@Component
public class ShinigamiDtoMapper {

    /**
     * Converte DTO de request para entidade de domínio
     */
    public Shinigami toDomain(CreateShinigamiRequest request) {
        if (request == null) {
            return null;
        }

        return new Shinigami(
                request.getNome(),
                request.getIdade(),
                request.getSexo(),
                request.getEmail(),
                request.getDivisao(),
                request.getCargo()
        );
    }

    /**
     * Converte entidade de domínio para DTO de response
     */
    public ShinigamiResponse toResponse(Shinigami shinigami) {
        if (shinigami == null) {
            return null;
        }

        return ShinigamiResponse.builder()
                .id(shinigami.getId())
                .nome(shinigami.getNome())
                .idade(shinigami.getIdade())
                .sexo(shinigami.getSexo())
                .email(shinigami.getEmail())
                .divisao(shinigami.getDivisao())
                .divisaoDescricao(shinigami.getDivisao().getDescricao())
                .cargo(shinigami.getCargo())
                .cargoDescricao(shinigami.getCargo().getDescricao())
                .build();
    }
}
