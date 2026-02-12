package com.soul_society.application.config;

import com.soul_society.application.usecases.*;
import com.soul_society.domain.repositories.ShinigamiRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Component Scan → O Spring escaneia todo o pacote com.soul_society e encontra a classe UseCaseConfig anotada com @Configuration
Bean Registration → O Spring executa os métodos anotados com @Bean e registra as instâncias retornadas como beans no container
Dependency Injection → O Spring injeta automaticamente esses beans onde são necessários (no seu ShinigamiController)
*/
@Configuration
public class UseCaseConfig {

    @Bean
    public CreateShinigamiUseCase createShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        return new CreateShinigamiUseCase(shinigamiRepository);
    }

    @Bean
    public UpdateShinigamiUseCase updateShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        return new UpdateShinigamiUseCase(shinigamiRepository);
    }

    @Bean
    public FindShinigamiUseCase findShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        return new FindShinigamiUseCase(shinigamiRepository);
    }

    @Bean
    public DeleteShinigamiUseCase deleteShinigamiUseCase(ShinigamiRepository shinigamiRepository) {
        return new DeleteShinigamiUseCase(shinigamiRepository);
    }
}