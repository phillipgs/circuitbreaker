package br.com.relicware.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class CircuitbreakerApplication implements CommandLineRunner {
	
	@Autowired
	ServicoHystrix hystrix;

	public static void main(String[] args) {
		SpringApplication.run(CircuitbreakerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		hystrix.chamadaMetodo();
	}

}
