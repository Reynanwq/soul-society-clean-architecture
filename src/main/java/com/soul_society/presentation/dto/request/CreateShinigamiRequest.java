package com.soul_society.presentation.dto.request;

import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.domain.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para criação de um novo Shinigami
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de um Shinigami")
public class CreateShinigamiRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Schema(description = "Nome do Shinigami", example = "Ichigo Kurosaki")
    private String nome;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 0, message = "Idade deve ser positiva")
    @Max(value = 10000, message = "Idade máxima é 10000 anos")
    @Schema(description = "Idade do Shinigami", example = "27")
    private Integer idade;

    @NotNull(message = "Sexo é obrigatório")
    @Schema(description = "Sexo do Shinigami", example = "MASCULINO")
    private Sexo sexo;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Schema(description = "Email do Shinigami", example = "ichigo@soulsociety.com")
    private String email;

    @NotNull(message = "Divisão é obrigatória")
    @Schema(description = "Divisão do Shinigami", example = "QUINTA")
    private Divisao divisao;

    @NotNull(message = "Cargo é obrigatório")
    @Schema(description = "Cargo do Shinigami", example = "CAPITAO")
    private Cargo cargo;
}
