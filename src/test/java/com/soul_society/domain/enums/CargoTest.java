package com.soul_society.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para o enum Cargo
 */
@DisplayName("Testes do Enum Cargo")
class CargoTest {

    // ========================================
    // TESTES DO MÉTODO isMaisAltoQue
    // ========================================

    @Test
    @DisplayName("Capitão-Comandante deve ser mais alto que Capitão")
    void capitaoComandanteDeveSerMaisAltoQueCapitao() {
        // Act
        boolean resultado = Cargo.CAPITAO_COMANDANTE.isMaisAltoQue(Cargo.CAPITAO);

        // Assert
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Capitão deve ser mais alto que Tenente")
    void capitaoDeveSerMaisAltoQueTenente() {
        // Act
        boolean resultado = Cargo.CAPITAO.isMaisAltoQue(Cargo.TENENTE);

        // Assert
        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Tenente NÃO deve ser mais alto que Capitão")
    void tenenteNaoDeveSerMaisAltoQueCapitao() {
        // Act
        boolean resultado = Cargo.TENENTE.isMaisAltoQue(Cargo.CAPITAO);

        // Assert
        assertThat(resultado).isFalse();
    }

    @Test
    @DisplayName("Cargo NÃO deve ser mais alto que ele mesmo")
    void cargoNaoDeveSerMaisAltoQueEleMesmo() {
        // Act
        boolean resultado = Cargo.CAPITAO.isMaisAltoQue(Cargo.CAPITAO);

        // Assert
        assertThat(resultado).isFalse();
    }

    @Test
    @DisplayName("Shinigami Comum NÃO deve ser mais alto que nenhum cargo")
    void shinigamiComumNaoDeveSerMaisAltoQueNenhumCargo() {
        // Assert
        assertThat(Cargo.SHINIGAMI_COMUM.isMaisAltoQue(Cargo.VIGESIMO_ASSENTO)).isFalse();
        assertThat(Cargo.SHINIGAMI_COMUM.isMaisAltoQue(Cargo.TENENTE)).isFalse();
        assertThat(Cargo.SHINIGAMI_COMUM.isMaisAltoQue(Cargo.CAPITAO)).isFalse();
    }

    // ========================================
    // TESTES DO MÉTODO isCargoUnico
    // ========================================

    @Test
    @DisplayName("Capitão-Comandante deve ser cargo único")
    void capitaoComandanteDeveSerCargoUnico() {
        // Act & Assert
        assertThat(Cargo.CAPITAO_COMANDANTE.isCargoUnico()).isTrue();
    }

    @Test
    @DisplayName("Capitão deve ser cargo único")
    void capitaoDeveSerCargoUnico() {
        // Act & Assert
        assertThat(Cargo.CAPITAO.isCargoUnico()).isTrue();
    }

    @Test
    @DisplayName("Tenente deve ser cargo único")
    void tenenteDeveSerCargoUnico() {
        // Act & Assert
        assertThat(Cargo.TENENTE.isCargoUnico()).isTrue();
    }

    @Test
    @DisplayName("3º Assento deve ser cargo único")
    void terceiroAssentoDeveSerCargoUnico() {
        // Act & Assert
        assertThat(Cargo.TERCEIRO_ASSENTO.isCargoUnico()).isTrue();
    }

    @Test
    @DisplayName("Shinigami Comum NÃO deve ser cargo único")
    void shinigamiComumNaoDeveSerCargoUnico() {
        // Act & Assert
        assertThat(Cargo.SHINIGAMI_COMUM.isCargoUnico()).isFalse();
    }

    // ========================================
    // TESTES DO MÉTODO isCargoUnicoGlobal
    // ========================================

    @Test
    @DisplayName("Capitão-Comandante deve ser cargo único global")
    void capitaoComandanteDeveSerCargoUnicoGlobal() {
        // Act & Assert
        assertThat(Cargo.CAPITAO_COMANDANTE.isCargoUnicoGlobal()).isTrue();
    }

    @Test
    @DisplayName("Capitão NÃO deve ser cargo único global")
    void capitaoNaoDeveSerCargoUnicoGlobal() {
        // Act & Assert
        assertThat(Cargo.CAPITAO.isCargoUnicoGlobal()).isFalse();
    }

    @Test
    @DisplayName("Shinigami Comum NÃO deve ser cargo único global")
    void shinigamiComumNaoDeveSerCargoUnicoGlobal() {
        // Act & Assert
        assertThat(Cargo.SHINIGAMI_COMUM.isCargoUnicoGlobal()).isFalse();
    }

    // ========================================
    // TESTES DE HIERARQUIA
    // ========================================

    @Test
    @DisplayName("Deve verificar hierarquia correta de todos os cargos")
    void deveVerificarHierarquiaCorreta() {
        // Assert - Capitão-Comandante é o mais alto
        assertThat(Cargo.CAPITAO_COMANDANTE.getHierarquia()).isEqualTo(1);

        // Assert - Capitão é menor que Capitão-Comandante
        assertThat(Cargo.CAPITAO.getHierarquia()).isGreaterThan(Cargo.CAPITAO_COMANDANTE.getHierarquia());

        // Assert - Tenente é menor que Capitão
        assertThat(Cargo.TENENTE.getHierarquia()).isGreaterThan(Cargo.CAPITAO.getHierarquia());

        // Assert - Shinigami Comum é o mais baixo
        assertThat(Cargo.SHINIGAMI_COMUM.getHierarquia()).isEqualTo(22);
    }

    // ========================================
    // TESTES DE DESCRIÇÃO
    // ========================================

    @Test
    @DisplayName("Deve retornar descrição correta para cada cargo")
    void deveRetornarDescricaoCorreta() {
        // Assert
        assertThat(Cargo.CAPITAO_COMANDANTE.getDescricao()).isEqualTo("Capitão-Comandante");
        assertThat(Cargo.CAPITAO.getDescricao()).isEqualTo("Capitão");
        assertThat(Cargo.TENENTE.getDescricao()).isEqualTo("Tenente");
        assertThat(Cargo.TERCEIRO_ASSENTO.getDescricao()).isEqualTo("3º Assento");
        assertThat(Cargo.SHINIGAMI_COMUM.getDescricao()).isEqualTo("Shinigami Comum");
    }
}