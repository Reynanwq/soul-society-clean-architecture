package com.soul_society.presentation.dto.response;

import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO de resposta com dados do Shinigami
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de um Shinigami")
public class ShinigamiResponse {

    @Schema(description = "ID único do Shinigami", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Nome do Shinigami", example = "Byakuya Kuchiki")
    private String nome;

    @Schema(description = "Idade do Shinigami", example = "242")
    private Integer idade;

    @Schema(description = "Sexo do Shinigami", example = "MASCULINO")
    private Sexo sexo;

    @Schema(description = "Email do Shinigami", example = "byakuya@soulsociety.com")
    private String email;

    @Schema(description = "Divisão do Shinigami", example = "SEXTA")
    private Divisao divisao;

    @Schema(description = "Descrição da divisão", example = "6ª Divisão")
    private String divisaoDescricao;

    @Schema(description = "Cargo do Shinigami", example = "CAPITAO")
    private Cargo cargo;

    @Schema(description = "Descrição do cargo", example = "Capitão")
    private String cargoDescricao;
}
