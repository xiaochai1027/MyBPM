package com.i360r.bpm.webapp.jsp.tag.MenuItemTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

import com.i360r.framework.log.Log;



/**
 * 后台菜单项高亮显示自定义标签
 */
@SuppressWarnings("deprecation")
public class MenuItemTag extends SimpleTagSupport {
private static final Log LOG = Log.getLog(MenuItemTag.class);
	
	public static final String MENU_CODE = "MENU_CODE";
	
	//private static final String MENU_CODE_DELIMITER = ".";
	private static final String ELEMENT_TAG = "li";
	private static final String CLASS_NAME = "active";
	
	private String link;
	
	/**
	 * code编码按层级进行,编码都从1开始
	 * 如 1代表一级菜单A, 2代表一级菜单B
	 * 而1.1代表A的二级子菜单A1,1.2代表A的二级子菜单A2;2.1代表B的二级子菜单B1,2.2代表B的二级子菜单B2
	 * 如果有更多的的菜单项则以此类推
	 */
	private String code; 
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		StringBuffer buf = new StringBuffer();
        buf.append("<").append(ELEMENT_TAG); 
        
        String menuCode = null;

		try {
			menuCode = (String)getJspContext().getVariableResolver().resolveVariable(MENU_CODE);
		} catch (ELException e) {
			LOG.error("Catch an exception!", e);
		}

		if (StringUtils.isNotBlank(menuCode)) {
			if (code.equals(menuCode)) {
	        	buf.append(" class=\"").append(CLASS_NAME).append("\"");
	        } else if (code.equals(menuCode.split("\\.")[0])) {
	        	buf.append(" class=\"").append(CLASS_NAME).append("\"");
	        }
		}
        
        buf.append(">");
        buf.append("<a href=\"").append(link).append("\">");
        
        out.write(buf.toString());
        
        getJspBody().invoke(out);
        
        buf.setLength(0);
        buf.append("</a></li>");
        out.write(buf.toString());
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		if (link.startsWith("http://")) {
			String str = StringUtils.substringAfter(link, "http://");
			str = "/" + StringUtils.substringAfter(str, "/");
			this.link = str;
		} else {
			this.link = link;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
}
