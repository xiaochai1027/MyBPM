package com.i360r.bpm.service.rs.process.employee.city.salary;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.CitySalaryExistException;
import com.i360r.bpm.business.handler.employee.salary.api.ISalaryHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.employee.city.salary.api.CitySalaryDetailVO;
import com.i360r.bpm.service.rs.process.employee.city.salary.api.CitySalaryRequest;
import com.i360r.bpm.service.rs.process.employee.city.salary.api.ICitySalaryService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;

public class CitySalaryService implements ICitySalaryService {
	
	@Autowired
	private ISalaryHandler salaryHandler;

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Override
	public ProcessDetailResponse<CitySalaryDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, CitySalaryDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(CitySalaryRequest request) throws Exception {
		
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		query = query.processDefinitionKey(CitySalaryConstants.PROCESS_DEFINITION_KEY);
		query = query.variableValueEquals("month", request.getMonth());
		query = query.variableValueEquals("cityCode", request.getCityCode());
		if (!CollectionUtils.isEmpty(query.list())) {
			throw new CitySalaryExistException();
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		processHandler.startProcess(employee, request, CitySalaryConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
		
		salaryHandler.createCitySalaryAudit(request.getCitySalaryId());
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse chiefCommissarApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, CitySalaryConstants.KEY_CHIEF_COMMISSAR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse compensationBenefitsSpecialistCheck(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, CitySalaryConstants.KEY_COMPENSATION_BENEFITS_SPECIALIST_CHECK);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse compensationSpecialistCheck(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, CitySalaryConstants.KEY_COMPENSATION_SPECIALIST_CHECK);
		return new ServiceResponse();
	}

}
