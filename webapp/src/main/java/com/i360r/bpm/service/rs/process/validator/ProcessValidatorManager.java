package com.i360r.bpm.service.rs.process.validator;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.log.Log;

public class ProcessValidatorManager implements BeanPostProcessor {
	private final static Log LOG = Log.getLog(ProcessValidatorManager.class);
	
	private Map<ProcessValidatorType, IProcessValidator<?>> validators = 
			new EnumMap<ProcessValidatorType, IProcessValidator<?>>(ProcessValidatorType.class);
	
	public <T> boolean validate(EmployeeCO employee, String processDefinitionKey, String processInstanceId, T request) {
		
		ProcessUniqueVariableRequest uniqueVariableRequest = ProcessUtility.getProcessUniqueVariableRequest(request);
		if (uniqueVariableRequest != null) {
			if (!validate(employee, processDefinitionKey, processInstanceId, uniqueVariableRequest)) {
				return false;
			}			
		} 
		
		if (request instanceof INeedValidate) {
			return validate(employee, processDefinitionKey, processInstanceId, (INeedValidate)request);
		}
		
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean validate(EmployeeCO employee, String processDefinitionKey, String processInstanceId, INeedValidate needValidate) {
		if (needValidate != null) {
			ProcessValidatorType type = needValidate.getType();
			
			IProcessValidator validator = validators.get(type);
			if (validator == null) {
				LOG.warn("validator not exist {}", type);
				return true;
			}
			
			return validator.validate(employee, processDefinitionKey, processInstanceId, needValidate);
		}
		
		return true;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		if (bean instanceof IProcessValidator) {
			IProcessValidator<?> validator = (IProcessValidator<?>)bean;
			ProcessValidatorType type = validator.getType();
			if (type == null) {
				throw new NullPointerException();
			}
			if (validators.containsKey(type)) {
				throw new RuntimeException("duplicate validator : " + type.name());
			}
			validators.put(type, validator);
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
