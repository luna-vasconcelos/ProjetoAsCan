package com.ascan.ascanflixapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // dá start em todas as configurações que o spring precisa pra iniciar o servidor
public class AscanflixAPI {
	// comando main que efetivamente starta a aplicação
	public static void main(String[] args) {

		SpringApplication.run(AscanflixAPI.class, args);
	}

}
