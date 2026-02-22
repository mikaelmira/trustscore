package com.trustscore.trustscoreapi.infrastructure.presentation;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.usecases.user.CreateUserUseCase;
import com.trustscore.trustscoreapi.infrastructure.dtos.users.CreateUserRequestDTO;
import com.trustscore.trustscoreapi.infrastructure.dtos.users.CreateUserResponseDTO;
import com.trustscore.trustscoreapi.infrastructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;
    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO dto) {
        User user = mapper.toDomain(dto);
        User createdUser = createUserUseCase.execute(user);
        CreateUserResponseDTO response = new CreateUserResponseDTO(createdUser.getName(), createdUser.getEmail().value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
