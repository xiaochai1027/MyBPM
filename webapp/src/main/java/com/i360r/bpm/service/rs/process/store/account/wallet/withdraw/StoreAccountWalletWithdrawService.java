package com.i360r.bpm.service.rs.process.store.account.wallet.withdraw;

import com.i360r.bpm.business.exception.StoreAccountNotActiveException;
import com.i360r.bpm.business.exception.WithdrawWalletBeyondUseableWalletException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.handler.store.account.api.IStoreAccountHandler;
import com.i360r.bpm.business.handler.store.account.api.StoreAccountVO;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api.IStoreAccountWalletWithdrawService;
import com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api.StoreAccountWalletWithdrawRequest;
import com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api.StoreAccountWalletWithdrawVO;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionSO;
import com.i360r.cds.api.internal.rs.employee.position.PositionCodeSO;
import com.i360r.dop.api.internal.rs.store.account.StoreAccountStatusSO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.DeliveronlyStoreAccountCO;
import com.i360r.framework.extension.context.model.EmployeeCO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class StoreAccountWalletWithdrawService implements IStoreAccountWalletWithdrawService {

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Autowired
	private IStoreAccountHandler storeAccountHandler;

	
	@Override
	public ProcessDetailResponse<StoreAccountWalletWithdrawVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, StoreAccountWalletWithdrawVO.class);
	}

	@Override
	public ServiceResponse startProcess(
			StoreAccountWalletWithdrawRequest storeAccountWalletWithdrawRequest) throws Exception {
		DeliveronlyStoreAccountCO account = SessionContextAccessor.getCurrentAccount();		
		
		StoreAccountVO storeAccountVO = storeAccountHandler.getStoreAccountById(account.getCode());
		if (storeAccountVO.getUseableWallet().compareTo(storeAccountWalletWithdrawRequest.getWithdrawAmount()) < 0) {		
			throw new WithdrawWalletBeyondUseableWalletException("Withdraw Wallet Beyond Useable Wallet");
		}
		if (!storeAccountVO.getStoreAccountStatusCode().equals(StoreAccountStatusSO.ACTIVE.getCode())) {
			throw new StoreAccountNotActiveException("Store Account Not Active");
		}
		storeAccountWalletWithdrawRequest.setStoreAccountId(account.getCode());
		String financeDirectorEmployeePositionCode = employeeHandler.getEmployeePositionByPositionCodeAndLocationId(PositionCodeSO.FINANCE_DIRECTOR.getCode(), null);
		EmployeePositionSO financeDirector = employeeHandler.getEmployeePositionByEmployeePositionCode(financeDirectorEmployeePositionCode);
		EmployeeCO employee = toEmployeeCO(financeDirector);

		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(ProcessConstants.KEY_STORE_ACCOUNT_ID, account.getCode());
		customVariables.put(ProcessConstants.KEY_STORE_ACCOUNT_MOBILE, storeAccountVO.getStoreAccountMobile());
		customVariables.put(ProcessConstants.KEY_STORE_ACCOUNT_WALLET_AMOUNT, storeAccountVO.getUseableWallet());
		processHandler.startProcess(employee, storeAccountWalletWithdrawRequest, StoreAccountWalletWithdrawCreationConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		storeAccountHandler.changeStoreAccountStatus(account.getCode(), StoreAccountStatusSO.SUSPENDED.getCode());
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(ApproveRequest request)
			throws Exception {
		Map<String, Object> customVariables = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(request.getSuggestion())) {
			customVariables.put(ProcessConstants.KEY_STORE_ACCOUNT_WITHDRAW_FAILED_REASON, request.getSuggestion());
		}
		processHandler.completeTask(request, customVariables, StoreAccountWalletWithdrawCreationConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	private EmployeeCO toEmployeeCO(EmployeePositionSO financeDirector) {
		EmployeeCO co = new EmployeeCO();
		co.setCode(financeDirector.getEmployee().getId());
		co.setName(financeDirector.getEmployee().getFullName());
		co.setEmployeePositionCode(financeDirector.getId());
		co.setPositionName(financeDirector.getPosition().getName());
		return co;
	}
}
