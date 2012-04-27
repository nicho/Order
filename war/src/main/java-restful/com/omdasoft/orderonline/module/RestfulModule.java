package com.omdasoft.orderonline.module;


import java.util.HashMap;
import java.util.Map;

import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.omdasoft.orderonline.controller.CommonController;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
 
public class RestfulModule extends ServletModule {
 
    @Override
    protected void configureServlets(){
        bind(CommonController.class).in(RequestScoped.class);
         
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("com.sun.jersey.config.property.packages", "com.omdasoft.orderonline.controller");
        serve("/rest/*").with(GuiceContainer.class, parameters);
    }
     
}