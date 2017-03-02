package com.i360r.bpm.business.handler.employee.city.salary.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.salary.api.ISalaryHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.employee.city.salary.CitySalaryConstants;
import com.i360r.bpm.service.rs.process.employee.city.salary.api.CitySalaryDetailVO;
import com.i360r.oas.api.internal.rs.employee.salary.UpdateCitySalaryAuditStatusRequest;
/**
 * 
 * @ClassName: CitySalaryResultHandler
 * @Description: 修改onProcessPass和onProcessNotPass方法，城市薪资的 审批流不同意和最终同意时调用以下方法
 * @author 柴方晨
 * @date 2016年7月4日 下午12:02:40
 *
 */
public class CitySalaryResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	private ISalaryHandler salaryHandler;

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		CitySalaryDetailVO citySalaryDetail = ProcessUtility.getProcessObject(
				processVariables, CitySalaryDetailVO.class);
		
		UpdateCitySalaryAuditStatusRequest citySalaryAuditStatusRequest = new UpdateCitySalaryAuditStatusRequest();
		citySalaryAuditStatusRequest.setApproved(true);
		citySalaryAuditStatusRequest.setCitySalaryId(citySalaryDetail.getCitySalaryId());
		salaryHandler.updateCitySalaryAuditStatus(citySalaryAuditStatusRequest);
		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		CitySalaryDetailVO citySalaryDetail = ProcessUtility.getProcessObject(
				processVariables, CitySalaryDetailVO.class);
		UpdateCitySalaryAuditStatusRequest citySalaryAuditStatusRequest = new UpdateCitySalaryAuditStatusRequest();
		citySalaryAuditStatusRequest.setApproved(false);
		citySalaryAuditStatusRequest.setCitySalaryId(citySalaryDetail.getCitySalaryId());
		salaryHandler.updateCitySalaryAuditStatus(citySalaryAuditStatusRequest);
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return CitySalaryConstants.PROCESS_DEFINITION_KEY;
	}
	
}
