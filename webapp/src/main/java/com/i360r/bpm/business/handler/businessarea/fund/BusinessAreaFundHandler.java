package com.i360r.bpm.business.handler.businessarea.fund;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.businessarea.fund.api.IBusinessAreaFundHandler;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionResponse;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionSO;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionsResponse;
import com.i360r.cds.api.internal.rs.employee.position.IEmployeePositionInternalService;
import com.i360r.cds.api.internal.rs.employee.position.PositionCodeSO;
import com.i360r.cds.api.internal.rs.employee.position.SearchEmployeePositionRequest;
import com.i360r.cds.api.internal.rs.employee.position.SearchSuperiorEmployeePositionRequest;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.StatusCodeManager;
import com.i360r.framework.util.CollectionUtility;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateLoanApplyRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateLoanRefundRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReimburseRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReservedCashApplyRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReservedCashRefundRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateRevenueApplyRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.IBusinessAreaFundInternalService;

public class BusinessAreaFundHandler extends AbstractBusinessHandler implements IBusinessAreaFundHandler {

	@Resource
	private IBusinessAreaFundInternalService businessAreaFundServiceClient;
	
	@Resource
	private IEmployeePositionInternalService employeePositionInternalServiceClient;
	
	/**
	 * 获取上级物流总监employeePositioncode（老版流程）
	 */
	@Override
	public String getLogisticsDirector(String stationManagerEmployeeCode) {
		SearchSuperiorEmployeePositionRequest request = new SearchSuperiorEmployeePositionRequest();
		request.setEmployeePositionId(stationManagerEmployeeCode);
		request.setIncludeParentLocation(true);
		request.setPositionCode(PositionCodeSO.LOGISTICS_CHIEF_MANAGER.getCode());
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("物流总监不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}
	
	@Override
	public String getCOO() {
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode(PositionCodeSO.COO.getCode());
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("COO不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}
	
	@Override
	public String getCEO() {
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode(PositionCodeSO.CEO.getCode());
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("CEO不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}
	
	//根据员工code得到该员工的审批限额
	@Override
	public BigDecimal getApproveLimitByEmployeePositionCode(String employeePositionCode) {
		
		EmployeePositionSO employeePositionSO = getEmployeePositionByEmployeePositionCode(employeePositionCode);
		
		BigDecimal approveAmountLimit = employeePositionSO.getPosition().getApproveAmountLimit();
		if (approveAmountLimit == null) {
			approveAmountLimit = BigDecimal.ZERO;
		}
		return approveAmountLimit;
		
	}
	
	
	private EmployeePositionSO getEmployeePositionByEmployeePositionCode(String employeePositionCode) {
		
		EmployeePositionResponse response = employeePositionInternalServiceClient.getEmployeePositionById(employeePositionCode);
		checkResponseSuccess(response);
		
		if (response.getEmployeePositionSO() == null) {
			throw new RemoteServerException(">>>EmployeePositionCode:" + employeePositionCode + " not found!");
		}
		
		return response.getEmployeePositionSO();
	}
	
	
	/**
	 * 判断金额是否超出物流总监审批上限(老接口)
	 */
	@Override
	public boolean isMoreThanLogisticsDirectorApproveLimit(String stationManagerEmployeePositionCode, BigDecimal amount) throws Exception {

		if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
			return false;
		}
		
		SearchSuperiorEmployeePositionRequest request = new SearchSuperiorEmployeePositionRequest();
		request.setEmployeePositionId(stationManagerEmployeePositionCode);
		request.setPositionCode(PositionCodeSO.LOGISTICS_CHIEF_MANAGER.getCode());
		request.setIncludeParentLocation(true);
		
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		checkResponseSuccess(response);
		
		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("物流总监不存在");
		}

		EmployeePositionSO employeePositon = response.getEmployeePositons().get(0);
		BigDecimal approveAmountLimit = employeePositon.getPosition().getApproveAmountLimit();
		if (approveAmountLimit == null) {
			approveAmountLimit = new BigDecimal(0);
		}
		
		return amount.compareTo(approveAmountLimit) >= 0;
		
	}

	/**
	 * 判断金额是否超出COO审批上限
	 */
	@Override
	public boolean isMoreThanCOOApproveLimit(BigDecimal amount) throws Exception {
		
		if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
			return false;
		}
		
		String COOPositionCode = PositionCodeSO.COO.getCode();
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode(COOPositionCode);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);

		if (response != null 
				&& response.getResultCode().equals(StatusCodeManager.getSucceedCode())) {
			
			if (response.getEmployeePositons().size() > 0) {
				EmployeePositionSO employeePositon = response.getEmployeePositons().get(0);
				BigDecimal approveAmountLimit = employeePositon.getPosition().getApproveAmountLimit();
				if (approveAmountLimit == null) {
					approveAmountLimit = new BigDecimal(0);
				}
				
				return amount.compareTo(approveAmountLimit) >= 0;
			}
			
			throw new RemoteServerException(">>>PositionCode: " + COOPositionCode + " not found!");
		}
		
		throw new RemoteServerException(">>>Get " + COOPositionCode + " ApproveAmountLimit Failed!");

	}
	
	/**
	 * 获取商圈的物流总监code（老接口）
	 */
	@Override
	public String getLogisticsDirectorByBusinessAreaCode(String businessAreaCode) {
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode(PositionCodeSO.LOGISTICS_CHIEF_MANAGER.getCode());
		request.setLocationId(businessAreaCode);
		request.setIncludeParentLocation(true);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("物流总监不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}

	/**
	 * 获取商圈物流站长
	 */
	@Override
	public String getStationmaster(String businessAreaCode) {
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode(PositionCodeSO.DELIVERY_STATION_MANAGER.getCode());
		request.setLocationId(businessAreaCode);
		request.setIncludeParentLocation(false);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("站长不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}

	/**
	 * 报销申请
	 */
	@Override
	public void createReimburse(
			CreateReimburseRequest request) throws Exception {
		ServiceResponse response = businessAreaFundServiceClient.createReimburse(request);
		checkResponseSuccess(response);
	}

	/**
	 * 站长申请营业额
	 */
	@Override
	public void createRevenueApply(CreateRevenueApplyRequest request) throws Exception {
		ServiceResponse response = businessAreaFundServiceClient.createRevenueApply(request);
		checkResponseSuccess(response);
	}

	/**
	 * 备用金额度降级
	 */
	@Override
	public void createReservedCashRefund(CreateReservedCashRefundRequest request) throws Exception {
		ServiceResponse response = businessAreaFundServiceClient.createReservedCashRefund(request);
		checkResponseSuccess(response);
	}

	/**
	 * 备用金申请
	 */
	@Override
	public void createReservedCashApply(CreateReservedCashApplyRequest request) throws Exception {
		ServiceResponse response = businessAreaFundServiceClient.createReservedCashApply(request);
		checkResponseSuccess(response);
	}

	/**
	 * 借款上缴
	 */
	@Override
	public void createLoanRefund(CreateLoanRefundRequest request) throws Exception {
		ServiceResponse response = businessAreaFundServiceClient.createLoanRefund(request);
		checkResponseSuccess(response);
	}

	/**
	 * 借款申请
	 */
	@Override
	public void createLoanApply(CreateLoanApplyRequest request) throws Exception {
		ServiceResponse response = businessAreaFundServiceClient.createLoanApply(request);
		checkResponseSuccess(response);
	}

}
