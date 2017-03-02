package com.i360r.bpm.business.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.i360r.bpm.business.exception.UniqueVariableRequestException;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.business.model.ProcessVariableEntity;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class ProcessUtility {

	public static Map<String, Object> getProcessVariables(Map<String, Object> existVariables, Object obj) {
		return getProcessVariables(existVariables, obj, true);
	}
	
	public static Map<String, Object> getProcessVariables(Map<String, Object> existVariables, Object obj, boolean checkRequired) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (obj == null) {
			return result;
		}
		
		String keywordSeed = null;
		if (existVariables != null) {
			keywordSeed = (String)existVariables.get(ProcessConstants.KEY_KEYWORD_SEED);
		}
		JSONObject keywordSeedObj = JSON.parseObject(keywordSeed);
		if (keywordSeedObj == null) {
			keywordSeedObj = new JSONObject();
		}
		
		boolean keywordChanged = false;
		
		Class<?> clazz = obj.getClass();
		while (!Object.class.equals(clazz)) {
			
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = null;
				try {
					value = field.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				ProcessVariable processVariable = field.getAnnotation(ProcessVariable.class);
				if (processVariable != null) {
					if (value == null) {
						if (checkRequired && processVariable.required()) {
							throw new IllegalRequestException();
						}
						continue;
					}
					
					String key = processVariable.key();
					if (StringUtils.isEmpty(key)) {
						key = field.getName();
					}
					result.put(key, value);
					
					if (processVariable.isKeyword()) {
						int keywordOrder = processVariable.keywordOrder();
						String sValue = value.toString();
						keywordSeedObj.put(getKeywordKey(keywordOrder, key), sValue);
						keywordChanged = true;
					}
				}
			}
			
			clazz = clazz.getSuperclass();
		}
		
		if (keywordChanged) {
			
			// 按关键字变量的顺序排序
			Set<Integer> keywordOrderSet = new TreeSet<Integer>();
			List<String> keywordVariableNameList = new ArrayList<String>();
			
			Set<String> keySet = keywordSeedObj.keySet();
			for (String key : keySet) {
				String[] orderVariableNameArray = StringUtils.split(key, "_");
				if (orderVariableNameArray != null && orderVariableNameArray.length == 2) {
					keywordOrderSet.add(new Integer(orderVariableNameArray[0]));
					keywordVariableNameList.add(orderVariableNameArray[1]);
				}
			}
			
			if (!keywordOrderSet.isEmpty()) { // 与老的兼容
				StringBuilder keywords = new StringBuilder();
				for (Integer keywordOrder : keywordOrderSet) {
					for (String keywordVariableName : keywordVariableNameList) {
						String key = getKeywordKey(keywordOrder, keywordVariableName);
						if (keySet.contains(key)) {
							if (StringUtils.isNotBlank(keywordSeedObj.get(key).toString())) {
								keywords.append(keywordSeedObj.get(key).toString()).append(",");
							}
						}
					}
				}
				
				result.put(ProcessConstants.KEY_KEYWORD_SEED, keywordSeedObj.toJSONString());
				result.put(ProcessConstants.KEY_KEYWORD, keywords.deleteCharAt(keywords.length() - 1).toString());	
			}				
		}
		
		return result;
	}
	
	private static String getKeywordKey(int keywordOrder, String keywordVariableName) {
		return keywordOrder + "_" + keywordVariableName;
	}
	
	public static <T> T getProcessObject(Map<String, Object> variables, Class<T> clazz) {
		try {
			T obj = clazz.newInstance();
			
			Class<?> curClazz = clazz;
			
			while (!Object.class.equals(curClazz)) {
				
				Field[] fields = curClazz.getDeclaredFields();
				for (Field field : fields) {
					ProcessVariable processVariable = field.getAnnotation(ProcessVariable.class);
					if (processVariable != null) {
						field.setAccessible(true);
						
						String key = processVariable.key();
						if (StringUtils.isEmpty(key)) {
							key = field.getName();
						}
						try {
							field.set(obj, variables.get(key));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}				
				}
				
				curClazz = curClazz.getSuperclass();
			}
			
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ProcessUniqueVariableRequest getProcessUniqueVariableRequest(Object obj) {
		if (obj == null) {
			return null;
		}
		
		List<ProcessVariableEntity> result = new ArrayList<ProcessVariableEntity>();
		
		Class<?> clazz = obj.getClass();
		while (!Object.class.equals(clazz)) {
			
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				ProcessVariable uniqueVariable = field.getAnnotation(ProcessVariable.class);
				if (uniqueVariable == null || !uniqueVariable.isUnique()) {
					continue;
				}
				
				field.setAccessible(true);
				Object value = null;
				try {
					value = field.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				if (value == null) {
					if (uniqueVariable.required()) {
						throw new UniqueVariableRequestException(uniqueVariable.showName());
					}
					continue;
				}
				
				String key = uniqueVariable.key();
				if (StringUtils.isEmpty(key)) {
					key = field.getName();
				}
				
				ProcessVariableEntity entity = new ProcessVariableEntity();
				entity.setKey(key);
				entity.setValue(value);
				entity.setShowName(uniqueVariable.showName());
				entity.setUniqueScope(uniqueVariable.uniqueScope());
				
				result.add(entity);					
			}
			
			clazz = clazz.getSuperclass();
		}
		
		if (result.isEmpty()) {
			return null;
		}
		ProcessUniqueVariableRequest request = new ProcessUniqueVariableRequest();
		request.setUniqueVariables(result);
		
		return request;
	}
	
	/**
	 * @param variables
	 * @return taskId -> variableName -> variableValue
	 */
	public static Map<String, Map<String, Object>> getVariableMap(List<HistoricVariableInstance> variables) {
		Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();
		if (variables == null) {
			return result;
		}
		for (HistoricVariableInstance hvi: variables) {
			String taskId = hvi.getTaskId();
			if (StringUtils.isBlank(taskId)) {
				taskId = ProcessConstants.KEY_NON_TASK;
			}
			
			Map<String, Object> taskVariables = result.get(taskId);
			if (taskVariables == null) {
				taskVariables = new HashMap<String, Object>();
				result.put(taskId, taskVariables);
			}
			
			taskVariables.put(hvi.getVariableName(), hvi.getValue());
			
		}
		return result;
	}
	
	public static boolean getBooleanValue(Map<String, Object> variables, String key) {
		Object obj = variables.get(key);
		if (obj == null) {
			return false;
		}
		if (obj.getClass().equals(boolean.class) || obj.getClass().equals(Boolean.class)) {
			return (Boolean) obj;
		}
		return false;
	}
	
	public static String getStringFromSet(Set<String> sets) {
		if (sets == null || sets.isEmpty()) {
			return null;
		}
		
		String value = sets.toString();
		return value.replaceFirst("\\[", "").replaceAll("\\]$", "");
	}
	
}
