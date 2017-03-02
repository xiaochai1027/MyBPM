package com.i360r.bpm.business.handler.attendance.api;

import com.i360r.oas.api.internal.rs.attendance.CreateAttendanceRawDataRequest;

/**
 * Created by MengWeiBo on 2016-12-12
 */
public interface IAttendanceHandler {

    public void createAttendanceRawData(CreateAttendanceRawDataRequest request) throws Exception;

}
