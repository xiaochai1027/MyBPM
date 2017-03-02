package com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.ResponsiblePersonResponse;
import com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.FixedAssetCityDistributeUncheckedConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IFixedAssetCityDistributeUncheckedService {
	
	@POST
	@Path("/" + FixedAssetCityDistributeUncheckedConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(FixedAssetCityDistributeUncheckedRequest request) throws Exception;
	
	@POST
	@Path("/" + FixedAssetCityDistributeUncheckedConstants.TASK_DELIVERY_STATION_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse stationManagerApprove(FixedAssetCityDistributeUncheckedNumbers request);
		
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<FixedAssetCityDistributeUncheckedDetailVO> getDetail(
			@QueryParam("processInstanceId") String processInstanceId);

	@GET
	@Path("/getResponsiblePerson")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ResponsiblePersonResponse getResponsiblePerson(
			@QueryParam("locationCode") String locationCode) throws Exception;
}
