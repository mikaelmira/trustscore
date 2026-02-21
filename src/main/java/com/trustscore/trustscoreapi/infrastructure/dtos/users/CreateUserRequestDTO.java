package com.trustscore.trustscoreapi.infrastructure.dtos.users;

import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;

public record CreateUserRequestDTO(
         String name,
         String email,
         Cpf cpf,
         String password,
         String profilePicture
) {
}
