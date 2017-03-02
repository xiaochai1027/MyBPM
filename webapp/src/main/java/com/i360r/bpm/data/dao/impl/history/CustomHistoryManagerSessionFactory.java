package com.i360r.bpm.data.dao.impl.history;

import org.activiti.engine.impl.history.HistoryManager;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

public class CustomHistoryManagerSessionFactory implements SessionFactory {
	
	public java.lang.Class<?> getSessionType() {
		return HistoryManager.class; 
	}
	
	@Override
	public Session openSession() {
		return new CustomHistoryManager();
	}

}
