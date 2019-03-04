package com.ongraph.CrudExample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class CrudExampleApplication {

	static void main(String[] args) {
		SpringApplication.run(CrudExampleApplication, args)
	}

}
