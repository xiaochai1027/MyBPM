package com.i360r.bpm.business.handler.store.account;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;

import com.i360r.bpm.business.exception.ProcessExistedException;
import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.store.account.api.IStoreAccountHandler;
import com.i360r.bpm.business.handler.store.account.api.StoreAccountVO;
import com.i360r.dop.api.internal.rs.store.CreateStoreRequest;
import com.i360r.dop.api.internal.rs.store.ExistingStoreRequest;
import com.i360r.dop.api.internal.rs.store.ExistingStoreResponse;
import com.i360r.dop.api.internal.rs.store.IStoreInternalService;
import com.i360r.dop.api.internal.rs.store.account.CreateStoreAccountRequest;
import com.i360r.dop.api.internal.rs.store.account.ExistingStoreAccountResponse;
import com.i360r.dop.api.internal.rs.store.account.IStoreAccountInternalService;
import com.i360r.dop.api.internal.rs.store.account.StoreAccountInfoResponse;
import com.i360r.dop.api.internal.rs.store.account.StoreAccountWalletWithdrawRequest;
import com.i360r.dop.api.internal.rs.store.account.SuspendedReasonSO;
import com.i360r.dop.api.internal.rs.vendor.CreateVendorRequest;
import com.i360r.dop.api.internal.rs.vendor.ExistingVendorResponse;
import com.i360r.dop.api.internal.rs.vendor.IVendorInternalService;
import com.i360r.dop.api.internal.rs.vendor.VendorDetailResponse;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.log.Log;

public class StoreAccountHandler extends AbstractBusinessHandler implements IStoreAccountHandler {

	private static final Log LOG = Log.getLog(StoreAccountHandler.class);

	@Resource
	private IStoreAccountInternalService storeAccountInternalServiceClient;

	@Resource
	private IStoreInternalService storeInternalServiceClient;

	@Resource
	private IVendorInternalService vendorInternalServiceServiceClient;

	@Override
	public StoreAccountVO getStoreAccountById(String storeAccountId)
			throws Exception {
		StoreAccountInfoResponse response = storeAccountInternalServiceClient.getStoreAccountInfo(storeAccountId);
		checkResponseSuccess(response);
		StoreAccountVO vo = new StoreAccountVO();
		vo.setStoreAccountMobile(response.getStoreAccountMobile());
		vo.setUseableWallet(response.getUseableWallet());
		vo.setStoreAccountStatusCode(response.getStoreAccountStatusCode());
		return vo;
	}

	@Override
	public void changeStoreAccountStatus(String storeAccountId,
			String statusCode) throws Exception {
		ServiceResponse response = storeAccountInternalServiceClient.changeStoreAccountStatus(storeAccountId, statusCode, SuspendedReasonSO.WITHDRAW.getCode());
		checkResponseSuccess(response);
	}

	@Override
	public void updateStoreAccountWallet(String storeAccountCode,
			BigDecimal amount) throws Exception {
		ServiceResponse response = storeAccountInternalServiceClient.updateStoreAccountWallet(storeAccountCode, amount);
		checkResponseSuccess(response);
	}

	@Override
	public void storeAccountWalletWithdraw(
			StoreAccountWalletWithdrawRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = storeAccountInternalServiceClient.withdrawWallet(request);
		} catch (Exception e) {
			LOG.error("withdraw wallet failed!", e);
			throw new Exception("withdraw wallet failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void createStoreAccount(CreateStoreAccountRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = storeAccountInternalServiceClient.createStoreAccount(request);
		} catch (Exception e) {
			LOG.error("create store account failed!", e);
			throw new Exception("create store account failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void createStore(CreateStoreRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = storeInternalServiceClient.createStore(request);
		} catch (Exception e) {
			LOG.error("create store failed!", e);
			throw new Exception("create store failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void createVendor(CreateVendorRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = vendorInternalServiceServiceClient.createVendor(request);
		} catch (Exception e) {
			LOG.error("create vendor failed!", e);
			throw new Exception("create vendor failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void validateStoreAccount(String accountName, String accountMobile) throws Exception {
		ExistingStoreAccountResponse response = storeAccountInternalServiceClient.validateStoreAccount(accountName, accountMobile);

		if (BooleanUtils.isTrue(response.getExisting())) {
			throw new ProcessExistedException(response.getExistMessage());
		}
	}

	@Override
	public void validateStore(String address, String businessAreaId, String name) throws Exception {
		ExistingStoreRequest request = new ExistingStoreRequest();
		request.setName(name);
		request.setBusinessAreaId(businessAreaId);
		request.setAddress(address);

		ExistingStoreResponse response = storeInternalServiceClient.validateStore(request);

		if (BooleanUtils.isTrue(response.getExisting())) {
			throw new ProcessExistedException(response.getExistMessage());
		}
	}

    @Override
    public VendorDetailResponse getVendorDetail(String vendorId) throws Exception {
    	VendorDetailResponse response = null;
        try {
            response = vendorInternalServiceServiceClient.getVendorDetail(vendorId);

        } catch (Exception e) {
            LOG.error("get vendor detail failed!", e);
            throw new Exception("get vendor detail failed!", e);
        }
        checkResponseSuccess(response);

        return response;
    }

    @Override
    public void validateVendor(String vendorName) throws Exception {
        ExistingVendorResponse response = vendorInternalServiceServiceClient.validateVender(vendorName);

        if (BooleanUtils.isTrue(response.getExisting())) {
            throw new ProcessExistedException(response.getExistMessage());
        }
    }
}
