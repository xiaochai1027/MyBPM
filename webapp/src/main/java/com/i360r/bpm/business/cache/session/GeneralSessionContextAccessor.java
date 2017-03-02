package com.i360r.bpm.business.cache.session;

import java.io.Serializable;

import com.i360r.framework.http.context.HttpSessionContextUtility;

public class GeneralSessionContextAccessor {
	
	public static Serializable getAttribute(String key) {
		return  (Serializable)HttpSessionContextUtility.getAttribute(key);
    }
	
	public static void setAttribute(String key, Serializable attribute) {
		HttpSessionContextUtility.setAttribute(key, attribute);
    }

}
