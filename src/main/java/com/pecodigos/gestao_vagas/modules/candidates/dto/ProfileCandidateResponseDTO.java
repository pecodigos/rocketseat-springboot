package com.pecodigos.gestao_vagas.modules.candidates.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "pecodigos")
    private String username;

    @Schema(example = "pecodigos@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "Pedro Henrique")
    private String name;
}
