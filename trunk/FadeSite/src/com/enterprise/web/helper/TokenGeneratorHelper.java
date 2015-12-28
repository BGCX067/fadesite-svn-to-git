package com.enterprise.web.helper;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGeneratorHelper
{
	
	  private static SecureRandom random = null;

	  public static String GenerateToken()
	  {
		  if (random == null)
		  {
			  random = new SecureRandom();  
		  }
		  
	    return new BigInteger(130, random).toString(32);
	  }
}
