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
import com.i360r.bpm.service.rs.process.store.StoreConstants;
import com.i360r.bpm.service.rs.process.store.api.StoreVO;
import com.i360r.dop.api.internal.rs.store.CreateStoreRequest;

/**
 * Created by MengWeiBo on 2016-12-23
 */
public class StoreResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

    @Autowired
    private IStoreAccountHandler storeAccountHandler;

    @Autowired
    private IMessageNotifyHandler messageHandler;

    @Override
    public void onProcessPass(DelegateExecution execution) throws Exception {
        Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

        storeAccountHandler.createStore(toRequest(processVariables));

        messageHandler.notifyApplierPass(execution);

    }

    @Override
    public void onProcessNotPass(DelegateExecution execution) throws Exception {
        messageHandler.notifyApplierNotPass(execution);

    }

    @Override
    public String getProcessDefinitionKey() {
        return StoreConstants.PROCESS_DEFINITION_KEY;
    }

    private static CreateStoreRequest toRequest(Map<String, Object> processVariables) {
        CreateStoreRequest request = new CreateStoreRequest();
        StoreVO detail = ProcessUtility.getProcessObject(processVariables, StoreVO.class);

        request.setAddress(detail.getAddress());
        request.setLongitude(detail.getLongitude());
        request.setLatitude(detail.getLatitude());
        request.setName(detail.getName());
        request.setOnline(detail.getOnline());
        request.setResident(detail.getResident());
        request.setBusinessAreaId(detail.getBusinessAreaId());

        String createdByCode = (String) processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
        String createdByName = (String) processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
        String createdByMobile = (String) processVariables.get(StoreConstants.KEY_CREATED_BY_MOBILE);

        request.setMaintainedById(createdByCode);
        request.setMaintainedByName(createdByName);
        request.setMaintainedByMobile(createdByMobile);

        request.setRemarks(detail.getRemarks());
        request.setPhone1(detail.getPhone1());
        request.setPhone2(detail.getPhone2());
        request.setPhone3(detail.getPhone3());

        request.setVendorId(detail.getVendorId());

        return request;
    }
}
