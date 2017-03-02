package com.i360r.bpm.business.handler.deliverystaff.result;

import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.bpm.service.rs.process.deliverystaffentry.api.DeliveryStaffEntryDetailVO;
import com.i360r.cds.api.internal.rs.deliverystaff.*;
import com.i360r.cds.api.internal.rs.employee.IEmployeeInternalService;
import com.i360r.cds.api.internal.rs.employee.position.PositionCodeSO;
import com.i360r.framework.util.DateTimeUtility;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

public class DeliveryStaffEntryResultHandler extends AbstractEngineHandler implements IProcessResultHandler {
	
	@Resource
	private IEmployeeInternalService employeeInternalServiceClient;
	
	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		DeliveryStaffEntryDetailVO detail = ProcessUtility.getProcessObject(processVariables, DeliveryStaffEntryDetailVO.class);
		
		// 兼容老流程（老流程全职配送员没有等级字段）
		String gradeTypeCode = detail.getDeliveryStaffGradeTypeCode();
		if (StringUtils.isBlank(gradeTypeCode) && PositionCodeSO.DIRECT_MANAGEMENT_FULL_TIME_DELIVERY_STAFF.getCode().equals(detail.getPositionCode())) {
			gradeTypeCode = DeliveryStaffGradeTypeSO.A.getCode();
		}
		
		if (!detail.getJobTransfer()) {
			CreateOfficialAccountRequest request = new CreateOfficialAccountRequest();
			request.setDeliveryStaffId(detail.getDeliveryStaffCode());
			request.setFullName(detail.getFullName());
			request.setGenderCode(detail.getGenderCode());
			request.setBankCardNumber(detail.getBankCard());
			request.setBankCardPictureUrl(detail.getBankCardPictureUrl());
			request.setOrigBankCardPictureUrl(detail.getOrigBankCardPictureUrl());
			request.setContractBeginDate(detail.getContractBeginDate());
			request.setContractEndDate(detail.getContractEndDate());
			request.setContractTypeCode(detail.getContractTypeCode());
			request.setHealthCertificatePictureUrl1(detail.getHealthCertificatePictureUrl1());
			request.setOrigHealthCertificatePictureUrl1(detail.getOrigHealthCertificatePictureUrl1());
			request.setHealthCertificatePictureUrl2(detail.getHealthCertificatePictureUrl2());
			request.setOrigHealthCertificatePictureUrl2(detail.getOrigHealthCertificatePictureUrl2());
			request.setEducationBackgroundCode(detail.getEducationBackgroundCode());
			request.setRecruitmentChannelCode(detail.getRecruitmentChannelCode());
			request.setHealthCertificateBeginDate(detail.getHealthCertificateBeginDate());
			request.setHealthCertificateEndDate(detail.getHealthCertificateEndDate());
			
			request.setAvatarPictureUrl(detail.getAvatarPictureUrl());
			request.setOrigAvatarPictureUrl(detail.getOrigAvatarPictureUrl());
			request.setIdentityCard(detail.getIdentityCard());
			request.setIdentityCardPictureUrl1(detail.getIdentityCardPictureUrl1());
			request.setOrigIdentityCardPictureUrl1(detail.getOrigIdentityCardPictureUrl1());
			request.setIdentityCardPictureUrl2(detail.getIdentityCardPictureUrl2());
			request.setOrigIdentityCardPictureUrl2(detail.getOrigIdentityCardPictureUrl2());
			request.setBasicSalary(detail.getBasicSalary());
			request.setEntryDate(detail.getEntryDate());
			request.setCompanyVehiclesUsed(detail.getCompanyVehiclesUsed());
			request.setDeliveryStaffGradeTypeCode(gradeTypeCode);
			
			deliveryStaffHandler.createOfficialAccount(request);
		} else {
			DeliveryStaffTransformationRequest request = new DeliveryStaffTransformationRequest();
			request.setDeliveryStaffId(detail.getOriginDeliveryStaffCode());
			request.setBusinessAreaId(detail.getBusinessAreaCode());
			request.setPositionCode(detail.getPositionCode());
			request.setTrasformationDate(detail.getJobTransferDate());
			request.setCompanyVehiclesUsed(detail.getCompanyVehiclesUsed());
			request.setBasicSalary(detail.getBasicSalary());
			request.setDingdingMobile(detail.getDingdingMobile());
			request.setHealthCertificatePictureUrl1(detail.getHealthCertificatePictureUrl1());
			request.setOrigHealthCertificatePictureUrl1(detail.getOrigHealthCertificatePictureUrl1());
			request.setHealthCertificatePictureUrl2(detail.getHealthCertificatePictureUrl2());
			request.setOrigHealthCertificatePictureUrl2(detail.getOrigHealthCertificatePictureUrl2());
			request.setHealthCertificateBeginDate(detail.getHealthCertificateBeginDate());
			request.setHealthCertificateEndDate(detail.getHealthCertificateEndDate());
			request.setDeliveryStaffGradeTypeCode(gradeTypeCode);
			
			deliveryStaffHandler.transferPosition(request);
		}
		
		messageHandler.notifyApplierPass(execution);
		
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {

		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		Boolean jobTransfer = (Boolean) processVariables.get(DeliveryStaffEntryConstants.KEY_JOB_TRANSFER);
		
		//如果是历史流程，不含jobTranser变量，设定初始值为false，即不是转岗，按照入职流程对待
		if (jobTransfer == null) {
			jobTransfer = false;
		}
		
		//只有入职回调 创建正式账号函数
		if (!jobTransfer) {
			DimissDeliveryStaffRequest request = new DimissDeliveryStaffRequest();
			
			String deliveryStaffCode = (String)processVariables.get(DeliveryStaffEntryConstants.KEY_DELIVERY_STAFF_CODE);
			
			request.setDeliveryStaffId(deliveryStaffCode);
			request.setDimissImmediately(false);
			String entryDateStr = (String)processVariables.get("entryDate");
			Date entryDate = DateTimeUtility.parseYYYYMMDD(entryDateStr);
			Date now = new Date();
			if (now.before(entryDate)) {
				request.setDimissionDate(DateTimeUtility.formatYYYYMMDD(entryDate));
			} else {
				request.setDimissionDate(DateTimeUtility.formatYYYYMMDD(now));
			}
			
			request.setDimissionWayCode(DimissionWaySO.TRIAL_FAILURE.name());
			request.setDimissionReasonCode(DimissionReasonSO.NOT_COMPETENT.name());
			
			deliveryStaffHandler.deliveryStaffDimission(request);
			messageHandler.notifyApplierNotPass(execution);
		} else {
			messageHandler.notifyApplierNotPass(execution);
		}

	}

	@Override
	public String getProcessDefinitionKey() {
		return DeliveryStaffEntryConstants.PROCESS_DEFINITION_KEY;
	}

}
