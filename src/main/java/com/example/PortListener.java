package com.example;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Provides access to the port the application is running on.
 */
@Component
public class PortListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	private int port;
	
	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
		port = event.getEmbeddedServletContainer().getPort();
	}
	
	public int getPort() {
		return port;
	}

}
