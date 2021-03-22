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
			   
			   "-----END RSA PRIVATE KEY-----";//paste your private key here 
			   
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"+ 
								"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy024XwqV48ZLkZfwLHxR\r\n"+ 
								"UXzT6g/53Kg0gKGY9Da9rYDFjSelqhEqC6vFKZ2jMiFrmxaeYSDBpkjy+aQBg6JW\r\n"+ 
								"3akRSuWNF9AgCQsJkFOqyUWrir/QcZPGv1Yw94Us8N6pcJGHj8dXC1CY0gyoKKaP\r\n"+ 
								"mQIDAQAB\r\n"+ 
								"-----END PUBLIC KEY-----";//paste your public key here

	   
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
