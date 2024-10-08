package com.pecodigos.gestao_vagas.modules.candidates.useCases;

import com.pecodigos.gestao_vagas.exceptions.UserNotFoundException;
import com.pecodigos.gestao_vagas.modules.candidates.repositories.CandidateRepository;
import com.pecodigos.gestao_vagas.modules.candidates.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);
        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }
}
