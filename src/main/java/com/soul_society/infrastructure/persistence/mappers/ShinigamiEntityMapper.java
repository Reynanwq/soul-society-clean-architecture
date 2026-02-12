package com.soul_society.infrastructure.persistence.mappers;

import com.soul_society.domain.entities.Shinigami;
import com.soul_society.infrastructure.persistence.entities.ShinigamiEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper para conversão entre entidade de domínio e entidade JPA
 * Agora alinhado com a entidade imutável
 */
@Component
public class ShinigamiEntityMapper {

    /**
     * Converte entidade de domínio para entidade JPA
     */
    public ShinigamiEntity toEntity(Shinigami shinigami) {
        if (shinigami == null) {
            return null;
        }

        // Usando o builder da entidade JPA (persistência)
        return ShinigamiEntity.builder()
                .id(shinigami.getId())
                .nome(shinigami.getNome())
                .idade(shinigami.getIdade())
                .sexo(shinigami.getSexo())
                .email(shinigami.getEmail())
                .divisao(shinigami.getDivisao())
                .cargo(shinigami.getCargo())
                .build();
    }

    /**
     * Converte entidade JPA para entidade de domínio
     * AGORA: usa o construtor com ID (reconstrução)
     */
    public Shinigami toDomain(ShinigamiEntity entity) {
        if (entity == null) {
            return null;
        }

        // ✅ Correção: usa o construtor de reconstrução com ID
        return new Shinigami(
                entity.getId(),        // ID existente do banco
                entity.getNome(),
                entity.getIdade(),
                entity.getSexo(),
                entity.getEmail(),
                entity.getDivisao(),
                entity.getCargo()
        );
    }
}