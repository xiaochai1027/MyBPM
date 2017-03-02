package com.i360r.bpm.service.rs.process.validator.impl;

import java.util.Iterator;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;

import com.i360r.bpm.business.exception.UniqueVariableRequestException;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariableEntity;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.bpm.service.rs.process.validator.IProcessValidator;
import com.i360r.bpm.service.rs.process.validator.ProcessValidatorType;
import com.i360r.framework.extension.context.model.EmployeeCO;

/**
 * 流程定义了唯一变量，同类型同一变量值只能创建一个 
 * 
 * @author liyong
 */
public class ProcessUniqueVariableValidator extends AbstractEngineHandler implements IProcessValidator<ProcessUniqueVariableRequest> {

	@Override
	public boolean validate(EmployeeCO employee, String processDefinitionKey, String processInstanceId,
			ProcessUniqueVariableRequest request) {
		
		List<ProcessVariableEntity> variables = request.getUniqueVariables();
		if (variables != null && variables.size() > 0) {
			for (ProcessVariableEntity variable : variables) {
				if (variable.getValue() == null) {
					throw new UniqueVariableRequestException(variable.getShowName());
				}
				HistoricProcessInstanceQuery query = 
						getHistoryService().createHistoricProcessInstanceQuery()
						.processDefinitionKey(processDefinitionKey)
						.variableValueEquals(variable.getKey(), variable.getValue());
				if (variable.getUniqueScope() == ProcessUniqueScope.CREATOR_UNFINISHED) {
					query = query
							.variableValueEquals(ProcessConstants.KEY_CREATED_BY_CODE, employee.getEmployeePositionCode())
							.unfinished();
				} else if (variable.getUniqueScope() == ProcessUniqueScope.UNFINISHED) {
					query = query.unfinished();
				} else if (variable.getUniqueScope() == ProcessUniqueScope.UNFINISHED_PASS) {
					query = query
							.or()
							.unfinished()
							.variableValueEquals(ProcessConstants.KEY_PASS, true);
				}
				
				List<HistoricProcessInstance> list = query.list();
				if (list != null) {
					if (processInstanceId != null) {
						Iterator<HistoricProcessInstance> ite = list.iterator();
						while (ite.hasNext()) {
							HistoricProcessInstance hpi = ite.next();
							if (hpi.getId().equals(processInstanceId)) {
								ite.remove();
							}
						}
					}
					if (list.size() > 0) {
						throw new UniqueVariableRequestException(variable.getShowName());
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public ProcessValidatorType getType() {
		return ProcessValidatorType.UNIQUE_VARIABLE;
	}
}
