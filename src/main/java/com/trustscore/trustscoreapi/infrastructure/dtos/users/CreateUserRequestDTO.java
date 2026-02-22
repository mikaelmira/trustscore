package com.trustscore.trustscoreapi.infrastructure.dtos.users;

public record CreateUserRequestDTO(
         String name,
         String email,
         String cpf,
         String password,
         String profilePicture
) {
}
