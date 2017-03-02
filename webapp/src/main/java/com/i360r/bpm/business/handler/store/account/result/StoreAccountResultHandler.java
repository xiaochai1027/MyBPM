package com.i360r.bpm.business.handler.store.account.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.handler.store.account.api.IStoreAccountHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.store.account.StoreAccountConstants;
import com.i360r.bpm.service.rs.process.store.account.api.StoreAccountVO;
import com.i360r.dop.api.internal.rs.store.account.CreateStoreAccountRequest;

/**
 * Created by MengWeiBo on 2016-12-23
 */
public class StoreAccountResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

    @Autowired
    private IStoreAccountHandler storeAccountHandler;

    @Autowired
    private IMessageNotifyHandler messageHandler;

    @Override
    public void onProcessPass(DelegateExecution execution) throws Exception {
        Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

        storeAccountHandler.createStoreAccount(toRequest(processVariables));

        messageHandler.notifyApplierPass(execution);

    }

    @Override
    public void onProcessNotPass(DelegateExecution execution) throws Exception {
        messageHandler.notifyApplierNotPass(execution);

    }

    @Override
    public String getProcessDefinitionKey() {
        return StoreAccountConstants.PROCESS_DEFINITION_KEY;
    }

    private static CreateStoreAccountRequest toRequest(Map<String, Object> processVariables) {
        CreateStoreAccountRequest request = new CreateStoreAccountRequest();
        StoreAccountVO detail = ProcessUtility.getProcessObject(processVariables, StoreAccountVO.class);

        request.setAccountMobile(detail.getAccountMobile());
        request.setAccountName(detail.getAccountName());
        request.setAlertAmount(detail.getAlertAmount());
        request.setAccountActivated(detail.getAccountStatus());

        request.setVendorName(detail.getVendorName());
        request.setContactName(detail.getContactName());
        request.setContactMobile(detail.getContactMobile());
        request.setVendorDescription(detail.getVendorDescription());
        request.setHasPartner(detail.getHasPartner());
        request.setDistanceLimit(detail.getDistanceLimit());
        request.setPlannedCompleteTimeInterval(detail.getPlannedCompleteTimeInterval());
        request.setVendorActivated(detail.getVendorStatus());
        request.setParttimeManual(detail.getPartTimeManual());
        request.setSeparatePaidSalary(detail.getSeparatePaidSalary());
        request.setUseFixedRoyaltiesConfig(detail.getUseFixedRoyaltiesConfig());
        request.setParttimeFixedRoyaltiesAmount(detail.getParttimeFixedRoyaltiesAmount());
        request.setFulltimeFixedRoyaltiesAmount(detail.getFulltimeFixedRoyaltiesAmount());
        request.setRemarks(detail.getRemarks());
        request.setDeliverTypeCode(detail.getDeliverTypeCode());
        request.setBusinessTypeCode(detail.getBusinessTypeCode());

        String createdByCode = (String) processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
        String createdByName = (String) processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
        request.setCreatedByCode(createdByCode);
        request.setCreatedByName(createdByName);

        request.setDeliverFeeRoundingTypeCode(detail.getDeliverFeeRoundingTypeCode());
        request.setSettlementTypeCode(detail.getSettlementTypeCode());

        request.setDeliverFeeBase(detail.getDeliverFeeBase());
        request.setDeliverFeeDistanceBase(detail.getDeliverFeeDistanceBase());
        request.setDeliverFeeDistanceUnit(detail.getDeliverFeeDistanceUnit());
        request.setDeliverFeeDistanceAmmountPerUnit(detail.getDeliverFeeDistanceAmmountPerUnit());
        request.setDeliverFeeDistanceMaxAmmount(detail.getDeliverFeeDistanceMaxAmmount());

        request.setDeliverFeeWeightBase(detail.getDeliverFeeWeightBase());
        request.setDeliverFeeWeightUnit(detail.getDeliverFeeWeightUnit());
        request.setDeliverFeeWeightAmmountPerUnit(detail.getDeliverFeeWeightAmmountPerUnit());
        request.setDeliverFeeWeightMaxAmmount(detail.getDeliverFeeWeightMaxAmmount());

        request.setDeliverFeeOrderPriceBase(detail.getDeliverFeeOrderPriceBase());
        request.setDeliverFeeOrderPriceDiscountRate(detail.getDeliverFeeOrderPriceDiscountRate());
        request.setDeliverFeeOrderPriceMaxAmmount(detail.getDeliverFeeOrderPriceMaxAmmount());

        request.setDeliverFeeTimeslot1From(detail.getDeliverFeeTimeslot1From());
        request.setDeliverFeeTimeslot1To(detail.getDeliverFeeTimeslot1To());
        request.setDeliverFeeTimeslot2From(detail.getDeliverFeeTimeslot2From());
        request.setDeliverFeeTimeslot2To(detail.getDeliverFeeTimeslot2To());
        request.setDeliverFeeTimeslot3From(detail.getDeliverFeeTimeslot3From());
        request.setDeliverFeeTimeslot3To(detail.getDeliverFeeTimeslot3To());

        request.setDeliverFeeTimeslot1Ammount(detail.getDeliverFeeTimeslot1Ammount());
        request.setDeliverFeeTimeslot2Ammount(detail.getDeliverFeeTimeslot2Ammount());
        request.setDeliverFeeTimeslot3Ammount(detail.getDeliverFeeTimeslot3Ammount());

        request.setCallBackUrl(detail.getCallBackUrl());
        request.setVersion(detail.getVersion());
        request.setSyncLocation(detail.getSyncLocation());
        request.setLocationProcessRatio(detail.getLocationProcessRatio());

        request.setPerformanceTimeImmediatelyMins(detail.getPerformanceTimeImmediatelyMins());
        request.setPerformanceTimeIntimeMaxMins(detail.getPerformanceTimeIntimeMaxMins());
        request.setPerformanceTimeIntimeMinMins(detail.getPerformanceTimeIntimeMinMins());
        request.setPerformanceDistanceCompleteMeter(detail.getPerformanceDistanceCompleteMeter());

        request.setAllowanceDistanceBase(detail.getAllowanceDistanceBase());
        request.setAllowanceDistanceUnit(detail.getAllowanceDistanceUnit());
        request.setAllowanceDistanceAmmountPerUnit(detail.getAllowanceDistanceAmmountPerUnit());
        request.setAllowanceDistanceMaxAmmount(detail.getAllowanceDistanceMaxAmmount());

        request.setAllowanceWeightBase(detail.getAllowanceWeightBase());
        request.setAllowanceWeightUnit(detail.getAllowanceWeightUnit());
        request.setAllowanceWeightAmmountPerUnit(detail.getAllowanceWeightAmmountPerUnit());
        request.setAllowanceWeightMaxAmmount(detail.getAllowanceWeightMaxAmmount());

        return request;
    }

}
