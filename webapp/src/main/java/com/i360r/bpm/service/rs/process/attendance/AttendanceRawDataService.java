package com.i360r.bpm.service.rs.process.attendance;

import com.i360r.bpm.business.exception.AttendanceRawDateErrorException;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.attendance.api.AttendanceRawDataDetailVO;
import com.i360r.bpm.service.rs.process.attendance.api.AttendanceRawDataRequest;
import com.i360r.bpm.service.rs.process.attendance.api.IAttendanceRawDataService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by MengWeiBo on 2016-12-07
 */
public class AttendanceRawDataService implements IAttendanceRawDataService {

    @Autowired
    private IProcessHandler processHandler;

    @Override
    public ServiceResponse startProcess(AttendanceRawDataRequest request) throws Exception {

        Date attendanceDateTime = DateTimeUtility.parseYYYYMMDDHHMM(request.getAttendanceDate() + " " + request.getAttendanceTime());
        Date now = new Date();
        if (attendanceDateTime.after(now)){
            throw new AttendanceRawDateErrorException("AttendanceRawData request date time :" + attendanceDateTime);
        }

        EmployeeCO employee = SessionContextAccessor.getCurrentAccount();

        processHandler.startProcess(employee, request, AttendanceRawDataConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
        return new ServiceResponse();
    }

    @Override
    public ProcessDetailResponse<AttendanceRawDataDetailVO> getDetail(String processInstanceId) {
        return processHandler.getProcessDetail(processInstanceId, AttendanceRawDataDetailVO.class);

    }

    @Override
    public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request) {
        processHandler.completeTask(request, AttendanceRawDataConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);

        return new ServiceResponse();
    }

    @Override
    public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) {
        processHandler.completeTask(request, AttendanceRawDataConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);

        return new ServiceResponse();
    }
}
