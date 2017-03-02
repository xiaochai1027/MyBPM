package com.i360r.bpm.data.dao.impl.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;


public class CustomUserEntityManagerFactory implements SessionFactory {

	@Autowired
	private IEmployeeHandler employeeHandler;
	
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	public Session openSession() {
		return new CustomUserEntityManager(employeeHandler);
	}

}
