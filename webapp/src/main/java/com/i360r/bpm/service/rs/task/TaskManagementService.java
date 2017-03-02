package com.i360r.bpm.service.rs.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricIdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.exception.InvalidDeliveryStaffException;
import com.i360r.bpm.business.exception.RequestParameterMissingException;
import com.i360r.bpm.business.exception.RequestParameterValidateException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.ProcessResultHandlerManager;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.task.api.BatchClaimRequest;
import com.i360r.bpm.service.rs.task.api.ConditionSearchTaskListRequest;
import com.i360r.bpm.service.rs.task.api.DeleteProcessRequest;
import com.i360r.bpm.service.rs.task.api.GetAssignListRequest;
import com.i360r.bpm.service.rs.task.api.GetAssignListResponse;
import com.i360r.bpm.service.rs.task.api.GetCandidateListRequest;
import com.i360r.bpm.service.rs.task.api.GetCandidateListResponse;
import com.i360r.bpm.service.rs.task.api.GetListResponse;
import com.i360r.bpm.service.rs.task.api.GetMyCurrentTaskRequest;
import com.i360r.bpm.service.rs.task.api.GetMyHistoryTaskRequest;
import com.i360r.bpm.service.rs.task.api.GetTaskListVO;
import com.i360r.bpm.service.rs.task.api.GetToDoListRequest;
import com.i360r.bpm.service.rs.task.api.GetToDoListResponse;
import com.i360r.bpm.service.rs.task.api.ITaskManagementService;
import com.i360r.bpm.service.rs.task.api.KeywordSearchTaskListRequest;
import com.i360r.bpm.service.rs.task.api.ModifyTaskAssigneeRequest;
import com.i360r.bpm.service.rs.task.api.ModifyTaskCreatorRequest;
import com.i360r.bpm.service.rs.task.api.ModifyTaskResponsibilityRequest;
import com.i360r.bpm.service.rs.task.api.ProcessVO;
import com.i360r.bpm.service.rs.task.api.SearchCurrentTaskListResponse;
import com.i360r.bpm.service.rs.task.api.SearchCurrentTaskRequest;
import com.i360r.bpm.service.rs.task.api.SearchHistoryTaskListResponse;
import com.i360r.bpm.service.rs.task.api.SearchHistoryTaskRequest;
import com.i360r.bpm.service.rs.task.api.SearchMyCurrentTaskListResponse;
import com.i360r.bpm.service.rs.task.api.SearchMyHistoryTaskListResponse;
import com.i360r.bpm.service.rs.task.api.SearchProcessVO;
import com.i360r.bpm.service.rs.task.api.TaskVO;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.log.Log;

public class TaskManagementService extends AbstractEngineHandler implements ITaskManagementService {
	
	private static final Log LOG = Log.getLog(TaskManagementService.class);
	
	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	private ProcessResultHandlerManager processResultHandlerManager;
	
	@Override
	public GetToDoListResponse getToDoList(GetToDoListRequest request) {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) 
				+ " T left outer join "+ managementService.getTableName(VariableInstanceEntity.class) + " V1 " + " on T.PROC_INST_ID_=" + " V1" + ".PROC_INST_ID_ "
				+ " left outer join " + managementService.getTableName(VariableInstanceEntity.class) + " V2 " + " on T.PROC_INST_ID_=" + " V2" + ".PROC_INST_ID_");
		
		StringBuilder countSql = new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class)
				+ "  T left outer join " + managementService.getTableName(VariableInstanceEntity.class) + " V1 " + " on T.PROC_INST_ID_=" + " V1" + ".PROC_INST_ID_ "
				+ " left outer join " + managementService.getTableName(VariableInstanceEntity.class) + " V2 " + " on T.PROC_INST_ID_=" + " V2" + ".PROC_INST_ID_");
		
		StringBuilder whereSql = new StringBuilder(" where 1=1 "); 
		whereSql.append("and ((" + "V1" + ".NAME_ = "+"'"+ProcessConstants.KEY_RESPONSIBLE+"'" + " and V1" + ".TEXT_='" + employee.getEmployeePositionCode() + "'" + 
					"and " + "V2" + ".NAME_ = " + "'" + ProcessConstants.KEY_CREATED_BY_CODE + "'" + " and V2" + ".TEXT_=" + "T.ASSIGNEE_" + " )");
		
		whereSql.append(" or T.ASSIGNEE_ ='" + employee.getEmployeePositionCode() + "')");
		
		// 拼接查询条件
		if (!StringUtils.isBlank(request.getProcessInstanceId())) {
			whereSql.append(" and T.PROC_INST_ID_ = '" + request.getProcessInstanceId() + "'");
		}
		
		if (!StringUtils.isBlank(request.getProcessName())) {
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			countSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			
			whereSql.append(" and PD.NAME_ like " + "'%" + request.getProcessName() + "%'"); 
		}
		
		if (StringUtils.isNotBlank(request.getBeginDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			whereSql.append(" and (");
			try {
				whereSql.append("T.CREATE_TIME_ < '" + DateTimeUtility.formatYYYYMMDD(DateTimeUtility.getNextDayTime(DateTimeUtility.parseYYYYMMDD(request.getEndDate()))) + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			whereSql.append(" and T.CREATE_TIME_ > '" + request.getBeginDate() + "')");
		}
		
		Map<String,String> args = request.argsToMap();
		if (!args.isEmpty()) {
			int k = 3;
			for (int i = 0; i < args.size(); i++, k++) {
				baseSql.append(" left outer join "+managementService.getTableName(VariableInstanceEntity.class)+ " V" + k + " on T.PROC_INST_ID_=" + " V" + k +".PROC_INST_ID_ ");
				countSql.append(" left outer join "+managementService.getTableName(VariableInstanceEntity.class)+ " V" + k + " on T.PROC_INST_ID_=" + " V" + k +".PROC_INST_ID_ ");
			}
			
		}
		//遍历流程变量
		int k = 3;
		Iterator<Map.Entry<String, String>>iterator = args.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			whereSql.append(" and (V" + k + ".NAME_='" +  entry.getKey() + "'");
			whereSql.append(" and V" + k + ".TEXT_ like" + "'%" +entry.getValue() + "%')");
			k++;
		}
		
		baseSql.append(whereSql.toString()).append(" ORDER BY T.CREATE_TIME_ asc");
		countSql.append(whereSql.toString());
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		long count = query.sql(countSql.toString()).count();
		
		GetToDoListResponse response = new GetToDoListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<TaskVO> taskVOs = new ArrayList<TaskVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			
			taskVOs.add(TaskVO.toVO(task, pi, variables));
		}
		
		response.setTasks(taskVOs);
		
		return response;
	}


	@Override
	public GetAssignListResponse getAssignList(GetAssignListRequest request) throws Exception {
		
		EmployeeCO employeeCO = SessionContextAccessor.getCurrentAccount();
		if (employeeCO == null) {
			throw new InvalidDeliveryStaffException();
		}
		
		TaskQuery taskQuery = getTaskService().createTaskQuery().processVariableValueEquals(ProcessConstants.KEY_RESPONSIBLE, employeeCO.getEmployeePositionCode());
		
		if (StringUtils.isNotEmpty(request.getProcessName())) {
			taskQuery = taskQuery.processDefinitionNameLike("%" + request.getProcessName() + "%");
		}
		
		if (StringUtils.isNotEmpty(request.getKeyword())) {
			taskQuery = taskQuery.processVariableValueLike(ProcessConstants.KEY_KEYWORD, "%" + request.getKeyword() + "%");
		}
		
		if (StringUtils.isNotEmpty(request.getCreatedByName())) {
			taskQuery = taskQuery.processVariableValueEquals(ProcessConstants.KEY_CREATED_BY_NAME, "%" + request.getCreatedByName() + "%");
		}
		
		if (StringUtils.isNotEmpty(request.getProcessInstanceId())) {
			taskQuery = taskQuery.processInstanceId(request.getProcessInstanceId());
		}
		
		if (StringUtils.isNotEmpty(request.getBeginDate())) {
			taskQuery = taskQuery.processVariableValueGreaterThan(ProcessConstants.KEY_CREATE_TIME, DateTimeUtility.parseYYYYMMDD(request.getBeginDate()));	
		}
		
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			taskQuery = taskQuery.processVariableValueLessThan(ProcessConstants.KEY_CREATE_TIME, DateTimeUtility.parseYYYYMMDD(request.getEndDate()));
		}
		
		taskQuery = taskQuery.orderByTaskCreateTime().asc();
		List<Task> tasks = taskQuery.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		long count = taskQuery.count();
		
		GetAssignListResponse response = new GetAssignListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		response.setPagingResult(result);
		
		List<TaskVO> taskVOs = new ArrayList<TaskVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			
			taskVOs.add(TaskVO.toVO(task, pi, variables));
		}
		response.setTasks(taskVOs);
		
		return response;
	}
	
	@Override
	public GetToDoListResponse searchToDoTasksByKeyword(KeywordSearchTaskListRequest request) {

		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T");
		
		StringBuilder countSql =new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T");
		
		StringBuilder whereSql = new StringBuilder(" where T.ASSIGNEE_ = '" + employee.getEmployeePositionCode() + "'");
		
		if (StringUtils.isNotBlank(request.getKeyword())) { // 关键字不为空
			
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			countSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");

			whereSql.append(" and");
			whereSql.append(" (T.PROC_INST_ID_ like " + "'%" + request.getKeyword() + "%'"); // 流程编号
			whereSql.append(" or PD.NAME_ like " + "'%" + request.getKeyword() + "%'"); // 类别
			
			// 关联流程变量表
			Map<String, String> argsToMap = request.argsToMap();
			if (!argsToMap.isEmpty()) {
				
				baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_");
				countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_");
				
				Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
				whereSql.append(" or (");
				
				int i=0;
				while (iterator.hasNext()) {
					if (i > 0) {
						whereSql.append(" or");
					}
					Map.Entry<String, String> entry = iterator.next();
					whereSql.append(" (VI.NAME_ = " + "'" + entry.getKey() + "'");
					whereSql.append(" and VI.TEXT_ like " + "'%" + entry.getValue() + "%')");
					i++;
				}
				whereSql.append(")");
			}
			whereSql.append(")");
		}
		
		baseSql.append(whereSql.toString()).append(" ORDER BY T.CREATE_TIME_ asc");
		countSql.append(whereSql.toString());

		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.sql(countSql.toString()).count();
		
		GetToDoListResponse response = new GetToDoListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<TaskVO> taskVOs = new ArrayList<TaskVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			
			taskVOs.add(TaskVO.toVO(task, pi, variables));
		}
		
		response.setTasks(taskVOs);
		
		return response;
	}
	
	@Override
	public GetCandidateListResponse getCandidateList(GetCandidateListRequest request) {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		String positionCode = employee.getPositionCode();
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T"
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class) + " IL on IL.TASK_ID_ = T.ID_ ");
		
		StringBuilder countSql = new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T"
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class) + " IL on IL.TASK_ID_ = T.ID_ ");
		
		// 关键字或者创建人不为空，关联流程变量表
		Map<String, String> argsToMap = request.argsToMap();
		if (!argsToMap.isEmpty()) {
			for (int i = 0; i < argsToMap.size(); i++) {
				String alias = " VI"+i;
				baseSql.append("inner join "+managementService.getTableName(VariableInstanceEntity.class)+alias+" on T.PROC_INST_ID_="+alias+".PROC_INST_ID_ ");
				countSql.append("inner join "+managementService.getTableName(VariableInstanceEntity.class)+alias+" on T.PROC_INST_ID_="+alias+".PROC_INST_ID_ ");
			}
		}
		
		StringBuilder whereSql = new StringBuilder(" where IL.TYPE_ = 'candidate' and T.ASSIGNEE_ IS NULL");
		if (StringUtils.isNotBlank(positionCode)) {
			whereSql.append(" and IL.GROUP_ID_ in ('" + positionCode + "')");
		}
		
		//遍历流程变量
		int k = 0;
		Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String alias = "VI"+k;
			whereSql.append(" And ("+alias+".NAME_ = "+"'"+entry.getKey()+"'");
			whereSql.append(" And "+alias+".TEXT_ like "+"'%"+entry.getValue()+"%')");
			k++;
		}
		
		List<String> locationCodes = employee.getLocationCodes();
		if (locationCodes != null && !locationCodes.isEmpty()) {
			baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI" +k +" on T.PROC_INST_ID_ = VI"+k+".PROC_INST_ID_");
			countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI" +k +" on T.PROC_INST_ID_ = VI"+k+".PROC_INST_ID_");
			whereSql.append(" and VI"+k+".NAME_ = '" + ProcessConstants.KEY_LOCATION_CODE + "' and (");
			for (int i = 0; i < locationCodes.size(); i++) {
				if (i > 0) {
					whereSql.append(" or ");
				}
				whereSql.append("VI"+k+".TEXT_ like '" + employee.getLocationCodes().get(i) + "%'");	
			}
			whereSql.append(" or VI"+k+".TEXT_ = ''");
			whereSql.append(")");
		}
	
		if (StringUtils.isNotBlank(request.getBeginDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			whereSql.append(" and");
			try {
				whereSql.append(" T.CREATE_TIME_ < '" + DateTimeUtility.formatYYYYMMDD(DateTimeUtility.getNextDayTime(DateTimeUtility.parseYYYYMMDD(request.getEndDate()))) + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			whereSql.append(" and T.CREATE_TIME_ > '" + request.getBeginDate() + "'");
		}
		
		if (StringUtils.isNotBlank(request.getProcessName())) {
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			countSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			
			whereSql.append(" and");
			whereSql.append(" PD.NAME_ like " + "'%" + request.getProcessName() + "%'"); 
		}
		
		if (StringUtils.isNotBlank(request.getProcessInstanceId())) {
			whereSql.append(" and");
			whereSql.append(" T.PROC_INST_ID_ = " + request.getProcessInstanceId());
		}
		//兼容原来流程变量里没有locationCode的流程也能被查出来	
		
		whereSql.append(compatforCandidateList(request, positionCode));
		
		baseSql.append(whereSql.toString()).append(" ORDER BY T.CREATE_TIME_ asc");
		countSql.append(whereSql.toString());

		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.sql(countSql.toString()).count();
		
		GetCandidateListResponse response = new GetCandidateListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<TaskVO> taskVOs = new ArrayList<TaskVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			
			taskVOs.add(TaskVO.toVO(task, pi, variables));
		}
		
		response.setTasks(taskVOs);
		
		return response;
	}
	
	private String compatforCandidateList (GetCandidateListRequest request, String positionCode) {
		
		StringBuilder baseSql = new StringBuilder(" or T.PROC_INST_ID_ in (select distinct VI.PROC_INST_ID_ from " + managementService.getTableName(TaskEntity.class) + " T"
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class) + " IL on IL.TASK_ID_ = T.ID_ "
				+ "inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_ ");
		
		// 关键字或者创建人不为空，关联流程变量表
		Map<String, String> argsToMap = request.argsToMap();
		if (!argsToMap.isEmpty()) {
			for (int i = 0; i < argsToMap.size(); i++) {
				String alias = " VI"+i;
				baseSql.append("inner join "+managementService.getTableName(VariableInstanceEntity.class)+alias+" on T.PROC_INST_ID_="+alias+".PROC_INST_ID_ ");
			}
		}
				
		StringBuilder whereSql = new StringBuilder(" where VI.PROC_INST_ID_ not in "
			+ "(SELECT PROC_INST_ID_ FROM " + managementService.getTableName(VariableInstanceEntity.class)
			+ " where NAME_ = '" + ProcessConstants.KEY_LOCATION_CODE + "') and IL.TYPE_ = 'candidate' and T.ASSIGNEE_ IS NULL ");
		
		if (StringUtils.isNotBlank(positionCode)) {
		whereSql.append(" and IL.GROUP_ID_ in ('" + positionCode + "')");
		}
		
		//遍历流程变量
		int k = 0;
		Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String alias = "VI"+k;
			whereSql.append(" And ("+alias+".NAME_ = "+"'"+entry.getKey()+"'");
			whereSql.append(" And "+alias+".TEXT_ like "+"'%"+entry.getValue()+"%')");
			k++;
		}

			
		if (StringUtils.isNotBlank(request.getBeginDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			whereSql.append(" and");
			try {
				whereSql.append(" T.CREATE_TIME_ < '" + DateTimeUtility.formatYYYYMMDD(DateTimeUtility.getNextDayTime(DateTimeUtility.parseYYYYMMDD(request.getEndDate()))) + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			whereSql.append(" and T.CREATE_TIME_ > '" + request.getBeginDate() + "'");
		}
				
		if (StringUtils.isNotBlank(request.getProcessName())) {
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			
			whereSql.append(" and");
			whereSql.append(" PD.NAME_ like " + "'%" + request.getProcessName() + "%'"); 
		}
				
		if (StringUtils.isNotBlank(request.getProcessInstanceId())) {
			whereSql.append(" and");
			whereSql.append(" T.PROC_INST_ID_ = " + request.getProcessInstanceId());
		}
		whereSql.append(")");
		return baseSql.append(whereSql).toString();
	}
	
	@Override
	public GetCandidateListResponse searchCandidateTasksByKeyword(KeywordSearchTaskListRequest request) {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();

		String positionCode = employee.getPositionCode();
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T"
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class)+	" IL on IL.TASK_ID_ = T.ID_ ");
		
		StringBuilder countSql =new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T"
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class)+	" IL on IL.TASK_ID_ = T.ID_ ");
		
		StringBuilder whereSql = new StringBuilder(" where IL.TYPE_ = 'candidate' and T.ASSIGNEE_ IS NULL");
		
		if (StringUtils.isNotBlank(positionCode)) {
			whereSql.append(" and IL.GROUP_ID_ in ('" + positionCode + "')");
		}
		
		if (StringUtils.isNotBlank(request.getKeyword())) { // 关键字不为空
			
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			countSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");

			whereSql.append(" and");
			whereSql.append(" (T.PROC_INST_ID_ like " + "'%" + request.getKeyword() + "%'"); // 流程编号
			whereSql.append(" or PD.NAME_ like " + "'%" + request.getKeyword() + "%'"); // 类别
			
			// 关联流程变量表
			Map<String, String> argsToMap = request.argsToMap();
			if (!argsToMap.isEmpty()) {
				
				baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_");
				countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_");
				
				Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
				whereSql.append(" or (");
				
				int i=0;
				while (iterator.hasNext()) {
					if (i > 0) {
						whereSql.append(" or");
					}
					Map.Entry<String, String> entry = iterator.next();
					whereSql.append(" (VI.NAME_ = " + "'" + entry.getKey() + "'");
					whereSql.append(" and VI.TEXT_ like " + "'%" + entry.getValue() + "%')");
					i++;
				}
				whereSql.append(")");
			}
			whereSql.append(")");
		}
		
		baseSql.append(whereSql.toString()).append(" ORDER BY T.CREATE_TIME_ asc");
		countSql.append(whereSql.toString());

		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.sql(countSql.toString()).count();
		
		GetCandidateListResponse response = new GetCandidateListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<TaskVO> taskVOs = new ArrayList<TaskVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			
			taskVOs.add(TaskVO.toVO(task, pi, variables));
		}
		
		response.setTasks(taskVOs);
		
		return response;
	}
	
	@Override
	public ServiceResponse claim(String taskId) {
	
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		if (StringUtils.isNotBlank(getTaskService().createTaskQuery().taskId(taskId).singleResult().getAssignee())) {
			throw new EntityNotExistException("taskId=" + taskId, "任务"); 
		}
		
		getTaskService().claim(taskId, employee.getEmployeePositionCode());
	
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyTaskAssignee(ModifyTaskAssigneeRequest request) throws Exception{
		if (StringUtils.isBlank(request.getAssigneeCode())) {
			throw new RequestParameterMissingException("处理人");
		}
		
		Task task = getTaskService().createTaskQuery().taskId(request.getTaskId()).singleResult();
		if (task == null) {
			throw new EntityNotExistException("taskId=" + request.getTaskId(), "任务");
		}
		
		if (!request.getAssigneeCode().equals(task.getAssignee())) {
			getTaskService().setAssignee(request.getTaskId(), request.getAssigneeCode());
		}
	    
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse modifyTaskResponsible(ModifyTaskResponsibilityRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		String employeePositionCode = employee.getEmployeePositionCode();
		
		if (StringUtils.isBlank(employeePositionCode)) {
			throw new InvalidDeliveryStaffException();
		}
		
		if (StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new RequestParameterMissingException("责任人");
		}
		
		TaskQuery query = getTaskService().createTaskQuery();
		Task task = query.processInstanceId(request.getProcessInstanceId())
				.processVariableValueEquals(ProcessConstants.KEY_RESPONSIBLE, employeePositionCode).singleResult();

		if (task == null) {
			throw new EntityNotExistException("processInstanceId=" + request.getProcessInstanceId(), "任务");
		}
		
		if (!request.getResponsibilityCode().equals(employeePositionCode)) {
			getRuntimeService().setVariable(task.getExecutionId(), ProcessConstants.KEY_RESPONSIBLE, request.getResponsibilityCode());
		}

		return new ServiceResponse();
	}
	
	@Override
	public SearchMyCurrentTaskListResponse searchMyCurrentTaskList(GetMyCurrentTaskRequest request) throws Exception{

		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		if (StringUtils.isEmpty(employee.getEmployeePositionCode())) {
			throw new InvalidDeliveryStaffException();
		}
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T "
				+ " inner join " + managementService.getTableName(HistoricProcessInstanceEntity.class) + " HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ "
				+ " left outer join " + managementService.getTableName(VariableInstanceEntity.class) + " V1 " + " on T.PROC_INST_ID_=" + " V1" + ".PROC_INST_ID_ ");
		
		StringBuilder countSql = new StringBuilder( "select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T "
				+ " inner join " + managementService.getTableName(HistoricProcessInstanceEntity.class) + " HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ "
				+ " left outer join " + managementService.getTableName(VariableInstanceEntity.class) + " V1 " + " on T.PROC_INST_ID_=" + " V1" + ".PROC_INST_ID_ ");
		
		Map<String, String> argsToMap = request.argsToMap();
		if (!argsToMap.isEmpty()) {
			int k = 2;
			for (int i = 0; i < argsToMap.size(); i++, k++) {
				String alias = " V" + k;
				baseSql.append("left outer join " + managementService.getTableName(VariableInstanceEntity.class) + alias + " on T.PROC_INST_ID_=" + alias + ".PROC_INST_ID_ ");
				countSql.append("left outer join " + managementService.getTableName(VariableInstanceEntity.class) + alias + " on T.PROC_INST_ID_=" + alias + ".PROC_INST_ID_ ");
			}
		}
		
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("Where 1=1 ");
		whereSql.append("And (((" + "V1" + ".NAME_ = " + "'" + ProcessConstants.KEY_RESPONSIBLE + "'" + " and V1" + ".TEXT_='" + employee.getEmployeePositionCode() + "')");
		whereSql.append(" or ( exists(select L.USER_ID_ from " + managementService.getTableName(IdentityLinkEntity.class) + " L where L.USER_ID_ ='" + employee.getEmployeePositionCode() + "' and L.PROC_INST_ID_ = HP.ID_ ))) ");

		if (!StringUtils.isBlank(request.getProcessInstanceId())) {
			whereSql.append("And T.PROC_INST_ID_ =" + request.getProcessInstanceId());
		}
		if (!StringUtils.isBlank(request.getProcessName())) {
			whereSql.append(" And HP.NAME_ like " + "'%" + request.getProcessName() + "%'");
		}
		if (!StringUtils.isBlank(request.getBeginDate())) {
			whereSql.append(" And HP.START_TIME_ >='" + request.getBeginDate() + "'");
		}
		if (!StringUtils.isBlank(request.getEndDate())) {
			try {
				whereSql.append(" And HP.START_TIME_ <'"+DateTimeUtility.formatYYYYMMDD(DateTimeUtility.getNextDayTime(DateTimeUtility.parseYYYYMMDD(request.getEndDate())))+"'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isBlank(request.getCurrentAssigneeName())) {
			List<String> assigneeCodes = employeeHandler.getEmployeePositionCodesByName(request.getCurrentAssigneeName());
			if (!CollectionUtils.isEmpty(assigneeCodes)) {
				whereSql.append(" And T.ASSIGNEE_ in " + "("+StringUtils.join(assigneeCodes.toArray(), ",") + ")");
			}
		}
		int i=2;
		Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String alias = "V"+i;
			whereSql.append(" And (" + alias + ".NAME_ = " + "'"+entry.getKey() + "'");
			whereSql.append(" And " + alias + ".TEXT_ like " + "'%"+entry.getValue() + "%')");
			i++;
		}
		whereSql.append(")");
		whereSql.append(" ORDER BY HP.START_TIME_ desc");
		
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		
		query.sql(baseSql.toString());
		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.sql(countSql.toString()).count();
		
		SearchMyCurrentTaskListResponse response = new SearchMyCurrentTaskListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<ProcessVO> processVOs = new ArrayList<ProcessVO>();
		for (Task task : tasks) {
			
			ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(task.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = getHistoryService()
					.createHistoricVariableInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			HistoricProcessInstance hpi = getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			
			processVOs.add(ProcessVO.toVO(hpi, pd, variableMap, employeeHandler.getAssigneeName(task.getAssignee()), task.getName()));
		}
		
		response.setProcesses(processVOs);
		
		return response;
	
	}
	
	@Override
	public SearchMyCurrentTaskListResponse searchMyCurrentTasksByKeyword(KeywordSearchTaskListRequest request) throws Exception {

		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from "+managementService.getTableName(TaskEntity.class)+" T "
				+ " inner join "+managementService.getTableName(HistoricProcessInstanceEntity.class)+	" HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ ");
		StringBuilder countSql =new StringBuilder( "select count(distinct T.ID_) from "+managementService.getTableName(TaskEntity.class)+" T "
				+ " inner join "+managementService.getTableName(HistoricProcessInstanceEntity.class)+	" HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ ");
		Map<String, String> argsToMap = request.argsToMap();
		if (!argsToMap.isEmpty()) {
			for (int i = 1; i <= argsToMap.size(); i++) {
				String alias = " V" + i;
				baseSql.append(" left outer join " + managementService.getTableName(VariableInstanceEntity.class) + alias + " on T.PROC_INST_ID_=" + alias +".PROC_INST_ID_ ");
				countSql.append(" left outer join " + managementService.getTableName(VariableInstanceEntity.class) + alias + " on T.PROC_INST_ID_=" + alias +".PROC_INST_ID_ ");
			}
		}
		
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("Where 1=1");
		
		if (!StringUtils.isBlank(employee.getEmployeePositionCode())) {
			whereSql.append(" And exists(select L.USER_ID_ from "+managementService.getTableName(IdentityLinkEntity.class)+" L where L.USER_ID_ ='"+employee.getEmployeePositionCode()+"' and L.PROC_INST_ID_ = HP.ID_ ) ");
		}
		
		if (!StringUtils.isBlank(request.getKeyword())) {
			whereSql.append(" And");
			whereSql.append(" (T.PROC_INST_ID_ like " + "'%" + request.getKeyword() + "%'"); // 流程编号
			whereSql.append(" or HP.NAME_ like " + "'%" + request.getKeyword() + "%'"); // 类别
			
			List<String> assigneeCodes= employeeHandler.getEmployeePositionCodesByName(request.getKeyword());
			if (!CollectionUtils.isEmpty(assigneeCodes)) {
				whereSql.append(" Or T.ASSIGNEE_ in "+"("+StringUtils.join(assigneeCodes.toArray(), ",")+")");
			}
			
			int i=1;
			Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				String alias = "V" + i;
				whereSql.append(" Or (" + alias + ".NAME_ = " + "'" + entry.getKey() + "'");
				whereSql.append(" And " + alias + ".TEXT_ like " + "'%" + entry.getValue() + "%')");
				i++;
			}
			whereSql.append(")");
		}
		
		baseSql.append(whereSql.toString()).append(" ORDER BY HP.START_TIME_ asc");
		countSql.append(whereSql.toString());
		
		query.sql(baseSql.toString());
		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.sql(countSql.toString()).count();
		
		SearchMyCurrentTaskListResponse response = new SearchMyCurrentTaskListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<ProcessVO> processVOs = new ArrayList<ProcessVO>();
		for (Task task : tasks) {
			
			ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(task.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = getHistoryService()
					.createHistoricVariableInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			HistoricProcessInstance hpi = getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			
			processVOs.add(ProcessVO.toVO(hpi, pd, variableMap, employeeHandler.getAssigneeName(task.getAssignee()), task.getName()));
		}
		
		response.setProcesses(processVOs);
		
		return response;
	
	
	}
	
	@Override
	public SearchMyHistoryTaskListResponse searchMyHistoryTaskList(GetMyHistoryTaskRequest request) {
		 
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		HistoricProcessInstanceQuery query = getHistoryService()
				.createHistoricProcessInstanceQuery().involvedUser(employee.getEmployeePositionCode());
		
		if (!StringUtils.isBlank(request.getProcessInstanceId())) {
			query = query.processInstanceId(request.getProcessInstanceId());
		}
		if (!StringUtils.isBlank(request.getProcessName())) {
			query = query.processInstanceNameLike("%"+request.getProcessName()+"%");
		}
		if (!StringUtils.isBlank(request.getBeginDate())) {
			try {
				query = query.startedAfter(DateTimeUtility.parseYYYYMMDD(request.getBeginDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isBlank(request.getEndDate())) {
			try {
				query = query.startedBefore(DateTimeUtility.getNextDayTime(
						DateTimeUtility.parseYYYYMMDD(request.getEndDate())).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isBlank(request.getCreatedByName())) {
			query = query.variableValueEquals(
					ProcessConstants.KEY_CREATED_BY_NAME, request.getCreatedByName());
		}
		if (!StringUtils.isBlank(request.getKeyword())) {
			query = query.variableValueLike(ProcessConstants.KEY_KEYWORD, "%"+request.getKeyword()+"%");
		}
		
		ProcessStatus status = ProcessStatus.fromCode(request.getProcessStatusCode());
		if (status != null) {
			switch (status)	{
			case NOT_PASS:
				query = query.variableValueEquals(ProcessConstants.KEY_PASS, false);
				break;
			case PASS:
				query = query.variableValueEquals(ProcessConstants.KEY_PASS, true);
				break;
			default:
				break;				
			}
		}
		
		query.finished();
		query = query.orderByProcessInstanceStartTime().desc();
		
		List<HistoricProcessInstance> hpis = query.listPage(
				(request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.count();
		
		SearchMyHistoryTaskListResponse response = new SearchMyHistoryTaskListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<ProcessVO> processVOs = new ArrayList<ProcessVO>();
		for (HistoricProcessInstance hpi : hpis) {
			
			ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = getHistoryService()
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(hpi.getId()).singleResult();
			String currentAssignee = "";
			String currentTaskName = "";
			if (pi != null) {
				Task task = getTaskService().createTaskQuery().processInstanceId(
						hpi.getId()).taskDefinitionKey(pi.getActivityId()).singleResult();
				if (task != null) {
					currentAssignee = employeeHandler.getAssigneeName(task.getAssignee());
					currentTaskName = task.getName();
				}
			}
			
			processVOs.add(ProcessVO.toVO(hpi, pd, variableMap, currentAssignee, currentTaskName));
		}
		
		response.setProcesses(processVOs);
		
		return response;
	}

	@Override
	public SearchCurrentTaskListResponse searchCurrentTaskList(SearchCurrentTaskRequest request) throws Exception {
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from "+managementService.getTableName(TaskEntity.class)+" T "
				+ "inner join "+managementService.getTableName(HistoricProcessInstanceEntity.class)+	" HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ ");
		StringBuilder countSql =new StringBuilder( "select count(distinct T.ID_) from "+managementService.getTableName(TaskEntity.class)+" T "
				+ "inner join "+managementService.getTableName(HistoricProcessInstanceEntity.class)+	" HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ ");
		Map<String, String> argsToMap = request.argsToMap();
		if (!argsToMap.isEmpty()) {
			for (int i = 1; i <= argsToMap.size(); i++) {
				String alias = " V"+i;
				baseSql.append("left outer join "+managementService.getTableName(VariableInstanceEntity.class)+alias+" on T.PROC_INST_ID_="+alias+".PROC_INST_ID_ ");
				countSql.append("left outer join "+managementService.getTableName(VariableInstanceEntity.class)+alias+" on T.PROC_INST_ID_="+alias+".PROC_INST_ID_ ");
			}
		}
		
		StringBuilder whereSql = new StringBuilder();
		whereSql.append("Where 1=1");
		
		if (!StringUtils.isBlank(request.getProcessInstanceId())) {
			validateParam(request.getProcessInstanceId());
			
			whereSql.append(" And T.PROC_INST_ID_ ="+request.getProcessInstanceId());
		}
		if (!StringUtils.isBlank(request.getProcessName())) {
			whereSql.append(" And HP.NAME_ like "+"'%"+request.getProcessName()+"%'");
		}
		if (!StringUtils.isBlank(request.getBeginDate())) {
			whereSql.append(" And HP.START_TIME_ >='"+request.getBeginDate()+"'");
		}
		if (!StringUtils.isBlank(request.getEndDate())) {
			try {
				whereSql.append(" And HP.START_TIME_ <'"+DateTimeUtility.formatYYYYMMDD(DateTimeUtility.getNextDayTime(DateTimeUtility.parseYYYYMMDD(request.getEndDate())))+"'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isBlank(request.getCurrentAssigneeName())) {
			List<String> assigneeCodes= employeeHandler.getEmployeePositionCodesByName(request.getCurrentAssigneeName());
			if (!CollectionUtils.isEmpty(assigneeCodes)) {
				whereSql.append(" And T.ASSIGNEE_ in "+"("+StringUtils.join(assigneeCodes.toArray(), ",")+")");
			} else {
				return new SearchCurrentTaskListResponse();
			}
		}
		
		int i=1;
		Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String alias = "V"+i;
			whereSql.append(" And ("+alias+".NAME_ = "+"'"+entry.getKey()+"'");
			whereSql.append(" And "+alias+".TEXT_ like "+"'%"+entry.getValue()+"%')");
			i++;
		}
		whereSql.append(" ORDER BY HP.START_TIME_ desc");
		
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		
		query.sql(baseSql.toString());
		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.sql(countSql.toString()).count();
		
		SearchCurrentTaskListResponse response = new SearchCurrentTaskListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<SearchProcessVO> processVOs = new ArrayList<SearchProcessVO>();
		for (Task task : tasks) {
			
			ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(task.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = getHistoryService()
					.createHistoricVariableInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			HistoricProcessInstance hpi = getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			
			processVOs.add(SearchProcessVO.toVO(hpi,pd,variableMap,employeeHandler.getAssigneeName(task.getAssignee()),task));
			
		}
		
		response.setProcesses(processVOs);
		
		return response;
	}
	
	@Override
	public SearchHistoryTaskListResponse searchHistoryTaskList(SearchHistoryTaskRequest request) {
		
		HistoricProcessInstanceQuery query = getHistoryService().createHistoricProcessInstanceQuery();
		
		if (!StringUtils.isBlank(request.getProcessInstanceId())) {
			query = query.processInstanceId(request.getProcessInstanceId());
		}
		if (!StringUtils.isBlank(request.getProcessName())) {
			query = query.processInstanceNameLike("%"+request.getProcessName()+"%");
		}
		if (!StringUtils.isBlank(request.getBeginDate())) {
			try {
				query = query.startedAfter(DateTimeUtility.parseYYYYMMDD(request.getBeginDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isBlank(request.getEndDate())) {
			try {
				query = query.startedBefore(DateTimeUtility.getNextDayTime(
						DateTimeUtility.parseYYYYMMDD(request.getEndDate())).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isBlank(request.getCreatedByName())) {
			query = query.variableValueEquals(
					ProcessConstants.KEY_CREATED_BY_NAME, request.getCreatedByName());
		}
		if (!StringUtils.isBlank(request.getKeyword())) {
			query = query.variableValueLike(ProcessConstants.KEY_KEYWORD, "%"+request.getKeyword()+"%");
		}
		
		ProcessStatus status = ProcessStatus.fromCode(request.getProcessStatusCode());
		if (status != null) {
			switch (status)	{
			case NOT_PASS:
				query = query.variableValueEquals(ProcessConstants.KEY_PASS, false);
				break;
			case PASS:
				query = query.variableValueEquals(ProcessConstants.KEY_PASS, true);
				break;
			default:
				break;				
			}
		}
		
		query = query.finished();
		query = query.orderByProcessInstanceStartTime().desc();
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.count();
		
		SearchHistoryTaskListResponse response = new SearchHistoryTaskListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<SearchProcessVO> processVOs = new ArrayList<SearchProcessVO>();
		for (HistoricProcessInstance hpi : hpis) {
			
			ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = getHistoryService()
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(hpi.getId()).singleResult();
			String currentAssignee = "";
			String assigneeCode = "";
			String currentTaskName = "";
			String taskId = "";
			if (pi != null) {
				Task task = getTaskService().createTaskQuery().processInstanceId(
						hpi.getId()).taskDefinitionKey(pi.getActivityId()).singleResult();
				if (task != null) {
					assigneeCode = task.getAssignee();
					currentAssignee = employeeHandler.getAssigneeName(task.getAssignee());
					currentTaskName = task.getName();
					taskId = task.getId();
				}
			}
			processVOs.add(SearchProcessVO.toVO(hpi, pd, variableMap, taskId, assigneeCode,currentAssignee, currentTaskName));
		}
		
		response.setProcesses(processVOs);
		
		return response;
	
	}

	@Override
	public ServiceResponse deleteProcess(DeleteProcessRequest request) throws Exception{
		ProcessInstance pi = getRuntimeService().createProcessInstanceQuery().processInstanceId(request.getProcessInstanceId()).singleResult();
		if (pi != null) {
			Task task = getTaskService().createTaskQuery().processInstanceId(request.getProcessInstanceId()).taskDefinitionKey(pi.getActivityId()).singleResult();
			
			List<Execution> executions = getRuntimeService().createExecutionQuery().executionId(task.getExecutionId()).list();
			
			if (!CollectionUtils.isEmpty(executions)) {
				processResultHandlerManager.onProcessNotPass((ExecutionEntity)executions.get(0));
				getRuntimeService().deleteProcessInstance(request.getProcessInstanceId(), request.getDeleteReason());
				
				if (task.getAssignee() != null) {
				   messageHandler.notifyTaskDelete(task.getAssignee(), task);	
				}
			} else {
				LOG.error("execution not exist : " + request.getProcessInstanceId());
			}
		}
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse batchClaim(BatchClaimRequest request) {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		if (!CollectionUtils.isEmpty(request.getTaskIds())) {
			for (String taskId : request.getTaskIds()) {
				if (StringUtils.isNotBlank(getTaskService().createTaskQuery().taskId(taskId).singleResult().getAssignee())) {
					throw new EntityNotExistException("taskId=" + taskId, "任务"); 
				}
				getTaskService().claim(taskId, employee.getEmployeePositionCode());
			}
		}
	    
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyTaskCreator(ModifyTaskCreatorRequest request)	throws Exception {
		if (StringUtils.isBlank(request.getCreatedByCode())) {
			throw new RequestParameterMissingException("创建人");
		}
		Task task = getTaskService().createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		List<HistoricVariableInstance> hvis = getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).variableName(ProcessConstants.KEY_CREATED_BY_ACCOUNT_TYPE).list();
		
		if (hvis == null || hvis.isEmpty() || !AccountType.EMPLOYEE.name().equals(hvis.get(0).getValue())) {
			throw new RequestParameterValidateException("创建人非员工,不能修改 !");
		}
		
		ProcessInstance pi = getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put(ProcessConstants.KEY_CREATED_BY_CODE, request.getCreatedByCode());
		variables.put(ProcessConstants.KEY_CREATED_BY_NAME, employeeHandler.getAssigneeName(request.getCreatedByCode()));
		
		getRuntimeService().setVariables(task.getExecutionId(), variables);
		
		List<IdentityLink> list = getRuntimeService().getIdentityLinksForProcessInstance(pi.getId());
		for (IdentityLink il : list) {
			if (il.getType().equals(IdentityLinkType.STARTER)) {
				getRuntimeService().deleteUserIdentityLink(il.getProcessInstanceId(), il.getUserId(), il.getType());
				getRuntimeService().addUserIdentityLink(il.getProcessInstanceId(), request.getCreatedByCode(), il.getType());
				break;
			}
		}
		messageHandler.notifyTaskCreatorModified(request.getCreatedByCode(), pi);
		return new ServiceResponse();
	}


	@Override
	public GetListResponse getTaskList(ConditionSearchTaskListRequest request) {
		GetListResponse response = null;
		
		if (StringUtils.isBlank(request.getType())) {
			return response;
		}
		
		// "todo"(待办), "candidate"(候选), "current"(我的), "history"(历史)
		if (request.getType().equals("todo")) {
			response = getToDoListByCondition(request);
			return response;
		} else if (request.getType().equals("candidate")) {
			response = getCandidateByCondition(request);
			return response;
		} else if (request.getType().equals("current")) {
			response = getCurrentByCondition(request);
			return response;
		} else if (request.getType().equals("history")) {
			response = getHistoryByCondition(request);
			return response;
		}
		
		return response;
	}
	
	private GetListResponse getToDoListByCondition(ConditionSearchTaskListRequest request) {
		GetListResponse response = new GetListResponse();
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T");
		StringBuilder countSql = new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T");
		StringBuilder whereSql = new StringBuilder(" where T.ASSIGNEE_ = '" + employee.getEmployeePositionCode() + "'");
		
		if (StringUtils.isNotBlank(request.getCondition())) {
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			countSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_");
			
			whereSql.append(" and");
			whereSql.append(" (T.PROC_INST_ID_ like " + "'%" + request.getCondition() + "%'"); // 流程编号
			whereSql.append(" or PD.NAME_ like " + "'%" + request.getCondition() + "%'"); // 类别
			
			// 关联流程变量表
			Map<String, String> argsToMap = request.argsToMap();
			if (!argsToMap.isEmpty()) {
				
				baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_");
				countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_");
				
				Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
				whereSql.append(" or (");
				
				int i = 0;
				while (iterator.hasNext()) {
					if (i > 0) {
						whereSql.append(" or");
					}
					Map.Entry<String, String> entry = iterator.next();
					whereSql.append(" (VI.NAME_ = " + "'" + entry.getKey() + "'");
					whereSql.append(" and VI.TEXT_ like " + "'%" + entry.getValue() + "%')");
					i++;
				}
				whereSql.append(")");
			}
			whereSql.append(")");
		}
		
		if (StringUtils.isBlank(request.getOrdering())) {
		 	throw new IllegalArgumentException("ordering is null");
		}
		baseSql.append(whereSql.toString()).append(" ORDER BY T.CREATE_TIME_ ").append(request.getOrdering());
		countSql.append(whereSql.toString());

		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		long count = query.sql(countSql.toString()).count();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<GetTaskListVO> taskVOs = new ArrayList<GetTaskListVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			// 查找任务列表
			List<HistoricActivityInstance> finishedTasks = getHistoryService()
					.createHistoricActivityInstanceQuery().finished().processInstanceId(pi.getProcessInstanceId())
					.orderByHistoricActivityInstanceEndTime().desc().list();
			HistoricActivityInstance hai  = null;
			for (HistoricActivityInstance finishedTask : finishedTasks) {
				if (finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_START_EVENT) 
						|| finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_USER_TASK)) {
					hai = finishedTask;
					break;
				}
			}
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			taskVOs.add(GetTaskListVO.toVO(task, pi, variables, hai));
		}
		
		response.setTasks(taskVOs);
		
		return response;
	}
	
	private GetListResponse getCandidateByCondition(ConditionSearchTaskListRequest request) {
		GetListResponse response = new GetListResponse();
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		String position = employee.getPositionCode();
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T "
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class) + " IL on IL.TASK_ID_ = T.ID_ ");
		StringBuilder countSql = new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T "
				+ " inner join " + managementService.getTableName(IdentityLinkEntity.class) + " IL on IL.TASK_ID_ = T.ID_ ");
		StringBuilder whereSql = new StringBuilder(" where IL.TYPE_ = 'candidate' and T.ASSIGNEE_ is null ");
		
		if (StringUtils.isNotBlank(position)) {
			whereSql.append(" and IL.GROUP_ID_ in ('" + position + "')");
		}
		
		List<String> locationCodes = employee.getLocationCodes();
		int k = 0;
		if (locationCodes != null && !locationCodes.isEmpty()) {
			baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI" +k +" on T.PROC_INST_ID_ = VI"+k+".PROC_INST_ID_");
			countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI" +k +" on T.PROC_INST_ID_ = VI"+k+".PROC_INST_ID_");
			whereSql.append(" and VI"+k+".NAME_ = '" + ProcessConstants.KEY_LOCATION_CODE + "' and (");
			for (int i = 0; i < locationCodes.size(); i++) {
				if (i > 0) {
					whereSql.append(" or ");
				}
				whereSql.append("VI"+k+".TEXT_ like '" + employee.getLocationCodes().get(i) + "%'");	
			}
			whereSql.append(" or VI"+k+".TEXT_ = ''");
			whereSql.append(")");
		}
		
		if (StringUtils.isNotBlank(request.getCondition())) {
			baseSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_ ");
			countSql.append(" inner join " + managementService.getTableName(ProcessDefinitionEntity.class) + " PD on PD.ID_ = T.PROC_DEF_ID_ ");
			whereSql.append(" and ( T.PROC_INST_ID_ like '%" + request.getCondition() + "%' or PD.NAME_ like '%" + request.getCondition() + "%'");
			
			Map<String,String> argsToMap = request.argsToMap();
			if (!argsToMap.isEmpty()) {
				baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on VI.PROC_INST_ID_ = T.PROC_INST_ID_ ");
				countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on VI.PROC_INST_ID_ = T.PROC_INST_ID_ ");
				whereSql.append(" or ( ");
				
				Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
				int i = 0;
				while (iterator.hasNext()) {
					if (i > 0) {
						whereSql.append(" or ");
					}
					Map.Entry<String, String> entry = iterator.next();
					String key = entry.getKey();
					String value = entry.getValue();
					
					whereSql.append(" (VI.NAME_ = '" + key + "' and VI.TEXT_ like '%" + value + "%')");
					i++;
				}
				whereSql.append(")");
			}
			whereSql.append(")");
		}
			
		if (StringUtils.isNotBlank(request.getOrdering())) {
			whereSql.append(" ORDER BY T.CREATE_TIME_ " + request.getOrdering());
		}
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * (request.getPageSize()), request.getPageSize()); 
		long count = query.sql(countSql.toString()).count();
		
		PagingResult pagingResult = new PagingResult();
		pagingResult.setPageNumber(request.getPageNumber());
		pagingResult.setPageSize(request.getPageSize());
		pagingResult.setRecordNumber((int)count);
		
		response.setPagingResult(pagingResult);
		List<GetTaskListVO> taskVOs = new ArrayList<GetTaskListVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			List<HistoricActivityInstance> finishedTasks = getHistoryService().createHistoricActivityInstanceQuery().finished()
						.processInstanceId(task.getProcessInstanceId()).orderByHistoricActivityInstanceEndTime().desc().list();
			HistoricActivityInstance hai = null;
			for (HistoricActivityInstance finishedTask : finishedTasks) {
				if (finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_START_EVENT) 
						|| finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_USER_TASK)) {
					hai = finishedTask;
					break;
				}
			}
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			taskVOs.add(GetTaskListVO.toVO(task, pi, variables, hai));
		}
		response.setTasks(taskVOs);
		
		return response;
	}
	
	private GetListResponse getCurrentByCondition(ConditionSearchTaskListRequest request) {
		GetListResponse response = new GetListResponse();
		
		NativeTaskQuery query = getTaskService().createNativeTaskQuery();
		EmployeeCO employeeCO = SessionContextAccessor.getCurrentAccount();
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(TaskEntity.class) + " T "
				+ " inner join " +  managementService.getTableName(HistoricProcessInstanceEntity.class) + " HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ ");
		StringBuilder countSql = new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(TaskEntity.class) + " T "
				+ " inner join " +  managementService.getTableName(HistoricProcessInstanceEntity.class) + " HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ ");
		StringBuilder whereSql = new StringBuilder("where 1 = 1 ");
		
		if (StringUtils.isNotBlank(employeeCO.getEmployeePositionCode())) {
			whereSql.append(" and exists (select L.USER_ID_ from " + managementService.getTableName(IdentityLinkEntity.class) + " L where L.USER_ID_ = '" + employeeCO.getEmployeePositionCode() + "' and L.PROC_INST_ID_ = HP.ID_)");
		}
		
		if (StringUtils.isNotBlank(request.getCondition())) {
			whereSql.append(" and (T.PROC_INST_ID_ like '%" + request.getCondition() + "%' or HP.NAME_ like '%" + request.getCondition() + "%'");
			
			Map<String, String> argargsToMap = request.argsToMap();
			if (!argargsToMap.isEmpty()) {
				baseSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on VI.PROC_INST_ID_ = T.PROC_INST_ID_ ");
				countSql.append(" inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on VI.PROC_INST_ID_ = T.PROC_INST_ID_ ");
				
				whereSql.append(" or(");
				Iterator<Map.Entry<String, String>> iterator = argargsToMap.entrySet().iterator();
				int i = 0;
				while (iterator.hasNext()) {
					if (i > 0) {
						whereSql.append(" or ");
					}
					Map.Entry<String, String> entry = iterator.next();
					String key = entry.getKey();
					String value = entry.getValue();
					
					whereSql.append(" (VI.NAME_ =  '" + key + "' and  VI.TEXT_ like '%" + value + "%')");
					i++;
				}
				whereSql.append(")");
			}
			whereSql.append(")");
		}
		if (StringUtils.isNotBlank(request.getOrdering())) {
			whereSql.append(" ORDER BY T.CREATE_TIME_ " + request.getOrdering());
		}
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		query.sql(baseSql.toString());
		List<Task> tasks = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		long count = query.sql(countSql.toString()).count();
		
		PagingResult pagingResult = new PagingResult();
		pagingResult.setPageNumber(request.getPageNumber());
		pagingResult.setPageSize(request.getPageSize());
		pagingResult.setRecordNumber((int)count);
		response.setPagingResult(pagingResult);
		
		List<GetTaskListVO> taskVOs= new ArrayList<GetTaskListVO>();
		for (Task task : tasks) {
			ProcessInstance pi = getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			List<HistoricActivityInstance> finishedTasks = getHistoryService().createHistoricActivityInstanceQuery().finished()
						.processInstanceId(task.getProcessInstanceId()).orderByHistoricActivityInstanceEndTime().desc().list();
			HistoricActivityInstance hai = null;
			for (HistoricActivityInstance finishedTask : finishedTasks) {
				if (finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_START_EVENT) 
						|| finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_USER_TASK)) {
					hai = finishedTask;
					break;
				}
			}
			
			Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
			taskVOs.add(GetTaskListVO.toVO(task, pi, variables, hai));
		}
		response.setTasks(taskVOs);
		
		return response;
	}
	
	private GetListResponse getHistoryByCondition(ConditionSearchTaskListRequest request) {
		GetListResponse response = new GetListResponse();
		
		EmployeeCO employeeCO = SessionContextAccessor.getCurrentAccount();
		NativeHistoricProcessInstanceQuery query = getHistoryService().createNativeHistoricProcessInstanceQuery();
		StringBuilder baseSql = new StringBuilder("select distinct HPI.* from " + managementService.getTableName(HistoricProcessInstanceEntity.class) + " HPI ");
		StringBuilder countSql = new StringBuilder("select count(HPI.ID_) from " + managementService.getTableName(HistoricProcessInstanceEntity.class) + " HPI ");
		StringBuilder whereSql = new StringBuilder("where 1 = 1 ");
		
		if (StringUtils.isNotBlank(employeeCO.getEmployeePositionCode())) {
			whereSql.append(" and exists (select L.USER_ID_ from " + managementService.getTableName(HistoricIdentityLinkEntity.class) + " L where L.USER_ID_ = '" + employeeCO.getEmployeePositionCode() + "' and L.PROC_INST_ID_ = HPI.ID_)");
		}
		
		if (StringUtils.isNotBlank(request.getCondition())) {
			whereSql.append(" and ( HPI.PROC_INST_ID_ like '%" + request.getCondition() + "%' or HPI.NAME_ like '%" + request.getCondition() + "%'");
			
			baseSql.append(" inner join " + managementService.getTableName(HistoricVariableInstanceEntity.class) + " HVI on HVI.PROC_INST_ID_ = HPI.PROC_INST_ID_ ");
			countSql.append(" inner join " + managementService.getTableName(HistoricVariableInstanceEntity.class) + " HVI on HVI.PROC_INST_ID_ = HPI.PROC_INST_ID_ ");
			Map<String, String> argsToMap = request.argsToMap();
			
			if (!argsToMap.isEmpty()) {
				Iterator<Map.Entry<String, String>> iterator = argsToMap.entrySet().iterator();
				int i = 0;
				whereSql.append(" or(");
				while (iterator.hasNext()) {
					if (i > 0) {
						whereSql.append(" or ");
					}
					Map.Entry<String, String> entry = iterator.next();
					String key = entry.getKey();
					String value = entry.getValue();
					whereSql.append(" (HVI.NAME_ = '" + key + "' and HVI.TEXT_ like '%" + value + "%') ");
					i++;
				}
				whereSql.append(")");
			}
			whereSql.append(")");
		}
		
		whereSql.append(" and HPI.END_TIME_ is not null ");
		
		if (StringUtils.isNotBlank(request.getOrdering())) {
			whereSql.append(" order by HPI.START_TIME_ " + request.getOrdering());
		}
		baseSql.append(whereSql.toString());
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		countSql.append(whereSql.toString());
		long count = query.sql(countSql.toString()).count();
		
		PagingResult pagingResult = new PagingResult();
		pagingResult.setPageNumber(request.getPageNumber());
		pagingResult.setPageSize(request.getPageSize());
		pagingResult.setRecordNumber((int)count);
		response.setPagingResult(pagingResult);
		List<GetTaskListVO> vos = new ArrayList<GetTaskListVO>();
		for (HistoricProcessInstance hpi : hpis) {
			ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = getHistoryService()
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			List<HistoricActivityInstance> finishedTasks = getHistoryService().createHistoricActivityInstanceQuery().finished()
					.processInstanceId(hpi.getId()).orderByHistoricActivityInstanceEndTime().desc().list();
			HistoricActivityInstance hai = null;
			for (HistoricActivityInstance finishedTask : finishedTasks) {
				if (finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_START_EVENT) 
						|| finishedTask.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_USER_TASK)) {
					hai = finishedTask;
					break;
				}
			}
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			vos.add(GetTaskListVO.toVO(hpi, pd, variableMap, hai));
		}
		response.setTasks(vos);
		
		return response;
	}

	private void validateParam(String processInstanceId) {
		Character[] validCharacter = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		List<Character> characterList = Arrays.asList(validCharacter);
		for (int i = 0; i < processInstanceId.length(); i++) {
			boolean result = characterList.contains(processInstanceId.charAt(i));
			if (!result) {
				throw new RequestParameterValidateException("参数流程编号存在违法字符!");
			}
		}
		
	}
	
}
