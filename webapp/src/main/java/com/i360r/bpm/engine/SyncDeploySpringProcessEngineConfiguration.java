package com.i360r.bpm.engine;

import org.activiti.engine.ProcessEngine;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.framework.log.Log;

public class SyncDeploySpringProcessEngineConfiguration extends
		SpringProcessEngineConfiguration {
	private static final Log LOG = Log.getLog(SyncDeploySpringProcessEngineConfiguration.class);

	@Autowired
	private DeploymentLock lock;
	
	@Override
	protected void autoDeployResources(ProcessEngine processEngine) {
		lock.lock();
		
		LOG.info("start auto deploy resources");
		
		try {
			super.autoDeployResources(processEngine);
		} finally {
			lock.unlock();
		}
		
		LOG.info("finish auto deploy resources");
	}
	
}
