package com.i360r.bpm.service.rs.task.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

@RequireAuthentication
public interface ITaskManagementService {

	//待办任务
	@POST
	@Path("/todo")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetToDoListResponse getToDoList(@FormParam("") GetToDoListRequest request);
	
	//待指派任务
	@POST
	@Path("/assign")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetAssignListResponse getAssignList(@FormParam("") GetAssignListRequest request) throws Exception;
	
	//候选任务
	@POST
	@Path("/candidate")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetCandidateListResponse getCandidateList(@FormParam("") GetCandidateListRequest request);
	
	//我的任务
	@POST
	@Path("/my/current")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SearchMyCurrentTaskListResponse searchMyCurrentTaskList(@FormParam("") GetMyCurrentTaskRequest request) throws Exception;
	
	//微信
	@POST
	@Path("/todo/keyword")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetToDoListResponse searchToDoTasksByKeyword(@FormParam("") KeywordSearchTaskListRequest request);
	
	//微信
	@POST
	@Path("/candidate/keyword")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetCandidateListResponse searchCandidateTasksByKeyword(@FormParam("") KeywordSearchTaskListRequest request);
	
	//微信
	@POST
	@Path("/my/current/keyword")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SearchMyCurrentTaskListResponse searchMyCurrentTasksByKeyword(@FormParam("") KeywordSearchTaskListRequest request) throws Exception;
	
	//历史任务
	@POST
	@Path("/my/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SearchMyHistoryTaskListResponse searchMyHistoryTaskList(@FormParam("") GetMyHistoryTaskRequest request);
	
	//piOffice任务列表
	@POST
	@Path("/mobile/task/list")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetListResponse getTaskList(@FormParam("") ConditionSearchTaskListRequest request);
	
	//查询任务-审核中的任务
	@POST
	@Path("/search/current")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SearchCurrentTaskListResponse searchCurrentTaskList(@FormParam("") SearchCurrentTaskRequest request) throws Exception;
	
	//查询任务-归档任务
	@POST
	@Path("/search/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SearchHistoryTaskListResponse searchHistoryTaskList(@FormParam("") SearchHistoryTaskRequest request);
	
	@GET
	@Path("/claim")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse claim(@QueryParam("taskId") String taskId);
	
	@POST
	@Path("/batchclaim")
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse batchClaim(BatchClaimRequest request);
	
	@POST
	@Path("/delete")
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse deleteProcess(DeleteProcessRequest request) throws Exception;
	
	@POST
	@Path("/modify/assignee")
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyTaskAssignee(ModifyTaskAssigneeRequest request) throws Exception;
	
	@POST
	@Path("/modify/responsible")
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyTaskResponsible(ModifyTaskResponsibilityRequest request) throws Exception;
	
	@POST
	@Path("/modify/creator")
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyTaskCreator(ModifyTaskCreatorRequest request) throws Exception;
	
}
