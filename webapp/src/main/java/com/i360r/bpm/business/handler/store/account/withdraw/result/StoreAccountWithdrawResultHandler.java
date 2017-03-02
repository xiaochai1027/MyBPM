package com.i360r.bpm.business.handler.store.account.withdraw.result;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.handler.store.account.api.IStoreAccountHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.StoreAccountWalletWithdrawCreationConstants;
import com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api.StoreAccountWalletWithdrawVO;
import com.i360r.dop.api.internal.rs.store.account.StoreAccountStatusSO;
import com.i360r.dop.api.internal.rs.store.account.StoreAccountWalletWithdrawRequest;
import com.i360r.framework.log.Log;

public class StoreAccountWithdrawResultHandler extends AbstractEngineHandler  implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(StoreAccountWithdrawResultHandler.class);
	
	
	@Autowired
	private IStoreAccountHandler storeAccountHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>StoreAccountWalletWithdrawCreationConstants Pass!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		StoreAccountWalletWithdrawVO detail = ProcessUtility.getProcessObject(processVariables, StoreAccountWalletWithdrawVO.class);
		
		StoreAccountWalletWithdrawRequest request = new StoreAccountWalletWithdrawRequest();
		request.setStoreAccountId(detail.getStoreAccountId());
		request.setWithdrawAccount(detail.getWithdrawAccount());
		request.setWithdrawAmount(detail.getWithdrawAmount());
		request.setCreatedById((String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE));
		request.setCreatedByName((String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		request.setWithdrawTime(new Date());
		request.setSuccessed(true);
		request.setStoreAccountWalletPayChannelCode(detail.getPayChannelCode());
		storeAccountHandler.storeAccountWalletWithdraw(request);
		storeAccountHandler.changeStoreAccountStatus(detail.getStoreAccountId(), "ACTIVE");
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingCheckoutCreationConstants deny!");
		
		messageHandler.notifyApplierNotPass(execution);
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		StoreAccountWalletWithdrawVO detail = ProcessUtility.getProcessObject(processVariables, StoreAccountWalletWithdrawVO.class);
		
		StoreAccountWalletWithdrawRequest request = new StoreAccountWalletWithdrawRequest();
		request.setStoreAccountId(detail.getStoreAccountId());
		request.setWithdrawAccount(detail.getWithdrawAccount());
		request.setWithdrawAmount(detail.getWithdrawAmount());
		request.setCreatedById((String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE));
		request.setCreatedByName((String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		request.setWithdrawTime(new Date());
		request.setSuccessed(false);
		request.setStoreAccountWalletPayChannelCode(detail.getPayChannelCode());
		request.setFailedReason(detail.getFailedReason());
		storeAccountHandler.storeAccountWalletWithdraw(request);
		storeAccountHandler.changeStoreAccountStatus(detail.getStoreAccountId(), StoreAccountStatusSO.ACTIVE.getCode());
	}

	@Override
	public String getProcessDefinitionKey() {
		
		return StoreAccountWalletWithdrawCreationConstants.PROCESS_DEFINITION_KEY;
	}

}
