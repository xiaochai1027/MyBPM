package com.i360r.bpm.data.dao.impl.identity;

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;


public class CustomGroupEntityManager extends AbstractManager implements GroupIdentityManager {

	private IEmployeeHandler employeeHandler;
	
	public CustomGroupEntityManager(IEmployeeHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public Group createNewGroup(String groupId) {
		throw new UnsupportedOperationException();
	}

	public void insertGroup(Group group) {
		throw new UnsupportedOperationException();
	}

	public void updateGroup(Group updatedGroup) {
		throw new UnsupportedOperationException();
	}

	public void deleteGroup(String groupId) {
		throw new UnsupportedOperationException();
	}

	public GroupQuery createNewGroupQuery() {
		return new GroupQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor());
	}

	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		throw new UnsupportedOperationException();
	}

	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		throw new UnsupportedOperationException();
	}

	public List<Group> findGroupsByUser(String userId) {
		return employeeHandler.findGroupsByUser(userId);
	}

	public List<Group> findGroupsByNativeQuery(
			Map<String, Object> parameterMap, int firstResult, int maxResults) {
		throw new UnsupportedOperationException();
	}

	public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isNewGroup(Group group) {
		throw new UnsupportedOperationException();
	}
  
}