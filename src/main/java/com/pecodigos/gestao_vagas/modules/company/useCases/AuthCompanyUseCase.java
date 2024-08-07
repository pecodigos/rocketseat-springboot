package com.pecodigos.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pecodigos.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.pecodigos.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Incorrect username/password.");
                });

        // Verifying password
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // If not matches
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        // If matches
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }
}
