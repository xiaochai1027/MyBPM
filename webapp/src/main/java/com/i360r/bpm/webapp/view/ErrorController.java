package com.i360r.bpm.webapp.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error/404.html", method = RequestMethod.GET)
	public ModelAndView vendorProductWelcomePage(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("error.404", "titleKey", "label.title.error.404");
	}
    
}
