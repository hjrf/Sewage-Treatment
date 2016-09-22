package com.apache.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HttpEncodingFilter implements Filter{
	private String encode = null;
	private boolean ignore = false;
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
	 
	        if (!ignore) {
	        request.setCharacterEncoding(encode);
	        response.setContentType("text/html;charset="+ encode +"");  
	        }
	        chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig)  {
		 String encode = filterConfig.getInitParameter("encode");
	     String ignore = filterConfig.getInitParameter("ignore");
	     if (this.encode == null) {
	         this.encode = encode;
	     }
         if ("1".equals(ignore) || "yes".equals(ignore)) {
        	 this.ignore = true;
	     }
	        
	}
	public void destroy() {
		encode = null;
        ignore = false;
	}
}
