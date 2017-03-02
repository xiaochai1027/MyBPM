package com.i360r.bpm.webapp.view.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class GenericInterceptor extends HandlerInterceptorAdapter {

	@Value("${oas.service.server.url}")
	private String oasServiceServerUrl;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    	
		addServerUrls(modelAndView);
    }
    
    private void addServerUrls(ModelAndView modelAndView) {
    	modelAndView.addObject("oasServiceServerUrl", oasServiceServerUrl);
    }

}
