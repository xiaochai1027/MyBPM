package com.i360r.bpm.business.handler.housing.checkout.result;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.housing.checkout.api.IHousingCheckoutHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.housingcheckout.HousingCheckoutCreationConstants;
import com.i360r.bpm.service.rs.process.housingcheckout.api.HousingCheckoutVO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CheckoutHousingRequest;

public class HousingCheckoutResultHandler extends AbstractEngineHandler  implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(HousingCheckoutResultHandler.class);
	
	
	@Autowired
	private IHousingCheckoutHandler housingCheckoutHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingCheckoutCreationConstants Pass!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		HousingCheckoutVO detail = ProcessUtility.getProcessObject(processVariables, HousingCheckoutVO.class);
		
		CheckoutHousingRequest request = new CheckoutHousingRequest();
		request.setContractId(String.valueOf(detail.getContractId()));
		request.setCheckoutNote(detail.getCheckoutNote());
		request.setCheckoutDate(detail.getCheckoutDate());
		request.setTurnoveredByCode((String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE));
		request.setTurnoveredByName((String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME));
		request.setTurnoverDate((Date)processVariables.get(ProcessConstants.KEY_CREATE_TIME));
		request.setActualTurnoverDate(detail.getActualTurnoverDate());
		request.setReturnedRentFee(detail.getRentBackFee());
				
		housingCheckoutHandler.checkoutHousing(request);	
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingCheckoutCreationConstants deny!");
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		
		return HousingCheckoutCreationConstants.PROCESS_DEFINITION_KEY;
	}

}
