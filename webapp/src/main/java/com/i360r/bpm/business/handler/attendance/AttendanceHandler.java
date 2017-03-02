package com.i360r.bpm.business.handler.attendance;

import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.attendance.api.IAttendanceHandler;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.oas.api.internal.rs.attendance.CreateAttendanceRawDataRequest;
import com.i360r.oas.api.internal.rs.attendance.IAttendanceInternalService;

import javax.annotation.Resource;

/**
 * Created by MengWeiBo on 2016-12-12
 */
public class AttendanceHandler extends AbstractBusinessHandler implements IAttendanceHandler {

    @Resource
    private IAttendanceInternalService attendanceInternalServiceClient;

    @Override
    public void createAttendanceRawData(CreateAttendanceRawDataRequest request) throws Exception {
        ServiceResponse response = attendanceInternalServiceClient.createAttendanceRawData(request);
        checkResponseSuccess(response);
    }
}

