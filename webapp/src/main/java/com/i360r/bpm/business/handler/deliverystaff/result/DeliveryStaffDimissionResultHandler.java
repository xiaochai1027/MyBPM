package com.i360r.bpm.business.handler.deliverystaff.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.DeliveryStaffDimissionConstants;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.api.DeliveryStaffDimissionDetailVO;
import com.i360r.cds.api.internal.rs.deliverystaff.DimissDeliveryStaffRequest;

public class DeliveryStaffDimissionResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		DeliveryStaffDimissionDetailVO detail = ProcessUtility.getProcessObject(
				processVariables, DeliveryStaffDimissionDetailVO.class);
		
		DimissDeliveryStaffRequest request = new DimissDeliveryStaffRequest();
		request.setDeliveryStaffId(detail.getDeliveryStaffCode());
		request.setDimissionReasonCode(detail.getDimissionReasonCode());
		request.setDimissImmediately(false);
		request.setDimissionDate(detail.getDimissionDate());
		request.setDetail(detail.getDetail());
		request.setPictureUrl1(detail.getPictureUrl1());
		request.setOrigPictureUrl1(detail.getOrigPictureUrl1());
		request.setPictureUrl2(detail.getPictureUrl2());
		request.setOrigPictureUrl2(detail.getOrigPictureUrl2());
		
		deliveryStaffHandler.deliveryStaffDimission(request);
		
		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return DeliveryStaffDimissionConstants.PROCESS_DEFINITION_KEY;
	}

}
