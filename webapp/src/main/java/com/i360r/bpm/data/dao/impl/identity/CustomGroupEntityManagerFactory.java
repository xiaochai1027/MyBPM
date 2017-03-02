package com.i360r.bpm.data.dao.impl.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;


public class CustomGroupEntityManagerFactory implements SessionFactory {

	@Autowired
	private IEmployeeHandler employeeHandler;
	
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	public Session openSession() {
		return new CustomGroupEntityManager(employeeHandler);
	}

}