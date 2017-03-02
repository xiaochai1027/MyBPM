package com.i360r.bpm.service.rs.process.employee.global.salary;

import com.i360r.bpm.business.handler.employee.salary.api.ISalaryHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.employee.global.salary.api.GlobalSalaryApproveRequest;
import com.i360r.bpm.service.rs.process.employee.global.salary.api.GlobalSalaryDetailVO;
import com.i360r.bpm.service.rs.process.employee.global.salary.api.GlobalSalaryRequest;
import com.i360r.bpm.service.rs.process.employee.global.salary.api.IGlobalSalaryService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class GlobalSalaryService implements IGlobalSalaryService {

	@Autowired
	private ISalaryHandler salaryHandler;

	@Autowired
	private IProcessHandler processHandler;

	@Override
	public ProcessDetailResponse<GlobalSalaryDetailVO> getDetail(String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, GlobalSalaryDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(GlobalSalaryRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		processHandler.startProcess(employee, request, GlobalSalaryConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);

		salaryHandler.createGlobalSalaryAudit(request.getGlobalSalaryId());
		return new ServiceResponse();
	}

	public ServiceResponse CPOApprove(GlobalSalaryApproveRequest request) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (!request.getApproved()) {
			variables.put(GlobalSalaryConstants.TASK_UNAPPROVEDCITYSALARYIDS, request.getUnapprovedCitySalaryIds());
		}
		processHandler.completeTask(request, variables, GlobalSalaryConstants.KEY_CPOAPPROVED);
		return new ServiceResponse();
	}

	public ServiceResponse COOApprove(GlobalSalaryApproveRequest request) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (!request.getApproved()) {
			variables.put(GlobalSalaryConstants.TASK_UNAPPROVEDCITYSALARYIDS, request.getUnapprovedCitySalaryIds());
		}
		processHandler.completeTask(request, variables, GlobalSalaryConstants.KEY_COOAPPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse compensationBenefitsSpecialistConfirm(GlobalSalaryApproveRequest request) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (!request.getApproved()) {
			variables.put(GlobalSalaryConstants.TASK_UNAPPROVEDCITYSALARYIDS, request.getUnapprovedCitySalaryIds());
		}

		processHandler.completeTask(request, variables, GlobalSalaryConstants.KEY_COMPENSATION_BENEFITS_SPECIALIST_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse compensationSpecialistConfirm(GlobalSalaryApproveRequest request) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (!request.getApproved()) {
			variables.put(GlobalSalaryConstants.TASK_UNAPPROVEDCITYSALARYIDS, request.getUnapprovedCitySalaryIds());
		}

		processHandler.completeTask(request, variables, GlobalSalaryConstants.KEY_COMPENSATION_SPECIALIST_CONFIRMED);
		return new ServiceResponse();
	}

}
