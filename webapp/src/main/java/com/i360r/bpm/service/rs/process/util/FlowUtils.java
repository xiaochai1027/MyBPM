package com.i360r.bpm.service.rs.process.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.cxf.common.util.CollectionUtils;

import com.i360r.bpm.business.exception.ProcessExistedException;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.StationManagerEntryConstants;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.DeliveryStaffGradeConstants;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.DeliveryStaffDimissionConstants;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.framework.i18n.MessageSourceManager;

public class FlowUtils {
	
	/**
	 * 配送员的流程发起时检查是否存在未完成的流程
	 * @param runtimeService
	 * @param deliveryStaffCode
	 */
	public static void checkdeliveryStaffFlow(RuntimeService runtimeService, String deliveryStaffCode, String deliveryStaffName) {
		// 检查离职流程
		Map<String, Object> dimissionVariables = new HashMap<>();
		dimissionVariables.put("deliveryStaffCode", deliveryStaffCode);
		String message = MessageSourceManager.getMessage("process.deliverystaff.exists.unend.flow", new String[]{deliveryStaffName, "离职"});
		checkFlowExists(runtimeService, DeliveryStaffDimissionConstants.PROCESS_DEFINITION_KEY, dimissionVariables, message);

		// 检查配送员转岗流程
		Map<String, Object> transferVariables = new HashMap<>();
		transferVariables.put("originDeliveryStaffCode", deliveryStaffCode);
		message = MessageSourceManager.getMessage("process.deliverystaff.exists.unend.flow", new String[]{deliveryStaffName, "转岗"});
		checkFlowExists(runtimeService, DeliveryStaffEntryConstants.PROCESS_DEFINITION_KEY, transferVariables, message);
		// 检查站长转岗流程
		checkFlowExists(runtimeService, StationManagerEntryConstants.PROCESS_DEFINITION_KEY, transferVariables, message);
				
		// 调级流程
		Map<String, Object> changeGradeVariables = new HashMap<>();
		changeGradeVariables.put("deliveryStaffId", deliveryStaffCode);
		message = MessageSourceManager.getMessage("process.deliverystaff.exists.unend.flow", new String[]{deliveryStaffName, "调级"});
		checkFlowExists(runtimeService, DeliveryStaffGradeConstants.PROCESS_DEFINITION_KEY, changeGradeVariables, message);
	}
	
	/**
	 * 检查流程是否存在
	 * @param runtimeService
	 * @param definitionKey
	 * @param variables
	 * @param message
	 */
	public static void checkFlowExists(RuntimeService runtimeService, String definitionKey, Map<String, Object> variables, 
			String message) {
		
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		query = query.processDefinitionKey(definitionKey);
		for (Entry<String, Object> entry : variables.entrySet()) {
			query = query.variableValueEquals(entry.getKey(), entry.getValue());
		}
		
		if (!CollectionUtils.isEmpty(query.list())) {
			throw new ProcessExistedException(message);
		}
	} 

}
