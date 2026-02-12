package com.soul_society.infrastructure.persistence.entities;

import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.enums.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Entidade JPA para persistência de Shinigami
 * Representa a tabela no banco de dados
 */
@Entity
@Table(name = "shinigamis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShinigamiEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "divisao", nullable = false)
    private Divisao divisao;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private Cargo cargo;
}
