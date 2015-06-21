//package com.todo.initializer;
//
//
//
//import javax.faces.webapp.FacesServlet;
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//
//public class AppInitializer implements WebApplicationInitializer {
//
//    private static final String CONFIG_LOCATION = "com.todo.config";
//    private static final String MAPPING_URL = "/";
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        WebApplicationContext context = getContext();
//        
//        servletContext.addListener(new ContextLoaderListener(context));
//        
//        
//	 	   FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",  
//	 		      new CharacterEncodingFilter());
//	 		   fr.setInitParameter("encoding", "UTF-8");
//	 		   fr.setInitParameter("forceEncoding", "true");
//	 		   fr.addMappingForUrlPatterns(null, true, "/*");
//	        
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping(MAPPING_URL);
//        
//        ServletRegistration.Dynamic dispatcherJSF = servletContext.addServlet("Faces Servlet", new FacesServlet());
//        dispatcherJSF.setLoadOnStartup(2);
//        dispatcherJSF.addMapping("/jsf/*");
//        dispatcherJSF.addMapping("*.xhtml");
//        
//        
//        
// 
//    }
//
//
//
//
//    private AnnotationConfigWebApplicationContext getContext() {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation(CONFIG_LOCATION);
//        return context;
//    }
//
//
//}