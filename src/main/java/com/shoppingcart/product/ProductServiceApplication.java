package com.shoppingcart.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Habilita el escaneo de componentes, autoconfiguración y configuración de Spring Boot
@SpringBootApplication
// Activa los clientes Feign para consumir APIs externas (FakeStoreClient)
@EnableFeignClients
public class ProductServiceApplication {

	// Punto de entrada de la aplicación
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
