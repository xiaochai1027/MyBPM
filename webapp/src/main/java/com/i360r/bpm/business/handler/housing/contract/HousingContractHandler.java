package com.i360r.bpm.business.handler.housing.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.housing.contract.api.IHousingContractHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.housing.contract.api.HousingContractVO;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CreateHousingContractRequest;
import com.i360r.oas.api.internal.rs.house.CreateHousingRentReimburseRequest;
import com.i360r.oas.api.internal.rs.house.HouseRentReimburseResponse;
import com.i360r.oas.api.internal.rs.house.IHousingInternalService;
import com.i360r.oas.api.internal.rs.house.ReletHousingContractRequest;


public class HousingContractHandler  extends AbstractBusinessHandler implements IHousingContractHandler {

	private static final Log LOG = Log.getLog(HousingContractHandler.class);
	
	@Resource
	private IHousingInternalService housingInternalServiceClient;

	@Autowired
	protected IEmployeeHandler employeeHandler;

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void createHousingContract(
			CreateHousingContractRequest housingContractRequest) throws Exception {
		ServiceResponse response = null;
		try {
			response = housingInternalServiceClient.createHousingContract(housingContractRequest);
		} catch (Exception e) {
			LOG.error("Create housingContract failed!", e);
			throw new Exception("Create housingContract failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void reletHousingContract(
			ReletHousingContractRequest reletHousingContractRequest) throws Exception {
		ServiceResponse response = null;
		try {
			response = housingInternalServiceClient.reletHousingContract(reletHousingContractRequest);
		} catch (Exception e) {
			LOG.error("relet housingContract failed!", e);
			throw new Exception("relet housingContract failed!", e);
		}
		checkResponseSuccess(response);
	}
	
	
	@Override
	public Integer createHousingRentReimburse(String executionId) throws Exception{
		HouseRentReimburseResponse response;
		Map<String, Object> variables = runtimeService.getVariables(executionId);
		CreateHousingRentReimburseRequest request = toHousingRentReimburseRequest(variables);

		try {
			response = housingInternalServiceClient.createHousingRentReimburseBeforeContract(request);
		} catch (Exception e) {
			LOG.error("CreateHousingRentReimburseRequest!", e);
			throw new Exception("CreateHousingRentReimburseRequest failed!", e);
		}

		checkResponseSuccess(response);
		return response.getReimburseId();
	}

	private CreateHousingRentReimburseRequest toHousingRentReimburseRequest(Map<String, Object> variables) {
		CreateHousingRentReimburseRequest request = new CreateHousingRentReimburseRequest();
		HousingContractVO detail = ProcessUtility.getProcessObject(variables, HousingContractVO.class);

		request.setBusinessAreaCode(detail.getBusinessAreaCode());
		request.setBusinessAreaName(detail.getBusinessAreaName());
		request.setCityCode(detail.getCityCode());
		request.setCityName(detail.getCityName());
		request.setEndDate(detail.getLatestPayEndDate());
		request.setBeginDate(detail.getLatestPayStartDate());

		//经办人信息、报销金额
		BigDecimal rentAmount = detail.getMonthlyRentFee().multiply(new BigDecimal(detail.getRentPaymentNumber()));
		request.setAmount(rentAmount);
		request.setNote(detail.getNote());
		request.setOperatorBankCardNumber(detail.getOperatorBankCardNumber());
		request.setOperatorInterbankNumber(detail.getOperatorInterbankNumber());
		request.setOperatorCode(detail.getOperatorCode());
		request.setOperatorName(detail.getOperatorName());

		// 创建人、付款人信息
		String createdByCode = (String)variables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		String createdByName = (String)variables.get(ProcessConstants.KEY_CREATED_BY_NAME);
		Date createTime = (Date)variables.get(ProcessConstants.KEY_CREATE_TIME);
		String paidByCode = (String)variables.get(ProcessConstants.KEY_PAID_BY_CODE);
		Date payTime = (Date)variables.get(ProcessConstants.KEY_PAY_TIME);

		EmployeeSO employee = employeeHandler.getEmployeeByCode(paidByCode);
		request.setPaidByCode(paidByCode);
		request.setPaidByName(employee.getFullName());
		request.setActualPaymentDate(detail.getActualPaymentDate());
		request.setPayDate(payTime);

		request.setCreatedByCode(createdByCode);
		request.setCreatedByName(createdByName);
		request.setCreateTime(createTime);

		return request;
	}
}
