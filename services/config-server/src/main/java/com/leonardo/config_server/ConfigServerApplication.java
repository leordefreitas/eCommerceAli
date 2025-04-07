package com.leonardo.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
// adicionado primeiramente para conseguir fazer deste projeto um
// server
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
