package com.i360r.bpm.business.handler.process;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.engine.AbstractEngineHandler;

public class ProcessResultHandlerManager extends AbstractEngineHandler implements BeanPostProcessor {

	private Map<String, IProcessResultHandler> handlers = new HashMap<String, IProcessResultHandler>();

	/**
	 * 流程处理通过
	 * 
	 * @param execution
	 * @throws Exception 
	 */
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		IProcessResultHandler handler = getHandler(execution);
		if (handler != null) {
			handler.onProcessPass(execution);	
		}
		
		// 这个放在最后
		getRuntimeService().setVariable(execution.getId(), ProcessConstants.KEY_PASS, true);
	}
	
	/**
	 * 流程处理未通过
	 * 
	 * @param execution
	 * @throws Exception 
	 */
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		IProcessResultHandler handler = getHandler(execution);
		if (handler != null) {
			handler.onProcessNotPass(execution);	
		}
		
		// 这个放在最后
		getRuntimeService().setVariable(execution.getId(), ProcessConstants.KEY_PASS, false);
	}
	
	public void onProcessNotPassAndFinish(DelegateExecution execution) throws Exception {
		onProcessNotPass(execution);
		getRuntimeService().deleteProcessInstance(execution.getProcessInstanceId(), "删除请假流程");
	}

	private IProcessResultHandler getHandler(DelegateExecution execution) {
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(execution.getProcessDefinitionId()).singleResult();
		if (pd != null) {
			return handlers.get(pd.getKey());
		}
		return null;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof IProcessResultHandler) {
			IProcessResultHandler handler = (IProcessResultHandler) bean;
			handlers.put(handler.getProcessDefinitionKey(), handler);
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	
}
