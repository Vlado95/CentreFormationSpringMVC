package com.cefisi.config;

//import context.ConnectionFilter;
import context.EncodingFilter;
import javax.servlet.Filter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    
//    protected Filter[] getServletFilters() {
//		return new Filter[]{new ConnectionFilter(),new EncodingFilter()};
//	}

}