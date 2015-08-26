package com.trier.exam.shopping.resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class ShoppingApplication extends Application {
}

/*
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class ShoppingApplication extends ResourceConfig {

	public ShoppingApplication() {
        packages("com.trier.exam");
    }
	
}
*/