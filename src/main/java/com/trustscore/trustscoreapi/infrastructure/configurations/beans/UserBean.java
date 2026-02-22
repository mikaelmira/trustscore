package com.trustscore.trustscoreapi.infrastructure.configurations.beans;

import com.trustscore.trustscoreapi.domain.gateway.UserGateway;
import com.trustscore.trustscoreapi.domain.usecases.user.CreateUserUseCase;
import com.trustscore.trustscoreapi.domain.usecases.user.CreateUserUseCaseImpl;
import com.trustscore.trustscoreapi.domain.utils.CpfHasher;
import com.trustscore.trustscoreapi.domain.utils.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBean {

    @Bean
    public CreateUserUseCase createUser(UserGateway gateway, PasswordHasher passwordHasher, CpfHasher cpfHasher) {
        return new CreateUserUseCaseImpl(gateway, passwordHasher, cpfHasher);
    }

}
