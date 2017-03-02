package com.i360r.bpm.webapp.view.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.i360r.bpm.webapp.jsp.tag.MenuItemTag.MenuItemTag;
import com.i360r.framework.http.util.HttpServletUtility;

public class MenuItemInterceptor extends HandlerInterceptorAdapter {
	
	private static final Map<String, String> MENU_CODE_MAP;
	
	static {
		MENU_CODE_MAP = new HashMap<String, String>();
		
		MENU_CODE_MAP.put("/bpm/myTask/search.html", "1");
		MENU_CODE_MAP.put("/bpm/waitTask/search.html", "2");
		MENU_CODE_MAP.put("/bpm/candidate/search.html", "3");
		MENU_CODE_MAP.put("/bpm/task/search.html", "4");

		
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String url = HttpServletUtility.getRequestUri(request);
		
		String menuCode = MENU_CODE_MAP.get(url);
		
		modelAndView.addObject(MenuItemTag.MENU_CODE, menuCode);
	}

}
