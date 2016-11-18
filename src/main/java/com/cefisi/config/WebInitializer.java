package com.cefisi.config;

import context.ConnectionFilter;
import context.EncodingFilter;
import javax.servlet.Filter;
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRegistration.Dynamic;  
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
  
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;  
import org.springframework.web.servlet.DispatcherServlet;  
  
public class WebInitializer implements WebApplicationInitializer {
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {        

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class); 
       // ctx.register(WebJars.class);  
        ctx.setServletContext(servletContext);    
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
        servlet.addMapping("/");  
        servlet.setLoadOnStartup(1);

    }

	protected Filter[] getServletFilters() {
		return new Filter[]{new ConnectionFilter(),new EncodingFilter()};
	}
}
