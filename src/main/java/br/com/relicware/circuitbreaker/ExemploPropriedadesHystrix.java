package br.com.relicware.circuitbreaker;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ExemploPropriedadesHystrix {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "4000"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "15000")
            })
    public int dividir(final int index, final int valor) throws InterruptedException {
    	System.err.println(index + " - " + LocalTime.now().format(formatter) + " - início do metodo");
        TimeUnit.SECONDS.sleep(1);
        final int resultado = 10 / index;
        if (index == 1 || index == 3 || index == 5 || index == 7)
        	throw new InterruptedException("InterruptedException");
        
        System.err.println("Fim do metodo!");
        return resultado;
    }


    public int fallback(final int index, final int valor) {
        System.err.println(index + " - " + LocalTime.now().format(formatter) + " - fallback ...");
        return 0;
    }

}