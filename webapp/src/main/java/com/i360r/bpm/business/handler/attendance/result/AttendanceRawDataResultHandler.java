package com.i360r.bpm.business.handler.attendance.result;

import com.i360r.bpm.business.handler.attendance.api.IAttendanceHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.attendance.AttendanceRawDataConstants;
import com.i360r.bpm.service.rs.process.attendance.api.AttendanceRawDataDetailVO;
import com.i360r.oas.api.internal.rs.attendance.CreateAttendanceRawDataRequest;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class AttendanceRawDataResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

    @Autowired
    private IAttendanceHandler attendanceHandler;

    @Autowired
    private IMessageNotifyHandler messageHandler;

    @Override
    public void onProcessPass(DelegateExecution execution) throws Exception {

        Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
        AttendanceRawDataDetailVO detail = ProcessUtility.getProcessObject(processVariables, AttendanceRawDataDetailVO.class);

        CreateAttendanceRawDataRequest request = new CreateAttendanceRawDataRequest();
        request.setDeliveryStaffCode(detail.getDeliveryStaffCode());
        request.setDate(detail.getAttendanceDate());
        request.setTime(detail.getAttendanceTime());
        request.setCreateByEmployeeCode(ProcessConstants.KEY_CREATED_BY_CODE);
        request.setCreateByEmployeeName(ProcessConstants.KEY_CREATED_BY_NAME);
        request.setDutyTypeCode(detail.getDutyTypeCode());

        attendanceHandler.createAttendanceRawData(request);

        messageHandler.notifyApplierPass(execution);
    }

    @Override
    public void onProcessNotPass(DelegateExecution execution) throws Exception {

        messageHandler.notifyApplierNotPass(execution);
    }

    @Override
    public String getProcessDefinitionKey() {
        return AttendanceRawDataConstants.PROCESS_DEFINITION_KEY;
    }

}
