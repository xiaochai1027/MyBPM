package com.i360r.bpm.service.rs.process.vendor;

import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.handler.store.account.api.IStoreAccountHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.vendor.api.CreateVendorRequest;
import com.i360r.bpm.service.rs.process.vendor.api.IVendorService;
import com.i360r.bpm.service.rs.process.vendor.api.VendorVO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2016-12-22
 */
public class VendorService implements IVendorService {

    private static final String POST_CHARGE = "POST_CHARGE";

    @Autowired
    private IProcessHandler processHandler;

    @Autowired
    private IStoreAccountHandler storeAccountHandler;

    @Override
    public ProcessDetailResponse<VendorVO> getDetail(String processInstanceId) {
        return processHandler.getProcessDetail(processInstanceId, VendorVO.class);
    }

    @Override
    public ServiceResponse startProcess(CreateVendorRequest request) throws Exception {

        storeAccountHandler.validateVendor(request.getVendorName());

        EmployeeCO employee = SessionContextAccessor.getCurrentAccount();

        Map<String, Object> variables = new HashMap<String, Object>();
        boolean businessManager = false;
        boolean businessDevelopmentDirectorApply = false;
        boolean postChargeable = false;

        if (VendorConstants.KEY_BUSINESS_MANAGER.equals(employee.getPositionCode())) {
            businessManager = true;

        } else if (VendorConstants.KEY_BUSINESS_DEVELOPMENT_DIRECTOR.equals(employee.getPositionCode())) {
            businessDevelopmentDirectorApply = true;
        }

        if (POST_CHARGE.equals(request.getSettlementTypeCode())){
            postChargeable = true;
        }

        variables.put(VendorConstants.KEY_POST_CHARGEABLE, postChargeable);
        variables.put(VendorConstants.KEY_BUSINESS_MANAGER_APPLY, businessManager);
        variables.put(VendorConstants.KEY_BUSINESS_DEVELOPMENT_DIRECTOR_APPLY, businessDevelopmentDirectorApply);

        processHandler.startProcess(employee, request, VendorConstants.PROCESS_DEFINITION_KEY, variables, AccountType.EMPLOYEE);
        return new ServiceResponse();
    }

    @Override
    public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception {
        processHandler.completeTask(request, VendorConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
        return new ServiceResponse();
    }

    @Override
    public ServiceResponse businessSupportManagerApprove(ApproveRequest request) throws Exception {
        processHandler.completeTask(request, VendorConstants.KEY_BUSINESS_DEVELOPMENT_DIRECTOR_APPROVED);
        return new ServiceResponse();
    }

    @Override
    public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception {
        processHandler.completeTask(request, VendorConstants.KEY_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
        return new ServiceResponse();
    }

    @Override
    public ServiceResponse financeDirectorApprove(ApproveRequest request) throws Exception {
        processHandler.completeTask(request, VendorConstants.KEY_FINANCE_DIRECTOR_APPROVED);
        return new ServiceResponse();
    }

    @Override
    public ServiceResponse cooApprove(ApproveRequest request) throws Exception {
        processHandler.completeTask(request, VendorConstants.KEY_COO_APPROVED);
        return new ServiceResponse();
    }
}
