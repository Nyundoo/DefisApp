package com.Defis.domain.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityEvaluationContextExtension implements EvaluationContextExtension {

	 @Override
	  public String getExtensionId() {
	    return "security";
	  }

	  @Override
	  public SecurityExpressionRoot getRootObject() {
	    org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return new SecurityExpressionRoot(authentication) {};
	  }

}
