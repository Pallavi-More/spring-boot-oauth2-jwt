package com.pallavi.springbootoauth2jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey =" -----BEGIN RSA PRIVATE KEY-----\r\n"+ 
			   "MIIEowIBAAKCAQEAy024XwqV48ZLkZfwLHxRUXzT6g/53Kg0gKGY9Da9rYDFjSel\r\n"+ 
			   "qhEqC6vFKZ2jMiFrmxaeYSDBpkjy+aQBg6JW3akRSuWNF9AgCQsJkFOqyUWrir/Q\r\n"+ 
			   "cZPGv1Yw94Us8N6pcJGHj8dXC1CY0gyoKKaPpTxX2r/HJiaU4e9Azo0TYccCAAPa\r\n"+ 
			   "aL43Uhfb+549aSYlg6QkhLo3MRA05WPc89msXoBOv8ZuS/Z8QvOfvO8x9ZKavWAs\r\n"+ 
			   "HmfL3IHvLOCFSw05I3q62r5qyQ2hpVUhjll2/EgRWZkKq7FHKwMX1epWKnpHj8cE\r\n"+ 
			   "FlXefUPCYaU6ERrwcx3mSSXtWFDSH+EPDIFemQIDAQABAoIBABzhd5WYzV5EJywR\r\n"+ 
			   "p37KUkr/LqOLjXU5N+wmCLJ4rE1fg7DftvsH4S8zQZj92dcfREgN2IX3v0q+BjZM\r\n"+ 
			   "hCKNB0YJ5Hj9TyPY3xQpyzwDhrl8+jeFtDjOQ2ee+OnQs2esWemx4s4cBVOIKY19\r\n"+ 
			   "Ws4bi0Bv6BMdN09GH9RQxqYRCHXVLKjXxnaIYL1atzBRVm9MAGACJQeeww3hwNHB\r\n"+ 
			   "BXFvjtkimL/uQnxV+c18JJslRKhCUz1ZYP8zLr2b6Nr5808acqob1NjNRT2LGWqS\r\n"+ 
			   "3paAInweOvqAF/lTBDuOJcG71iyThbJn/8LMfSJzUdUWTarR4poLx+TBI964IcoK\r\n"+ 
			   "pYyTnzkCgYEA7MPx0je8+OaJv6WoEiRkwDrU1ExRCuIyix3axPa+G6WXwtuuy41u\r\n"+ 
			   "LaoUsk5U0NicgjQz6p0ZYk2AwvrKxp2ljWeVeKysz9dAVnB9Y8GcQcRjP7zSAq65\r\n"+ 
			   "OiJ5fJORwJUscuU+F5D6StEs3iQSUlx9S+jJV/yLsPuMGDGHXDg1BBsCgYEA29Hd\r\n"+ 
			   "JXCqX1B1LuVDXFyASoBzOUtHm5l4rHzbcVeoA4gyythn3Ib5pashadl2CIeftotb\r\n"+ 
			   "C/q6wN5J+Qa6Ha3aAtSE/dv1ZE//lmSgDg3jSsTvdSV28ivaJWFX6QZbqqKC1om2\r\n"+ 
			   "H/lVo6ZbhF+qsnCZiLcHyPa6Obn7+16xQuGvS1sCgYB4cZmD4jzstYJfjur4B7GF\r\n"+ 
			   "JKh+MzCLA9TIdnCDAC5nrA6O3uiFvjXNXxdWOYjmETtU9S9v62ktW9Xj2mbrMOk2\r\n"+ 
			   "DPq5v8XNmWeuUV1IMQbHYhtHIkwHd1Dc5Qq+N+fOe1R4Qmwa2vKous5DV0BGy6wl\r\n"+ 
			   "wI8khmGdr+ltqUx9uk4l/wKBgFZBPzmVwASumYzRQWFYmq6hQJVWyL5xtjxYsfMD\r\n"+ 
			   "plXcd7HIwNhzzjoHsJDMV9h4/16tyAgnVmif0P76eMV0lntN0PGcAepeybfFvFhH\r\n"+ 
			   "cszAAgZBsS7NrkfErq4tZILMSfLNDr31JHmWqYqDz4M9U8GlsFRXHDPSmuHEAmJw\r\n"+ 
			   "FEzJAoGBALmH3q2RtXW2xidNX+Cfn6E5da2wdw7Zbm/qit88mE/uvBgUNw5ejUtT\r\n"+ 
			   "wSXfYwfUh/yTjXcOopLT1MqWahFCSzpAKbJdC4sjFpu2UzvoflGsiY+MfAfVt9GJ\r\n"+ 
			   "fQpy8KA73hxEvlbSyzFS7js0hZX0Q5PLDEclnyRuZepKhSqlokhT\r\n"+ 
			   "-----END RSA PRIVATE KEY-----";
			   
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"+ 
								"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy024XwqV48ZLkZfwLHxR\r\n"+ 
								"UXzT6g/53Kg0gKGY9Da9rYDFjSelqhEqC6vFKZ2jMiFrmxaeYSDBpkjy+aQBg6JW\r\n"+ 
								"3akRSuWNF9AgCQsJkFOqyUWrir/QcZPGv1Yw94Us8N6pcJGHj8dXC1CY0gyoKKaP\r\n"+ 
								"pTxX2r/HJiaU4e9Azo0TYccCAAPaaL43Uhfb+549aSYlg6QkhLo3MRA05WPc89ms\r\n"+ 
								"XoBOv8ZuS/Z8QvOfvO8x9ZKavWAsHmfL3IHvLOCFSw05I3q62r5qyQ2hpVUhjll2\r\n"+ 
								"/EgRWZkKq7FHKwMX1epWKnpHj8cEFlXefUPCYaU6ERrwcx3mSSXtWFDSH+EPDIFe\r\n"+ 
								"mQIDAQAB\r\n"+ 
								"-----END PUBLIC KEY-----";

	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
