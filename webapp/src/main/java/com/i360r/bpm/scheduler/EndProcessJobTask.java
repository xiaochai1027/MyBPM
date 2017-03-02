package com.i360r.bpm.scheduler;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.ProcessResultHandlerManager;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.attendance.AttendanceRawDataConstants;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.DeliveryStaffCancelLeaveConstants;
import com.i360r.bpm.service.rs.process.deliverystaffleave.DeliveryStaffLeaveConstants;
import com.i360r.framework.log.Log;
import com.i360r.framework.scheduler.AbstractStatefulJobTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EndProcessJobTask extends AbstractStatefulJobTask {
	private static final long serialVersionUID = 6697533888949383874L;

	private static final Log LOG = Log.getLog(EndProcessJobTask.class);

	@Autowired
	private ProcessResultHandlerManager resultManager;

	@Override
	public void run() {

		LOG.info("Start EndProcessJobTask");
		long time1 = System.currentTimeMillis();

		HistoryService historyService = BeanUtility.getHistoryService();
		RuntimeService runtimeService = BeanUtility.getRuntimeService();
		Date firstDayOfCurrentMonth = DateTimeUtility.getFirstDayOfMonth(new Date());

		List<HistoricProcessInstance> leavepis = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(DeliveryStaffLeaveConstants.PROCESS_DEFINITION_KEY).unfinished().list();
		if (leavepis != null && !leavepis.isEmpty()) {
			for (HistoricProcessInstance pi: leavepis) {

				List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();

				Map<String, Object> variableMap = ProcessUtility
						.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);

				String fromDateHour = (String)variableMap.get(DeliveryStaffLeaveConstants.KEY_FROM_DATE_HOUR);

				try {
					Date date = DateTimeUtility.parseYYYYMMDDHHMMSS(fromDateHour);
					if (date.before(firstDayOfCurrentMonth)) {
						List<Execution> exes = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).list();
						resultManager.onProcessNotPassAndFinish((ExecutionEntity)exes.get(0));
					}
				} catch (Exception e) {
					LOG.error("end process error, processInstanceId : " + pi.getId(), e);
				}
			}
		}

		List<HistoricProcessInstance> cancelLeavepis = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(DeliveryStaffCancelLeaveConstants.PROCESS_DEFINITION_KEY).unfinished().list();
		if (cancelLeavepis != null && !cancelLeavepis.isEmpty()) {
			for (HistoricProcessInstance pi: cancelLeavepis) {

				List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();

				Map<String, Object> variableMap = ProcessUtility
						.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);

				String leaveFromDate = (String)variableMap.get(DeliveryStaffCancelLeaveConstants.KEY_LEAVE_FROM_DATE);

				try {
					Date date = DateTimeUtility.parseYYYYMMDD(leaveFromDate);
					if (date.before(firstDayOfCurrentMonth)) {
						List<Execution> exes = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).list();
						resultManager.onProcessNotPassAndFinish((ExecutionEntity)exes.get(0));
					}
				} catch (Exception e) {
					LOG.error("end process error, processInstanceId : " + pi.getId(), e);
				}

			}
		}

		//添加考勤流程，月初不通过
		List<HistoricProcessInstance> attendancepis  = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(AttendanceRawDataConstants.PROCESS_DEFINITION_KEY).unfinished().list();
		if (attendancepis != null &	 !attendancepis.isEmpty()) {
			for (HistoricProcessInstance pi: attendancepis) {

				List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();

				Map<String, Object> variableMap = ProcessUtility
						.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);

				Date createTime = (Date)variableMap.get(ProcessConstants.KEY_CREATE_TIME);

				try {
					if (createTime.before(firstDayOfCurrentMonth)) {
						List<Execution> exes = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).list();

						resultManager.onProcessNotPassAndFinish((ExecutionEntity)exes.get(0));
					}
				} catch (Exception e) {
					LOG.error("end add attendance process error, processInstanceId : " + pi.getId(), e);
				}

			}
		}
		
		LOG.info("End EndProcessJobTask use time {} ms", System.currentTimeMillis() - time1);
	}
}
