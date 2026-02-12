package com.soul_society.domain.entities;

import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.enums.Sexo;
import lombok.Getter;

import java.util.UUID;

/**
 * Entidade de domínio Shinigami (Rich Domain Model)
 * Imutável, com validações e regras de negócio encapsuladas
 */
public class Shinigami {
    private final UUID id;
    private final String nome;
    private final Integer idade;
    private final Sexo sexo;
    private final String email;
    private final Divisao divisao;
    private final Cargo cargo;

    // Construtor para criação (gera novo ID)
    public Shinigami(String nome, Integer idade, Sexo sexo,
                     String email, Divisao divisao, Cargo cargo) {
        this(UUID.randomUUID(), nome, idade, sexo, email, divisao, cargo);
    }

    // Construtor para reconstrução (com ID existente - ex: do banco)
    public Shinigami(UUID id, String nome, Integer idade, Sexo sexo,
                     String email, Divisao divisao, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.divisao = divisao;
        this.cargo = cargo;
        validate();
    }

    private void validate() {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do Shinigami é obrigatório");
        }
        if (idade == null || idade < 0) {
            throw new IllegalArgumentException("Idade deve ser positiva");
        }
        if (sexo == null) {
            throw new IllegalArgumentException("Sexo é obrigatório");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (divisao == null) {
            throw new IllegalArgumentException("Divisão é obrigatória");
        }
        if (cargo == null) {
            throw new IllegalArgumentException("Cargo é obrigatório");
        }
        if (cargo == Cargo.CAPITAO_COMANDANTE && divisao != Divisao.PRIMEIRA) {
            throw new IllegalArgumentException(
                    "O Capitão-Comandante deve pertencer à 1ª Divisão"
            );
        }
    }

    public Shinigami atualizar(String nome, Integer idade, Sexo sexo,
                               String email, Divisao divisao, Cargo cargo) {
        return new Shinigami(this.id, nome, idade, sexo, email, divisao, cargo);
    }

    public Shinigami promover(Cargo novoCargo) {
        if (!novoCargo.isMaisAltoQue(this.cargo)) {
            throw new IllegalArgumentException(
                    "O novo cargo deve ser superior ao atual"
            );
        }
        return new Shinigami(this.id, this.nome, this.idade, this.sexo,
                this.email, this.divisao, novoCargo);
    }

    public Shinigami transferir(Divisao novaDivisao) {
        if (this.cargo == Cargo.CAPITAO_COMANDANTE && novaDivisao != Divisao.PRIMEIRA) {
            throw new IllegalArgumentException(
                    "O Capitão-Comandante não pode ser transferido da 1ª Divisão"
            );
        }
        return new Shinigami(this.id, this.nome, this.idade, this.sexo,
                this.email, novaDivisao, this.cargo);
    }

    @Override
    public String toString() {
        return "Shinigami{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo=" + sexo +
                ", email='" + email + '\'' +
                ", divisao=" + divisao +
                ", cargo=" + cargo +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public Divisao getDivisao() {
        return divisao;
    }

    public Cargo getCargo() {
        return cargo;
    }
}