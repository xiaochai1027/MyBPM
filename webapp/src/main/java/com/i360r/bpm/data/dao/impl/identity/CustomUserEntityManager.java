package com.i360r.bpm.data.dao.impl.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;


public class CustomUserEntityManager extends AbstractManager implements UserIdentityManager {

	private IEmployeeHandler employeeHandler;
	
	public CustomUserEntityManager(IEmployeeHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public User createNewUser(String userId) {
		throw new UnsupportedOperationException();
	}

	public void insertUser(User user) {
		throw new UnsupportedOperationException();
	}

	public void updateUser(User updatedUser) {
		throw new UnsupportedOperationException();
	}

	public User findUserById(String userId) {
		return employeeHandler.findUserById(userId);
	}

	public void deleteUser(String userId) {
		throw new UnsupportedOperationException();
	}

	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		List<User> result = new ArrayList<User>();
		User user = findUserById(query.getId());
		if (user != null) {
			result.add(user);
		}
		return result;
	}

	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		User user = findUserById(query.getId());
		if (user == null) {
			return 0;
		}
		return 1;
	}

	public List<Group> findGroupsByUser(String userId) {
		return employeeHandler.findGroupsByUser(userId);
	}

	public UserQuery createNewUserQuery() {
		return new UserQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor());
	}

	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId,
			String key) {
		throw new UnsupportedOperationException();
	}

	public List<String> findUserInfoKeysByUserIdAndType(String userId,
			String type) {
		throw new UnsupportedOperationException();
	}

	public Boolean checkPassword(String userId, String password) {
		throw new UnsupportedOperationException();
	}

	public List<User> findPotentialStarterUsers(String proceDefId) {
		throw new UnsupportedOperationException();
	}

	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap,
			int firstResult, int maxResults) {
		throw new UnsupportedOperationException();
	}

	public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isNewUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Picture getUserPicture(String userId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setUserPicture(String userId, Picture picture) {
		throw new UnsupportedOperationException();
	}
  
}
