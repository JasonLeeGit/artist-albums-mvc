package com.ltd.coders.software.artist.albums.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ltd.coders.software.artist.albums.config.SpringCoreConfig;
import com.ltd.coders.software.artist.albums.config.SpringWebConfig;

public class MyServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // services and data sources
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringCoreConfig.class};
    }

    // controller, view resolver, handler mapping
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
    	System.out.println("MyServletInitializer.getServletMappings");
        return new String[]{"/"};
    }
}
