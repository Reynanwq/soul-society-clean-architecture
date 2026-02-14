package com.soul_society.domain.entities

import java.util.UUID

/**
 * Entidade de domínio User (Rich Domain Model)
 * Imutável, com validações encapsuladas
 */
data class User(
    val id: UUID,
    val nome: String,
    val email: String,
    val senha: String // Já criptografada (BCrypt)
) {
    // Construtor secundário para criação (gera novo ID)
    constructor(nome: String, email: String, senha: String) : this(
        id = UUID.randomUUID(),
        nome = nome,
        email = email,
        senha = senha
    )

    init {
        validate()
    }

    private fun validate() {
        require(nome.isNotBlank()) { "Nome do usuário é obrigatório" }
        require(nome.length in 3..100) { "Nome deve ter entre 3 e 100 caracteres" }
        require(email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) { "Email inválido" }
        require(senha.isNotBlank()) { "Senha é obrigatória" }
    }

    /**
     * Atualiza os dados do usuário
     */
    fun atualizar(nome: String, email: String, senha: String): User {
        return User(this.id, nome, email, senha)
    }

    /**
     * Atualiza apenas a senha
     */
    fun atualizarSenha(novaSenha: String): User {
        return User(this.id, this.nome, this.email, novaSenha)
    }

    override fun toString(): String {
        return "User(id=$id, nome='$nome', email='$email')"
    }
}