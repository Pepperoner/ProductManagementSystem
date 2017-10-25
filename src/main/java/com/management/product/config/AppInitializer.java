package com.management.product.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String ENCODING = "UTF-8";

    private static final String FORCE_ENCODING = "true";

    private static final boolean MAPPING_FOR_URL_IS_MATCH_AFTER = true;

    private static final String MAPPING_FOR_URL_PATTERNS = "/*";

    private static final boolean THROW_EXCEPTION_IF_NO_HANDLER_FOUND = true;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                RootConfig.class,
                SecurityConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic dynamic = servletContext
                .addFilter("encodingFilter", new CharacterEncodingFilter());
        dynamic.setInitParameter("encoding", ENCODING);
        dynamic.setInitParameter("forceEncoding", FORCE_ENCODING);
        dynamic.addMappingForUrlPatterns(
                null,
                MAPPING_FOR_URL_IS_MATCH_AFTER,
                MAPPING_FOR_URL_PATTERNS
        );
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(THROW_EXCEPTION_IF_NO_HANDLER_FOUND);
        return dispatcherServlet;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }
}
