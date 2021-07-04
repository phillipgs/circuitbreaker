package br.com.relicware.circuitbreaker;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoHystrix { 

	@Autowired
	ExemploPropriedadesHystrix exemploPropriedadesHystrix;

	public void chamadaMetodo() throws Exception {
		for (int i = 0; i < 10; i++) {
			exemploPropriedadesHystrix.dividir(i, 0);
			if (i == 5 || i == 8) {
				TimeUnit.SECONDS.sleep(5);
			}
		}
	}
}
