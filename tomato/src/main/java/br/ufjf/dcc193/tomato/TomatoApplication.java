package br.ufjf.dcc193.tomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TomatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TomatoApplication.class, args);
		System.out.println("Deu certo!");
	}

}
