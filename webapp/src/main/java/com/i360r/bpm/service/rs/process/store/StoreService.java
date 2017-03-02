package com.i360r.bpm.service.rs.process.store;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.handler.store.account.api.IStoreAccountHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.store.api.CreateStoreRequest;
import com.i360r.bpm.service.rs.process.store.api.IStoreService;
import com.i360r.bpm.service.rs.process.store.api.StoreVO;
import com.i360r.dop.api.internal.rs.vendor.VendorDetailResponse;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;

/**
 * Created by MengWeiBo on 2016-12-23
 */
public class StoreService implements IStoreService {

    @Autowired
    private IProcessHandler processHandler;

    @Autowired
    private IEmployeeHandler employeeHandler;

    @Autowired
    private IStoreAccountHandler storeAccountHandler;

    @Override
    public ProcessDetailResponse<StoreVO> getDetail(String processInstanceId) throws Exception {
        ProcessDetailResponse<StoreVO> detail = processHandler.getProcessDetail(processInstanceId, StoreVO.class);
        StoreVO vo = detail.getDetail();

        setStoreVO(vo, storeAccountHandler.getVendorDetail(vo.getVendorId()));

        return detail;
    }

    @Override
    public ServiceResponse startProcess(CreateStoreRequest request) throws Exception {

        storeAccountHandler.validateStore(request.getAddress(), request.getBusinessAreaId(), request.getName());

        EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
        Map<String, Object> variables = new HashMap<String, Object>();

        String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());

        variables.put(StoreConstants.KEY_CREATED_BY_MOBILE, mobile);
        variables.put(ProcessConstants.KEY_LOCATION_CODE, request.getBusinessAreaId());

        processHandler.startProcess(employee, request, StoreConstants.PROCESS_DEFINITION_KEY, variables, AccountType.EMPLOYEE);

        return new ServiceResponse();
    }

    @Override
    public ServiceResponse businessSupportManagerApprove(ApproveRequest request) throws Exception {
        processHandler.completeTask(request, StoreConstants.KEY_BUSINESS_SUPPORT_MANAGER_APPROVED);

        return new ServiceResponse();
    }

    private void setStoreVO(StoreVO vo, VendorDetailResponse response) {
        vo.setAccountId(response.getAccountId());
        vo.setAccountName(response.getAccountName());
        vo.setAccountMobile(response.getAccountMobile());
        vo.setAccountActivated(response.getAccountActivated());
        vo.setAlertAmount(response.getWalletAlertAmount());
        vo.setInvoiceTitle(response.getInvoiceTitle());

        vo.setVendorId(response.getVendorId());
        vo.setVendorName(response.getVendorName());
        vo.setContactName(response.getContactName());
        vo.setContactMobile(response.getContactMobile());
        vo.setBusinessTypeCode(response.getBusinessTypeCode());
        vo.setBusinessTypeName(response.getBusinessTypeName());
        vo.setDeliverTypeName(response.getDeliverTypeName());
        vo.setDeliverTypeCode(response.getDeliverTypeCode());
        vo.setVendorDescription(response.getVendorDescription());
        vo.setHasPartner(response.getHasPartner());
        vo.setVendorActivated(response.getVendorActivated());
        vo.setParttimeManual(response.getParttimeManual());
        vo.setSeparatePaidSalary(response.getSeparatePaidSalary());
        vo.setUseFixedRoyaltiesConfig(response.getUseFixedRoyaltiesConfig());
        vo.setParttimeFixedRoyaltiesAmount(response.getParttimeFixedRoyaltiesAmount());
        vo.setFulltimeFixedRoyaltiesAmount(response.getFulltimeFixedRoyaltiesAmount());
        vo.setRemarks(response.getRemarks());

        // 距离配置
        if (response.getDeliverFeeDistanceBase() != null && response.getDeliverFeeDistanceBase().compareTo(BigDecimal.ZERO) > 0) {
            vo.setHasDeliverFeeDistanceConfig(true);
            vo.setDeliverFeeDistanceBase(response.getDeliverFeeDistanceBase());
            vo.setDeliverFeeDistanceUnit(response.getDeliverFeeDistanceUnit());
            vo.setDeliverFeeDistanceAmmountPerUnit(response.getDeliverFeeDistanceAmmountPerUnit());
            vo.setDeliverFeeDistanceMaxAmmount(response.getDeliverFeeDistanceMaxAmmount());

        } else { // 前端需要null去做逻辑，从而显示空白
            vo.setDeliverFeeDistanceBase(null);
            vo.setDeliverFeeDistanceUnit(null);
            vo.setDeliverFeeDistanceAmmountPerUnit(null);
            vo.setDeliverFeeDistanceMaxAmmount(null);
        }

        // 重量配置
        if (response.getDeliverFeeWeightBase() != null && response.getDeliverFeeWeightBase().compareTo(BigDecimal.ZERO) > 0) {
            vo.setHasDeliverFeeWeightConfig(true);
            vo.setDeliverFeeWeightBase(response.getDeliverFeeWeightBase());
            vo.setDeliverFeeWeightUnit(response.getDeliverFeeWeightUnit());
            vo.setDeliverFeeWeightAmmountPerUnit(response.getDeliverFeeWeightAmmountPerUnit());
            vo.setDeliverFeeWeightMaxAmmount(response.getDeliverFeeWeightMaxAmmount());

        } else {
            vo.setDeliverFeeWeightBase(null);
            vo.setDeliverFeeWeightUnit(null);
            vo.setDeliverFeeWeightAmmountPerUnit(null);
            vo.setDeliverFeeWeightMaxAmmount(null);
        }

        // 订单金额配置
        if (response.getDeliverFeeOrderPriceDiscountRate() != null && response.getDeliverFeeOrderPriceDiscountRate().compareTo(BigDecimal.ZERO) > 0) {
            vo.setHasDeliverFeeOrderPriceConfig(true);
            vo.setDeliverFeeOrderPriceBase(response.getDeliverFeeOrderPriceBase());
            vo.setDeliverFeeOrderPriceDiscountRate(response.getDeliverFeeOrderPriceDiscountRate());
            vo.setDeliverFeeOrderPriceMaxAmmount(response.getDeliverFeeOrderPriceMaxAmmount());

        } else {
            vo.setDeliverFeeOrderPriceBase(null);
            vo.setDeliverFeeOrderPriceDiscountRate(null);
            vo.setDeliverFeeOrderPriceMaxAmmount(null);
        }

        // 时段配置
        vo.setDeliverFeeTimeslot1From(response.getDeliverFeeTimeslot1From());
        vo.setDeliverFeeTimeslot1To(response.getDeliverFeeTimeslot1To());

        vo.setDeliverFeeTimeslot2From(response.getDeliverFeeTimeslot2From());
        vo.setDeliverFeeTimeslot2To(response.getDeliverFeeTimeslot2To());

        vo.setDeliverFeeTimeslot3From(response.getDeliverFeeTimeslot3From());
        vo.setDeliverFeeTimeslot3To(response.getDeliverFeeTimeslot3To());
        if (response.getDeliverFeeTimeslot1Ammount() != null && response.getDeliverFeeTimeslot1Ammount().compareTo(BigDecimal.ZERO) > 0) {
            vo.setHasDeliverFeeTimeslotConfig(true);
            vo.setDeliverFeeTimeslot1Ammount(response.getDeliverFeeTimeslot1Ammount());

            if (response.getDeliverFeeTimeslot2Ammount() != null && response.getDeliverFeeTimeslot2Ammount().compareTo(BigDecimal.ZERO) > 0) {
                vo.setDeliverFeeTimeslot2Ammount(response.getDeliverFeeTimeslot2Ammount());
            } else {
                vo.setDeliverFeeTimeslot2Ammount(null);
            }
            if (response.getDeliverFeeTimeslot3Ammount() != null && response.getDeliverFeeTimeslot3Ammount().compareTo(BigDecimal.ZERO) > 0) {
                vo.setDeliverFeeTimeslot3Ammount(response.getDeliverFeeTimeslot3Ammount());
            } else {
                vo.setDeliverFeeTimeslot3Ammount(null);
            }

        } else {
            vo.setDeliverFeeTimeslot1Ammount(null);
            vo.setDeliverFeeTimeslot2Ammount(null);
            vo.setDeliverFeeTimeslot3Ammount(null);
        }

        vo.setDeliverFeeBase(response.getDeliverFeeBase());
        vo.setSettlementTypeCode(response.getSettlementTypeCode());
        vo.setSettlementTypeName(response.getSettlementTypeName());
        vo.setDeliverFeeRoundingTypeCode(response.getDeliverFeeRoundingTypeCode());
        vo.setDeliverFeeRoundingTypeName(response.getDeliverFeeRoundingTypeName());

        vo.setCallBackUrl(response.getCallBackUrl());
        vo.setVersion(response.getVersion());
        vo.setSyncLocation(response.getSyncLocation());
        vo.setLocationProcessRatio(response.getLocationProcessRatio());

        vo.setPerformanceTimeImmediatelyMins(response.getPerformanceTimeImmediatelyMins());
        vo.setPerformanceTimeIntimeMinMins(response.getPerformanceTimeIntimeMinMins());
        vo.setPerformanceTimeIntimeMaxMins(response.getPerformanceTimeIntimeMaxMins());
        vo.setPerformanceDistanceCompleteMeter(response.getPerformanceDistanceCompleteMeter());

        //距离补贴
        if (response.getAllowanceDistanceBase() != null && response.getAllowanceDistanceBase().compareTo(BigDecimal.ZERO) > 0) {
            vo.setHasAllowanceDistanceConfig(true);
            vo.setAllowanceDistanceBase(response.getAllowanceDistanceBase());
            vo.setAllowanceDistanceUnit(response.getAllowanceDistanceUnit());
            vo.setAllowanceDistanceAmmountPerUnit(response.getAllowanceDistanceAmmountPerUnit());
            vo.setAllowanceDistanceMaxAmmount(response.getAllowanceDistanceMaxAmmount());

        } else {
            vo.setAllowanceDistanceBase(null);
            vo.setAllowanceDistanceUnit(null);
            vo.setAllowanceDistanceAmmountPerUnit(null);
            vo.setAllowanceDistanceMaxAmmount(null);
        }

        // 重量补贴
        if (response.getAllowanceWeightBase() != null && response.getAllowanceWeightBase().compareTo(BigDecimal.ZERO) > 0) {
            vo.setHasAllowanceWeightConfig(true);
            vo.setAllowanceWeightBase(response.getAllowanceWeightBase());
            vo.setAllowanceWeightUnit(response.getAllowanceWeightUnit());
            vo.setAllowanceWeightAmmountPerUnit(response.getAllowanceWeightAmmountPerUnit());
            vo.setAllowanceWeightMaxAmmount(response.getAllowanceWeightMaxAmmount());

        } else {
            vo.setAllowanceWeightBase(null);
            vo.setAllowanceWeightUnit(null);
            vo.setAllowanceWeightAmmountPerUnit(null);
            vo.setAllowanceWeightMaxAmmount(null);
        }
    }
}
