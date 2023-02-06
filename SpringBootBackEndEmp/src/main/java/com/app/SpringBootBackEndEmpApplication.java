package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootBackEndEmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackEndEmpApplication.class, args);
	}

	//method to config ModelMapper as a spring bean
	@Bean //same like <bean id ....> tah=g in xml file
	public ModelMapper configureMapper() {
		System.out.println("****in Config Mapper****");
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper; // return instance to spring container
	}
}
