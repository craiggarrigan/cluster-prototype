package com.example.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PortListener;

/**
 * Adds a 'Cluster-Node' HTTP header to responses. Useful for identifying which node in the cluster handled a request.
 */
@Component
public final class ClusterNodeHeaderFilter implements Filter {
	
	@Autowired
	private PortListener portListener;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {				
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(req, resp);
		if(resp instanceof HttpServletResponse){
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			httpResp.setHeader("Cluster-Node", "node-" + portListener.getPort());
		}
	}

	@Override
	public void destroy() {				
	}
}