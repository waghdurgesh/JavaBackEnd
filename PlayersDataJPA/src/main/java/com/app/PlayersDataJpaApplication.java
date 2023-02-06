package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayersDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayersDataJpaApplication.class, args);

	}

//	@Bean
//	public ModelMapper configureMapper() {
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		return modelMapper;
//	}

}
