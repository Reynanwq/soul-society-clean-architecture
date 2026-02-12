package com.soul_society.domain.entities;

import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.enums.Sexo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para a entidade Shinigami
 * Testa as regras de negócio do domínio
 */
@DisplayName("Testes da Entidade Shinigami")
class ShinigamiTest {

    // ========================================
    // TESTES DE CRIAÇÃO
    // ========================================

    @Test
    @DisplayName("Deve criar Shinigami com sucesso")
    void deveCriarShinigamiComSucesso() {
        // Arrange & Act
        Shinigami shinigami = new Shinigami(
                "Byakuya Kuchiki",
                242,
                Sexo.MASCULINO,
                "byakuya@soulsociety.com",
                Divisao.SEXTA,
                Cargo.CAPITAO
        );

        // Assert
        assertThat(shinigami).isNotNull();
        assertThat(shinigami.getId()).isNotNull();
        assertThat(shinigami.getNome()).isEqualTo("Byakuya Kuchiki");
        assertThat(shinigami.getIdade()).isEqualTo(242);
        assertThat(shinigami.getSexo()).isEqualTo(Sexo.MASCULINO);
        assertThat(shinigami.getEmail()).isEqualTo("byakuya@soulsociety.com");
        assertThat(shinigami.getDivisao()).isEqualTo(Divisao.SEXTA);
        assertThat(shinigami.getCargo()).isEqualTo(Cargo.CAPITAO);
    }

    @Test
    @DisplayName("Deve criar Shinigami com ID específico (reconstrução)")
    void deveCriarShinigamiComIdEspecifico() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        Shinigami shinigami = new Shinigami(
                id,
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Assert
        assertThat(shinigami.getId()).isEqualTo(id);
    }

    // ========================================
    // TESTES DE VALIDAÇÃO - NOME
    // ========================================

    @Test
    @DisplayName("Deve lançar exceção quando nome for nulo")
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                null,  // ← Nome nulo
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome do Shinigami é obrigatório");
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for vazio")
    void deveLancarExcecaoQuandoNomeForVazio() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "",  // ← Nome vazio
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome do Shinigami é obrigatório");
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for apenas espaços")
    void deveLancarExcecaoQuandoNomeForApenasEspacos() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "   ",  // ← Apenas espaços
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome do Shinigami é obrigatório");
    }

    // ========================================
    // TESTES DE VALIDAÇÃO - IDADE
    // ========================================

    @Test
    @DisplayName("Deve lançar exceção quando idade for nula")
    void deveLancarExcecaoQuandoIdadeForNula() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                null,  // ← Idade nula
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Idade deve ser positiva");
    }

    @Test
    @DisplayName("Deve lançar exceção quando idade for negativa")
    void deveLancarExcecaoQuandoIdadeForNegativa() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                -1,  // ← Idade negativa
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Idade deve ser positiva");
    }

    @Test
    @DisplayName("Deve aceitar idade zero")
    void deveAceitarIdadeZero() {
        // Act
        Shinigami shinigami = new Shinigami(
                "Shinigami Recém-nascido",
                0,  // ← Idade zero
                Sexo.MASCULINO,
                "recem.nascido@soulsociety.com",
                Divisao.QUARTA,
                Cargo.SHINIGAMI_COMUM
        );

        // Assert
        assertThat(shinigami.getIdade()).isZero();
    }

    // ========================================
    // TESTES DE VALIDAÇÃO - EMAIL
    // ========================================

    @Test
    @DisplayName("Deve lançar exceção quando email for nulo")
    void deveLancarExcecaoQuandoEmailForNulo() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                null,  // ← Email nulo
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email inválido");
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for inválido")
    void deveLancarExcecaoQuandoEmailForInvalido() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "email-invalido",  // ← Email sem @
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email inválido");
    }

    @Test
    @DisplayName("Deve aceitar email válido")
    void deveAceitarEmailValido() {
        // Act
        Shinigami shinigami = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji.abarai@soulsociety.com",  // ← Email válido
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Assert
        assertThat(shinigami.getEmail()).isEqualTo("renji.abarai@soulsociety.com");
    }

    // ========================================
    // TESTES DE VALIDAÇÃO - SEXO
    // ========================================

    @Test
    @DisplayName("Deve lançar exceção quando sexo for nulo")
    void deveLancarExcecaoQuandoSexoForNulo() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                150,
                null,  // ← Sexo nulo
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sexo é obrigatório");
    }

    // ========================================
    // TESTES DE VALIDAÇÃO - DIVISÃO
    // ========================================

    @Test
    @DisplayName("Deve lançar exceção quando divisão for nula")
    void deveLancarExcecaoQuandoDivisaoForNula() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                null,  // ← Divisão nula
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Divisão é obrigatória");
    }

    // ========================================
    // TESTES DE VALIDAÇÃO - CARGO
    // ========================================

    @Test
    @DisplayName("Deve lançar exceção quando cargo for nulo")
    void deveLancarExcecaoQuandoCargoForNulo() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                null  // ← Cargo nulo
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cargo é obrigatório");
    }

    // ========================================
    // TESTES DE REGRA DE NEGÓCIO - CAPITÃO-COMANDANTE
    // ========================================

    @Test
    @DisplayName("Deve permitir Capitão-Comandante na 1ª Divisão")
    void devePermitirCapitaoComandanteNaPrimeiraDivisao() {
        // Act
        Shinigami shinigami = new Shinigami(
                "Genryusai Yamamoto",
                2100,
                Sexo.MASCULINO,
                "yamamoto@soulsociety.com",
                Divisao.PRIMEIRA,  // ← 1ª Divisão
                Cargo.CAPITAO_COMANDANTE
        );

        // Assert
        assertThat(shinigami.getCargo()).isEqualTo(Cargo.CAPITAO_COMANDANTE);
        assertThat(shinigami.getDivisao()).isEqualTo(Divisao.PRIMEIRA);
    }

    @Test
    @DisplayName("Deve lançar exceção quando Capitão-Comandante não estiver na 1ª Divisão")
    void deveLancarExcecaoQuandoCapitaoComandanteNaoEstiverNaPrimeiraDivisao() {
        // Act & Assert
        assertThatThrownBy(() -> new Shinigami(
                "Fake Yamamoto",
                2000,
                Sexo.MASCULINO,
                "fake@soulsociety.com",
                Divisao.SEGUNDA,  // ← Divisão errada!
                Cargo.CAPITAO_COMANDANTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O Capitão-Comandante deve pertencer à 1ª Divisão");
    }

    // ========================================
    // TESTES DO MÉTODO ATUALIZAR
    // ========================================

    @Test
    @DisplayName("Deve atualizar Shinigami mantendo o ID")
    void deveAtualizarShinigamiMantendoId() {
        // Arrange
        Shinigami original = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );
        UUID idOriginal = original.getId();

        // Act
        Shinigami atualizado = original.atualizar(
                "Renji Abarai",
                151,  // ← Idade atualizada
                Sexo.MASCULINO,
                "renji.novo@soulsociety.com",  // ← Email atualizado
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Assert
        assertThat(atualizado.getId()).isEqualTo(idOriginal);  // ID mantido
        assertThat(atualizado.getIdade()).isEqualTo(151);
        assertThat(atualizado.getEmail()).isEqualTo("renji.novo@soulsociety.com");
    }

    @Test
    @DisplayName("Deve validar dados ao atualizar")
    void deveValidarDadosAoAtualizar() {
        // Arrange
        Shinigami original = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Act & Assert
        assertThatThrownBy(() -> original.atualizar(
                "",  // ← Nome inválido
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome do Shinigami é obrigatório");
    }

    // ========================================
    // TESTES DO MÉTODO PROMOVER
    // ========================================

    @Test
    @DisplayName("Deve promover Shinigami para cargo superior")
    void devePromoverShinigamiParaCargoSuperior() {
        // Arrange
        Shinigami shinigami = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TERCEIRO_ASSENTO
        );

        // Act
        Shinigami promovido = shinigami.promover(Cargo.TENENTE);

        // Assert
        assertThat(promovido.getCargo()).isEqualTo(Cargo.TENENTE);
        assertThat(promovido.getId()).isEqualTo(shinigami.getId());  // ID mantido
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar promover para cargo inferior")
    void deveLancarExcecaoAoTentarPromoverParaCargoInferior() {
        // Arrange
        Shinigami shinigami = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Act & Assert
        assertThatThrownBy(() -> shinigami.promover(Cargo.TERCEIRO_ASSENTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O novo cargo deve ser superior ao atual");
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar promover para o mesmo cargo")
    void deveLancarExcecaoAoTentarPromoverParaMesmoCargo() {
        // Arrange
        Shinigami shinigami = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Act & Assert
        assertThatThrownBy(() -> shinigami.promover(Cargo.TENENTE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O novo cargo deve ser superior ao atual");
    }

    // ========================================
    // TESTES DO MÉTODO TRANSFERIR
    // ========================================

    @Test
    @DisplayName("Deve transferir Shinigami para outra divisão")
    void deveTransferirShinigamiParaOutraDivisao() {
        // Arrange
        Shinigami shinigami = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Act
        Shinigami transferido = shinigami.transferir(Divisao.QUINTA);

        // Assert
        assertThat(transferido.getDivisao()).isEqualTo(Divisao.QUINTA);
        assertThat(transferido.getId()).isEqualTo(shinigami.getId());  // ID mantido
        assertThat(transferido.getCargo()).isEqualTo(Cargo.TENENTE);  // Cargo mantido
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar transferir Capitão-Comandante da 1ª Divisão")
    void deveLancarExcecaoAoTentarTransferirCapitaoComandante() {
        // Arrange
        Shinigami capitaoComandante = new Shinigami(
                "Genryusai Yamamoto",
                2100,
                Sexo.MASCULINO,
                "yamamoto@soulsociety.com",
                Divisao.PRIMEIRA,
                Cargo.CAPITAO_COMANDANTE
        );

        // Act & Assert
        assertThatThrownBy(() -> capitaoComandante.transferir(Divisao.SEGUNDA))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O Capitão-Comandante não pode ser transferido da 1ª Divisão");
    }

    @Test
    @DisplayName("Deve permitir transferir Capitão-Comandante para a própria 1ª Divisão")
    void devePermitirTransferirCapitaoComandanteParaPrimeiraDivisao() {
        // Arrange
        Shinigami capitaoComandante = new Shinigami(
                "Genryusai Yamamoto",
                2100,
                Sexo.MASCULINO,
                "yamamoto@soulsociety.com",
                Divisao.PRIMEIRA,
                Cargo.CAPITAO_COMANDANTE
        );

        // Act
        Shinigami transferido = capitaoComandante.transferir(Divisao.PRIMEIRA);

        // Assert
        assertThat(transferido.getDivisao()).isEqualTo(Divisao.PRIMEIRA);
    }

    // ========================================
    // TESTES DE IMUTABILIDADE
    // ========================================

    @Test
    @DisplayName("Deve garantir imutabilidade - atualizar cria nova instância")
    void deveGarantirImutabilidadeAtualizar() {
        // Arrange
        Shinigami original = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Act
        Shinigami atualizado = original.atualizar(
                "Renji Abarai Updated",
                151,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Assert
        assertThat(original).isNotSameAs(atualizado);  // Instâncias diferentes
        assertThat(original.getNome()).isEqualTo("Renji Abarai");  // Original não mudou
        assertThat(atualizado.getNome()).isEqualTo("Renji Abarai Updated");
    }

    @Test
    @DisplayName("Deve garantir imutabilidade - promover cria nova instância")
    void deveGarantirImutabilidadePromover() {
        // Arrange
        Shinigami original = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TERCEIRO_ASSENTO
        );

        // Act
        Shinigami promovido = original.promover(Cargo.TENENTE);

        // Assert
        assertThat(original).isNotSameAs(promovido);
        assertThat(original.getCargo()).isEqualTo(Cargo.TERCEIRO_ASSENTO);  // Original não mudou
        assertThat(promovido.getCargo()).isEqualTo(Cargo.TENENTE);
    }

    @Test
    @DisplayName("Deve garantir imutabilidade - transferir cria nova instância")
    void deveGarantirImutabilidadeTransferir() {
        // Arrange
        Shinigami original = new Shinigami(
                "Renji Abarai",
                150,
                Sexo.MASCULINO,
                "renji@soulsociety.com",
                Divisao.SEXTA,
                Cargo.TENENTE
        );

        // Act
        Shinigami transferido = original.transferir(Divisao.QUINTA);

        // Assert
        assertThat(original).isNotSameAs(transferido);
        assertThat(original.getDivisao()).isEqualTo(Divisao.SEXTA);  // Original não mudou
        assertThat(transferido.getDivisao()).isEqualTo(Divisao.QUINTA);
    }
}