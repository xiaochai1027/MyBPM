package com.i360r.bpm.business.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.i360r.cds.api.internal.rs.employee.BpmMessageReceiveTypeSO;
import com.i360r.cds.api.internal.rs.employee.MessageReceiveTypeResponse;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.StatusCodeManager;
import com.i360r.module.message.AsyncMessager;
import com.i360r.module.message.api.TemplateMessageMO;
import com.i360r.module.message.app.push.AndroidOpenTypeMO;
import com.i360r.module.message.app.push.AppMO;
import com.i360r.module.message.app.push.AppPushOptionMO;
import com.i360r.module.message.app.push.AppPushTypeMO;
import com.i360r.module.message.inner.message.InnerMessageTypeMO;
import com.i360r.module.message.sms.SmsTypeMO;

public class ProcessNotifyMessageEventSendUtility {
	private static Log LOG = Log.getLog(ProcessNotifyMessageEventSendUtility.class);
	
	public static void sendTemplateNotifyMessage(String templateCode, List<String> params, Receiver... receivers) {
        
		for (Receiver receiver : receivers) {
			try {
				MessageReceiveTypeResponse response = ServiceClientUtility.getEmployeeInternalService().getMesssgeReceiveTypes(receiver.getEmployeeId());
				if (response == null || !response.getResultCode().equals(StatusCodeManager.getSucceedCode())) {
					LOG.error("ProcessNotifyMessageEventSendUtility.sendTemplateNotifyMessage call cds response error. "
							+ "templateCode:{}, params:{}, receiver:{}, response:{}", templateCode, params, receiver, response);
					break;
				}
			    TemplateMessageMO message = new TemplateMessageMO();
				message.setParams(params);
				message.setTemplateCode(templateCode);
				String title = MessageSourceManager.getMessage("label.task.notify.title");
				message.setTitle(title);
				
				Set<BpmMessageReceiveTypeSO> types = response.getBpmMessageReceiveTypes();
				for (BpmMessageReceiveTypeSO messageReceiveTypeSO : types) {
					switch (messageReceiveTypeSO) {
					case APP_PUSH: 
						AppPushOptionMO pushOption = new AppPushOptionMO();
						pushOption.setAndroidOpenType(AndroidOpenTypeMO.OPEN_APP);
						AsyncMessager.sendAppPush(message, AppPushTypeMO.NOTICE, AppMO.PAI_OFFICE, pushOption, Arrays.asList(receiver.getEmployeeId()));
						break;
					case INNER_MESSAGE:
						AsyncMessager.sendInnerMessage(message, InnerMessageTypeMO.SYSTEM, null, MessageSourceManager.getMessage("label.inner.message.task.sender.name"), Arrays.asList(receiver.getEmployeeId()));
						break;
					case MOBILE_MESSAGE:
						AsyncMessager.sendSms(message, SmsTypeMO.OTHER, Arrays.asList(receiver.getMobile()));
						break;
					default:
						break;
					}
				}
				
			} catch (Exception e) {
				LOG.error("ProcessNotifyMessageEventSendUtility.sendTemplateNotifyMessage error.", e);
			}
		}
	}
	public static class Receiver implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String mobile;//this should not be empty,when send sms.
		private String employeeId;// include employeeId,storeAcountId.this should not be empty, when send app-push or inner-message.

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}


		public String getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}

		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this);
		}
	}
}
