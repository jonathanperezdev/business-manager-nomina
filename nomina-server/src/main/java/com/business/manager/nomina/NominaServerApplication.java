package com.business.manager.nomina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.business.manager.nomina"})
@EntityScan(basePackages = {"com.business.manager.nomina.daos"})
@EnableDiscoveryClient
@RefreshScope
public class NominaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(NominaServerApplication.class, args);
	}
}
