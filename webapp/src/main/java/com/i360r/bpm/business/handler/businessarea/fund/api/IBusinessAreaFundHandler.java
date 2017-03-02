package com.i360r.bpm.business.handler.businessarea.fund.api;

import java.math.BigDecimal;

import com.i360r.oas.api.internal.rs.businessarea.fund.CreateLoanApplyRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateLoanRefundRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReimburseRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReservedCashApplyRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReservedCashRefundRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateRevenueApplyRequest;

public interface IBusinessAreaFundHandler {
	
	public String getLogisticsDirector(String stationManagerCode);
	
	public String getCOO();
	
	public String getCEO();
	
	public BigDecimal getApproveLimitByEmployeePositionCode(String employeePositionCode);
	
	public boolean isMoreThanLogisticsDirectorApproveLimit(String stationManagerCode, BigDecimal amount) throws Exception;
	
	public boolean isMoreThanCOOApproveLimit(BigDecimal amount) throws Exception;
	
	public String getLogisticsDirectorByBusinessAreaCode(String businessAreaCode);
	
	public String getStationmaster(String businessAreaCode); 
	
	public void createReimburse(CreateReimburseRequest request) throws Exception;
	
	public void createRevenueApply(CreateRevenueApplyRequest request) throws Exception;
	
	public void createReservedCashRefund(CreateReservedCashRefundRequest request) throws Exception;
	
	public void createReservedCashApply(CreateReservedCashApplyRequest request) throws Exception;
	
	public void createLoanRefund(CreateLoanRefundRequest request) throws Exception;
	
	public void createLoanApply(CreateLoanApplyRequest request) throws Exception;
	
}
