package com.fei.projetodecantadorbe;

import com.fei.projetodecantadorbe.service.componentes.services.SimuladorService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class ProjetoDecantadorBeApplication {


	@Autowired
	private SimuladorService simuladorService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoDecantadorBeApplication.class, args);
	}

	@Bean
	public void sendMessage(){
		this.simuladorService.updateMain();
	}

}
