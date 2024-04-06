package com.patika.kredinbizdeservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "KredinBizde API", version = "1.0", description = "KredinBizde Information", contact= @Contact(name = "Ipek Cil", url = "https://ipekcil.com", email = "ipekcill94@gmail.com")))
public class  KredinbizdeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KredinbizdeServiceApplication.class, args);
	}

}
