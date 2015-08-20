package com.spring.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer {
	

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		// set active profile for spring environment
		rootContext.getEnvironment().setActiveProfiles("prod");

		rootContext.register(RootConfig.class, SecurityConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(MvcConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		// Register spring security FilterChain
		FilterRegistration.Dynamic registration1 = container.addFilter("springSessionRepositoryFilter", DelegatingFilterProxy.class);
		EnumSet<DispatcherType> dispatcherTypes1 = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
		registration1.addMappingForUrlPatterns(dispatcherTypes1, true, "/*");

		// Register spring security FilterChain
		FilterRegistration.Dynamic registration2 = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		EnumSet<DispatcherType> dispatcherTypes2 = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
		registration2.addMappingForUrlPatterns(dispatcherTypes2, true, "/*");

	}


	
	
/*	 Enable this for tomcat */
/*public void onStartup(ServletContext servletContext) throws ServletException {
	  WebApplicationContext context = getContext(servletContext);
	  Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
	  servlet.addMapping("/");
	  servlet.setLoadOnStartup(1);
	 }

	 private WebApplicationContext getContext(ServletContext servletContext) {
	  AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	  context.register(MvcConfig.class);
	  context.setServletContext(servletContext);
	  //context.setConfigLocation("com.tmobile.mpos");
	  Properties prop = propertiesLoader.load("mpos.properties"); 
	  context.getEnvironment().setActiveProfiles(prop.getProperty("spring.profiles.active", "prod"));
	  return context;
	 }*/
	
	

}