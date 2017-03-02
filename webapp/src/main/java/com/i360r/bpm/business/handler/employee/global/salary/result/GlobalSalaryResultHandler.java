package com.i360r.bpm.business.handler.employee.global.salary.result;

import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.ExistUnapprovedCitySalaryException;
import com.i360r.bpm.business.handler.employee.salary.api.ISalaryHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.employee.global.salary.GlobalSalaryConstants;
import com.i360r.bpm.service.rs.process.employee.global.salary.api.GlobalSalaryDetailVO;
import com.i360r.oas.api.internal.rs.employee.salary.UpdateGlobalSalaryAuditStatusRequest;

/**
 * 
 * @ClassName: GlobalSalaryResultHandler
 * @Description: 修改onProcessPass和onProcessNotPass方法，全国薪资的 审批流不同意和最终同意时调用以下方法。
 * @author 柴方晨
 * @date 2016年7月4日 下午12:02:40
 * 
 */
public class GlobalSalaryResultHandler extends AbstractEngineHandler implements
		IProcessResultHandler {

	@Autowired
	private IMessageNotifyHandler messageHandler;

	@Autowired
	private ISalaryHandler salaryHandler;

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {

		Map<String, Object> processVariables = getRuntimeService()
				.getVariables(execution.getId());

		GlobalSalaryDetailVO globalDetail = ProcessUtility.getProcessObject(
				processVariables, GlobalSalaryDetailVO.class);
		List<Integer> unapprovedCitySalaryIds = globalDetail.getUnapprovedCitySalaryIds();
		if ( unapprovedCitySalaryIds != null && unapprovedCitySalaryIds.size() > 0) {
			throw new ExistUnapprovedCitySalaryException();
		}
		UpdateGlobalSalaryAuditStatusRequest globalSalaryAuditStatusRequest = new UpdateGlobalSalaryAuditStatusRequest();
		globalSalaryAuditStatusRequest.setApproved(true);
		globalSalaryAuditStatusRequest.setGlobalSalaryId(globalDetail.getGlobalSalaryId());
		
		
		salaryHandler.updateGlobalSalaryAuditStatus(globalSalaryAuditStatusRequest);
		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {

		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		GlobalSalaryDetailVO globalDetail = ProcessUtility.getProcessObject(processVariables, GlobalSalaryDetailVO.class);
		List<Integer> unapprovedCitySalaryIds = globalDetail.getUnapprovedCitySalaryIds();
		
		UpdateGlobalSalaryAuditStatusRequest globalSalaryAuditStatusRequest = new UpdateGlobalSalaryAuditStatusRequest();
		globalSalaryAuditStatusRequest.setApproved(false);
		globalSalaryAuditStatusRequest.setGlobalSalaryId(globalDetail.getGlobalSalaryId());
		if (unapprovedCitySalaryIds != null && unapprovedCitySalaryIds.size() > 0) {
			globalSalaryAuditStatusRequest.setUnapprovedCitySalaryIds(unapprovedCitySalaryIds);
		}
		
		salaryHandler.updateGlobalSalaryAuditStatus(globalSalaryAuditStatusRequest);
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return GlobalSalaryConstants.PROCESS_DEFINITION_KEY;
	}

}
