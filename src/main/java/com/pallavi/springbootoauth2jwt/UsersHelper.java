package com.pallavi.springbootoauth2jwt;
import org.springframework.security.core.userdetails.User;
//Helper class 
public class UsersHelper extends User{

	private static final long serialVersionUID = 1L;
	   public UsersHelper(UsersPojo user) {
	      super(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  user.getListOfgrantedAuthorities()
	    		);
	   }
}


