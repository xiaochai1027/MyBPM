package com.i360r.bpm.business.util;

import com.i360r.cds.api.internal.rs.employee.IEmployeeInternalService;
import com.i360r.cds.api.internal.rs.employee.passport.IEmployeePassportInternalService;
import com.i360r.cds.api.internal.rs.employee.position.IEmployeePositionInternalService;
import com.i360r.framework.bean.context.BeanContextUtility;
import com.i360r.oas.api.internal.rs.employee.salary.IEmployeeSalaryInternalService;
import com.i360r.oas.api.internal.rs.innner.announcement.IInnerAnnouncementInternalService;

public class ServiceClientUtility {
	
	private static IEmployeePassportInternalService employeePassportInternalServiceClient;

	private static IEmployeeSalaryInternalService employeeSalaryInternalServiceClient;

	private static IEmployeePositionInternalService employeePositionInternalServiceClient;
	
	private static IEmployeeInternalService employeeInternalServiceClient;
	
	private static IInnerAnnouncementInternalService innerAnnouncementInternalServiceClient;
	
	public static IEmployeePassportInternalService getEmployeePassportInternalService() {
		if (employeePassportInternalServiceClient == null) {
			employeePassportInternalServiceClient = BeanContextUtility.getBean(IEmployeePassportInternalService.class);
		}
		return employeePassportInternalServiceClient;
	}
	
	public static IEmployeeSalaryInternalService getEmployeeSalaryInternalService() {
		if (employeeSalaryInternalServiceClient == null) {
			employeeSalaryInternalServiceClient = BeanContextUtility.getBean(IEmployeeSalaryInternalService.class);
		}
		return employeeSalaryInternalServiceClient;
	}
	
	public static IEmployeePositionInternalService getEmployeePositionInternalService() {
		if (employeePositionInternalServiceClient == null) {
			employeePositionInternalServiceClient = BeanContextUtility.getBean(IEmployeePositionInternalService.class);
		}
		return employeePositionInternalServiceClient;
	}
	
	public static IEmployeeInternalService getEmployeeInternalService() {
		if (employeeInternalServiceClient == null) {
			employeeInternalServiceClient = BeanContextUtility.getBean(IEmployeeInternalService.class);
		}
		return employeeInternalServiceClient;
	}
	
	public static IInnerAnnouncementInternalService getInnerAnnouncementInternalService() {
		if (innerAnnouncementInternalServiceClient == null) {
			innerAnnouncementInternalServiceClient = BeanContextUtility.getBean(IInnerAnnouncementInternalService.class);
		}
		return innerAnnouncementInternalServiceClient;
	}
}
