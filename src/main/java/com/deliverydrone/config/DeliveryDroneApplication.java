package com.deliverydrone.config;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({ "com.deliverydrone" })
@EnableJpaRepositories("com.deliverydrone.repository")
@EntityScan("com.deliverydrone.model")
public class DeliveryDroneApplication {

  public static void main(String[] args) {
	SpringApplication.run(DeliveryDroneApplication.class, args);
  }

  @Bean
  public DozerBeanMapper dozerBeanMapper() {
	return new DozerBeanMapper();
  }

}
