package com.i360r.bpm.business.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;

import com.i360r.bpm.business.exception.ChangeGradeExistException;
import com.i360r.bpm.business.exception.DimissionExistException;
import com.i360r.bpm.business.exception.ExceedingReimburseCountException;
import com.i360r.bpm.business.exception.TransferExistException;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.DeliveryStaffGradeConstants;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.DeliveryStaffDimissionConstants;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.bpm.service.rs.process.housingfeereimburse.HousingFeeReimburseConstants;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.SpendBeforeReimburseConstants;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.util.DateTimeUtility;

public class ValidateUtility {

	public static void validateHistoricDeliveryStaffEntryProcess(HistoryService historyService, String deliveryStaffCode) throws Exception {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		
		query = query.processInstanceName(MessageSourceManager.getMessage("process.deliverystaff.entry"));
		query = query.variableValueEquals(DeliveryStaffEntryConstants.KEY_ORIGIN_DELIVERY_STAFF_CODE, deliveryStaffCode);
		query = query.variableValueEquals(ProcessConstants.KEY_PASS, true);
		
		List<HistoricProcessInstance> hpis = query.list();
		for (HistoricProcessInstance hpi : hpis) {
			Date endTime = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(hpi.getEndTime()));
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			String jobTransferDateStr = (String)variableMap.get(DeliveryStaffEntryConstants.KEY_JOB_TRANSFER_DATE);
			Date jobTransferDate = DateTimeUtility.parseYYYYMMDD(jobTransferDateStr);
			Date realJobTransferDate = null;
    		if (jobTransferDate.after(endTime)) {
    			realJobTransferDate = jobTransferDate;
    		} else {
    			realJobTransferDate = DateTimeUtility.addDays(endTime, 1);
    		}
    		
    		Date now = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(new Date()));
    		if (realJobTransferDate.after(now)) {
    			throw new TransferExistException(); 
    		}
		}
	}
	
	public static void validateHistoricStationManagerEntryProcess(HistoryService historyService, String deliveryStaffCode) throws Exception {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		
		query = query.processInstanceName(MessageSourceManager.getMessage("process.stationmanager.entry"));
		query = query.variableValueEquals(DeliveryStaffEntryConstants.KEY_ORIGIN_DELIVERY_STAFF_CODE, deliveryStaffCode);
		query = query.variableValueEquals(ProcessConstants.KEY_PASS, true);
		
		List<HistoricProcessInstance> hpis = query.list();
		for (HistoricProcessInstance hpi : hpis) {
			Date endTime = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(hpi.getEndTime()));
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			String jobTransferDateStr = (String)variableMap.get(DeliveryStaffEntryConstants.KEY_JOB_TRANSFER_DATE);
			Date jobTransferDate = DateTimeUtility.parseYYYYMMDD(jobTransferDateStr);
			Date realJobTransferDate = null;
    		if (jobTransferDate.after(endTime)) {
    			realJobTransferDate = jobTransferDate;
    		} else {
    			realJobTransferDate = DateTimeUtility.addDays(endTime, 1);
    		}
    		
    		Date now = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(new Date()));
    		if (realJobTransferDate.after(now)) {
    			throw new TransferExistException(); 
    		}
		}
	}
	
	public static void validateHistoricDeliveryStaffDismissionProcess(HistoryService historyService, String deliveryStaffCode) throws Exception {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		
		query = query.processInstanceName(MessageSourceManager.getMessage("process.deliverystaff.dimission"));
		query = query.variableValueEquals(DeliveryStaffEntryConstants.KEY_DELIVERY_STAFF_CODE, deliveryStaffCode);
		query = query.variableValueEquals(ProcessConstants.KEY_PASS, true);
		
		List<HistoricProcessInstance> hpis = query.list();
		for (HistoricProcessInstance hpi : hpis) {
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			String dimissionDateStr = (String)variableMap.get(DeliveryStaffDimissionConstants.KEY_DIMISSION_DATE);
			Date dimissionDate = DateTimeUtility.addDays(DateTimeUtility.parseYYYYMMDD(dimissionDateStr), 1);
    		Date now = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(new Date()));
    		if (dimissionDate.after(now)) {
    			throw new DimissionExistException(); 
    		}
		}
	}
	
	public static void validateHousingFeeReimburseCount(String processName, String businessAreaCode, HistoryService historyService, RuntimeService runtimeService) {

		int count = getProcessCount(processName, businessAreaCode, historyService, runtimeService);
		if (count >= HousingFeeReimburseConstants.MONTHLY_HOUSING_FEE_REIMBURSE_COUNT) {
			throw new ExceedingReimburseCountException();
		}
		
	}
	
	public static void validateSpendBeforeReimburseCount(String processName, String businessAreaCode, HistoryService historyService, RuntimeService runtimeService) {
		int count = getProcessCount(processName, businessAreaCode, historyService, runtimeService);
		if (count >= SpendBeforeReimburseConstants.MONTHLY_SPEND_BEFORE_REIMBURSE_COUNT) {
			throw new ExceedingReimburseCountException();
		}
		
	}
	
	private static int getProcessCount(String processName, String businessAreaCode, HistoryService historyService, RuntimeService runtimeService) {
		Date now = new Date();
		HistoricProcessInstanceQuery queryHistoric = historyService.createHistoricProcessInstanceQuery();
		queryHistoric = queryHistoric.processDefinitionKey(processName);
		queryHistoric = queryHistoric.variableValueEquals(ProcessConstants.KEY_PASS, true);
		queryHistoric = queryHistoric.variableValueEquals(ProcessConstants.KEY_BUSINESS_AREA_CODE, businessAreaCode);
		queryHistoric = queryHistoric.variableValueGreaterThanOrEqual(ProcessConstants.KEY_CREATE_TIME, DateTimeUtility.getFirstDayOfMonth(now));
		queryHistoric = queryHistoric.variableValueLessThan(ProcessConstants.KEY_CREATE_TIME, DateTimeUtility.getFirstDayOfMonth(DateTimeUtility.getNextMonth(now)));
		
		ProcessInstanceQuery queryRuntime = runtimeService.createProcessInstanceQuery();
		queryRuntime = queryRuntime.processDefinitionKey(processName);
		queryRuntime = queryRuntime.variableValueEquals(ProcessConstants.KEY_BUSINESS_AREA_CODE, businessAreaCode);
		queryRuntime = queryRuntime.variableValueGreaterThanOrEqual(ProcessConstants.KEY_CREATE_TIME, DateTimeUtility.getFirstDayOfMonth(now));
		queryRuntime = queryRuntime.variableValueLessThan(ProcessConstants.KEY_CREATE_TIME, DateTimeUtility.getFirstDayOfMonth(DateTimeUtility.getNextMonth(now)));
		int count = queryHistoric.list().size() + queryRuntime.list().size();
		
		return count;
	}
	
	public static void validateHistoricDeliveryStaffGradeProcess(HistoryService historyService, String deliveryStaffCode) throws Exception {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		
		query = query.processInstanceName(MessageSourceManager.getMessage("process.deliverystaff.grade"));
		query = query.variableValueEquals(DeliveryStaffGradeConstants.KEY_DELIVERY_STAFF_ID, deliveryStaffCode);
		query = query.variableValueEquals(ProcessConstants.KEY_PASS, true);
		
		List<HistoricProcessInstance> hpis = query.list();
		for (HistoricProcessInstance hpi : hpis) {
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			String changeGradeDateStr = (String)variableMap.get(DeliveryStaffGradeConstants.KEY_CHANGE_GRADE_DATE);
			Date changeGradeDate = DateTimeUtility.parseYYYYMMDD(changeGradeDateStr);
    		
    		Date now = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(new Date()));
    		if (changeGradeDate.after(now)) {
    			throw new ChangeGradeExistException(); 
    		}
		}
	}
	
}
