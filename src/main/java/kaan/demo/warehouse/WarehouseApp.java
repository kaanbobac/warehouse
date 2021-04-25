package kaan.demo.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.java.Log;
@Log
@SpringBootApplication
public class WarehouseApp {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApp.class, args);
		log.info("Application has been started");
	}
}
