package com.soul_society.presentation.controllers;

import com.soul_society.application.usecases.*;
import com.soul_society.domain.entities.Shinigami;
import com.soul_society.domain.enums.Cargo;
import com.soul_society.domain.enums.Divisao;
import com.soul_society.presentation.dto.request.CreateShinigamiRequest;
import com.soul_society.presentation.dto.response.ShinigamiResponse;
import com.soul_society.presentation.mappers.ShinigamiDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller REST para gerenciamento de Shinigamis
 */
@RestController
@RequestMapping("/api/v1/shinigamis")
@RequiredArgsConstructor
@Tag(name = "Shinigami", description = "Gerenciamento de Shinigamis da Soul Society")
public class ShinigamiController {

    private final CreateShinigamiUseCase createShinigamiUseCase;
    private final UpdateShinigamiUseCase updateShinigamiUseCase;
    private final FindShinigamiUseCase findShinigamiUseCase;
    private final DeleteShinigamiUseCase deleteShinigamiUseCase;
    private final ShinigamiDtoMapper mapper;

    @PostMapping
    @Operation(summary = "Criar novo Shinigami", description = "Registra um novo Shinigami na Soul Society")
    public ResponseEntity<ShinigamiResponse> create(@Valid @RequestBody CreateShinigamiRequest request) {
        Shinigami shinigami = mapper.toDomain(request);
        Shinigami created = createShinigamiUseCase.execute(shinigami);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @GetMapping
    @Operation(summary = "Listar todos os Shinigamis", description = "Retorna lista completa de Shinigamis")
    public ResponseEntity<List<ShinigamiResponse>> findAll() {
        List<ShinigamiResponse> response = findShinigamiUseCase.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Shinigami por ID", description = "Retorna um Shinigami específico")
    public ResponseEntity<ShinigamiResponse> findById(@PathVariable UUID id) {
        Shinigami shinigami = findShinigamiUseCase.findById(id);
        return ResponseEntity.ok(mapper.toResponse(shinigami));
    }

    @GetMapping("/divisao/{divisao}")
    @Operation(summary = "Buscar Shinigamis por Divisão", description = "Retorna todos os Shinigamis de uma divisão")
    public ResponseEntity<List<ShinigamiResponse>> findByDivisao(@PathVariable Divisao divisao) {
        List<ShinigamiResponse> response = findShinigamiUseCase.findByDivisao(divisao).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cargo/{cargo}")
    @Operation(summary = "Buscar Shinigamis por Cargo", description = "Retorna todos os Shinigamis de um cargo")
    public ResponseEntity<List<ShinigamiResponse>> findByCargo(@PathVariable Cargo cargo) {
        List<ShinigamiResponse> response = findShinigamiUseCase.findByCargo(cargo).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar Shinigami por Email", description = "Retorna um Shinigami pelo email")
    public ResponseEntity<ShinigamiResponse> findByEmail(@PathVariable String email) {
        Shinigami shinigami = findShinigamiUseCase.findByEmail(email);
        return ResponseEntity.ok(mapper.toResponse(shinigami));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Shinigami", description = "Atualiza os dados de um Shinigami")
    public ResponseEntity<ShinigamiResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody CreateShinigamiRequest request) {
        Shinigami shinigami = mapper.toDomain(request);
        Shinigami updated = updateShinigamiUseCase.execute(id, shinigami);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Shinigami", description = "Remove um Shinigami da Soul Society")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteShinigamiUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
