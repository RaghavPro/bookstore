package com.raghavpro.bookhive;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.jsp.JspFactory;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        JspFactory.getDefaultFactory().getJspApplicationContext(event.getServletContext())
                .addELContextListener(event1 -> event1.getELContext().getImportHandler().importClass("com.raghavpro.bookhive.Constants"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}