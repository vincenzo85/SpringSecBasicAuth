/**
 * CustomServerInitializer.java
 *
 * robgion
 * www.2clever.it
 * 
 * 19 apr 2017
 * For further information please write to info@2clever.it
 */
package it.clever.springsec.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author robgion
 *
 */
public class CustomServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	
	
	// voglio la root config class... che mi abilita i controller....
	// qui setto tutte le configurazioni iniziali....
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebMvcConfig.class };
    }
  
    
    // questo è cm se non esistesse....
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    
    // questi sono tutti gli url ... 
    
    
    
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new CORSFilter()};
    	return singleton;
    }
 
}