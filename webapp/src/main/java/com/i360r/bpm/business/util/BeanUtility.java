package com.i360r.bpm.business.util;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.framework.bean.context.BeanContextUtility;

/**
 * lazy注入的方式解决循环依赖问题
 * 
 * @author liyong
 *
 */
public class BeanUtility {
	
	private static IMessageNotifyHandler messageNotifyHandler;

	private static RuntimeService runtimeService;
	
	private static HistoryService historyService;
	
	private static TaskService taskService;
	
	private static RepositoryService repositoryService;
	
	public static IMessageNotifyHandler getMessageNotifyHandler() {
		if (messageNotifyHandler == null) {
			messageNotifyHandler = BeanContextUtility.getBean(IMessageNotifyHandler.class);
		}
		return messageNotifyHandler;
	}
	
	public static RuntimeService getRuntimeService() {
		if (runtimeService == null) {
			runtimeService = BeanContextUtility.getBean(RuntimeService.class);
		}
		return runtimeService;
	}
	
	public static HistoryService getHistoryService() {
		if (historyService == null) {
			historyService = BeanContextUtility.getBean(HistoryService.class);
		}
		return historyService;
	}
	
	public static TaskService getTaskService() {
		if (taskService == null) {
			taskService = BeanContextUtility.getBean(TaskService.class);
		}
		return taskService;
	}
	
	public static RepositoryService getRepositoryService() {
		if (repositoryService == null) {
			repositoryService = BeanContextUtility.getBean(RepositoryService.class);
		}
		return repositoryService;
	}
}
