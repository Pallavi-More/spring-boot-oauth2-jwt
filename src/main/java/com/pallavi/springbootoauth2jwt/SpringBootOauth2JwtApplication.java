package com.pallavi.springbootoauth2jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//AuthorizationServer : This annotation enables the Authorization Server in the application.
//EnableResourceServer: It makes the application Resource Server.
//In this case, our application will act as both Authorization as well as Resource Server.
//main class
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class SpringBootOauth2JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauth2JwtApplication.class, args);
	}

}
