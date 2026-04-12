package br.com.fiap.m8music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class M8musicApplication {

	public static void main(String[] args) {
		SpringApplication.run(M8musicApplication.class, args);
	}

}
