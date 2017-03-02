package com.i360r.bpm.business.handler.store.account.api;

import java.math.BigDecimal;

import com.i360r.dop.api.internal.rs.store.CreateStoreRequest;
import com.i360r.dop.api.internal.rs.store.account.CreateStoreAccountRequest;
import com.i360r.dop.api.internal.rs.store.account.StoreAccountWalletWithdrawRequest;
import com.i360r.dop.api.internal.rs.vendor.CreateVendorRequest;
import com.i360r.dop.api.internal.rs.vendor.VendorDetailResponse;



public interface IStoreAccountHandler {

    public StoreAccountVO getStoreAccountById(String storeAccountId) throws Exception;

    public void changeStoreAccountStatus(String storeAccountId, String statusCode) throws Exception;

    public void updateStoreAccountWallet(String storeAccountCode, BigDecimal amount) throws Exception;

    public void storeAccountWalletWithdraw(StoreAccountWalletWithdrawRequest request) throws Exception;

    public void createStoreAccount(CreateStoreAccountRequest request) throws Exception;

    public void createStore(CreateStoreRequest request) throws Exception;

    public void createVendor(CreateVendorRequest request) throws Exception;

    public void validateStoreAccount(String accountName, String accountMobile) throws Exception;

    public void validateStore(String address, String businessAreaId, String name) throws Exception;

    public void validateVendor(String vendorName) throws Exception;

    public VendorDetailResponse getVendorDetail(String vendorId) throws Exception ;

    }
