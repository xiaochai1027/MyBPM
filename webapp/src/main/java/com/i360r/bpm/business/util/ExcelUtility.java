package com.i360r.bpm.business.util;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ExcelUtility {
	 public static String encodeFileName(String fileName, HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		return encodeFileName(fileName, agent);
	 }
	
	public static String encodeFileName(String fileName, String agent) {        
	    try {
	        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
	        String newFileName = URLEncoder.encode(fileName, "UTF-8");
	        newFileName = StringUtils.replace(newFileName, "+", "%20");
	        if (newFileName.length() > 150) {
	            newFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
	            newFileName = StringUtils.replace(newFileName, " ", "%20");
	        }
	        return newFileName;
	    }
	    if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
	        // return MimeUtility.encodeText(fileName, "UTF-8", "B");
	        return URLEncoder.encode(fileName, "UTF-8");
	        }
	        return fileName;
	    } catch (Exception ex) {
	        return fileName;
	    }
	}

}
