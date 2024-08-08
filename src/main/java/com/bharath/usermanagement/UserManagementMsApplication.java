package com.bharath.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class UserManagementMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMsApplication.class, args);
	}

}
