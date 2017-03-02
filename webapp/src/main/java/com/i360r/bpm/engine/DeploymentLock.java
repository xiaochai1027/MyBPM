package com.i360r.bpm.engine;

import com.hazelcast.core.ILock;
import com.i360r.framework.hazelcast.AbstractHazelcast;

public class DeploymentLock extends AbstractHazelcast {

	private ILock deployLock;
	
	@Override
	public void init() throws Exception {
		deployLock = getHazelcast().getLock(getHolderName("DeployLock"));
	}

	public void lock() {
		deployLock.lock();
	}
	
	public void unlock() {
		deployLock.unlock();
	}
	
}
