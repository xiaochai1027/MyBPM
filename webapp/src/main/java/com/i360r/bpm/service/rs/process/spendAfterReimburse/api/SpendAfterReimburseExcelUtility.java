package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.api.TaskResultVO;
import com.i360r.framework.util.ExcelUtility;

public class SpendAfterReimburseExcelUtility {
	public static final String LABEL_REIMBURSE_DETAIL_INFO = "报销(先付款)任务详情列表";
	
	public static final String BUSINESSAREA_NAME = "商圈:";
	public static final String FUND_TYPE = "工单类型:";
	public static final String APPLY_AMOUNT = "申请金额:";
	public static final String APPLY_NOTE= "申请说明:";
	
	public static final String PROCESS_TASKS = "处理流程:";
	public static final String TASK_ASSIGNEE_NAME = "处理人";
	public static final String TASK_TIME = "处理时间";
	public static final String TASK_NAME = "审批节点";
	public static final String TASK_RESULT = "审批结果";
	public static final String TASK_SUGGESTION = "处理意见";
	
	public static final String REIMBURSE_ITEMS = "报销明细:";
	public static final String REIMBURSE_NAME = "报销名称";
	public static final String REIMBURSE_AMOUNT = "报销金额";
	public static final String REIMBURSE_USE_TIME = "费用产生时间";
	public static final String REIMBURSE_NOTE = "备注";
	
	public static void createSpendAfterReimburseExcel(HSSFWorkbook workbook, ProcessDefinition pd, 
			ProcessDetailResponse<SpendAfterReimburseDetailVO> detailResponse) throws Exception {
		
		HSSFSheet sheet = ExcelUtility.createSheet(workbook, LABEL_REIMBURSE_DETAIL_INFO);
		CellStyle cellStyle = ExcelUtility.getCellStyle(workbook, true);
	        
		int rowIndex = 0;
		int columnIndex = 0;
		
		ExcelUtility.createCell(sheet, rowIndex++, columnIndex, BUSINESSAREA_NAME, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex++, columnIndex, FUND_TYPE, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex++, columnIndex, APPLY_AMOUNT, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex++, columnIndex, APPLY_NOTE, cellStyle);
		
		SpendAfterReimburseDetailVO detail = detailResponse.getDetail();
		if (detail != null) {
			rowIndex = 0;
			columnIndex ++;
			ExcelUtility.createCell(sheet, rowIndex++, columnIndex, detail.getBusinessAreaName(), cellStyle);
			ExcelUtility.createCell(sheet, rowIndex++, columnIndex, pd.getName(), cellStyle);
			ExcelUtility.createCell(sheet, rowIndex++, columnIndex, detail.getTotalAmount(), cellStyle);
			ExcelUtility.createCell(sheet, rowIndex++, columnIndex, detail.getNote(), cellStyle);
		}
		
		columnIndex = 0;
		rowIndex ++;	
		ExcelUtility.createCell(sheet, rowIndex++, columnIndex, PROCESS_TASKS, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, TASK_ASSIGNEE_NAME, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, TASK_TIME, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, TASK_NAME, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, TASK_RESULT, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, TASK_SUGGESTION, cellStyle);
		
		List<TaskResultVO> taskResults = detailResponse.getTaskList();
		if (taskResults != null && taskResults.size() > 0) {
			for (TaskResultVO taskResult : taskResults) {
				rowIndex ++;
				columnIndex = 0;
				ExcelUtility.createCell(sheet, rowIndex, columnIndex++, taskResult.getAssigneeName(), cellStyle);
				ExcelUtility.createCell(sheet, rowIndex, columnIndex++, taskResult.getEndTime(), cellStyle);
				ExcelUtility.createCell(sheet, rowIndex, columnIndex++, taskResult.getTaskName(), cellStyle);
				ExcelUtility.createCell(sheet, rowIndex, columnIndex++, taskResult.getResult(), cellStyle);
				ExcelUtility.createCell(sheet, rowIndex, columnIndex++, taskResult.getSuggestion(), cellStyle);
			}
		}
		rowIndex ++;	
		
		columnIndex = 0;
		rowIndex ++;	//空一行
		ExcelUtility.createCell(sheet, rowIndex++, columnIndex, REIMBURSE_ITEMS, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, REIMBURSE_NAME, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, REIMBURSE_AMOUNT, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, REIMBURSE_USE_TIME, cellStyle);
		ExcelUtility.createCell(sheet, rowIndex, columnIndex++, REIMBURSE_NOTE, cellStyle);
		
		if (detail != null) {
			List<SpendAfterReimburseUploadVoucherVO> reimburseItems = detail.getUploadVoucherVOs();
			if (reimburseItems != null && reimburseItems.size() > 0) {
				for (SpendAfterReimburseUploadVoucherVO reimburseItem : reimburseItems) {
					rowIndex ++;
					columnIndex = 0;
					ExcelUtility.createCell(sheet, rowIndex, columnIndex++, reimburseItem.getReimburseSubTypeName(), cellStyle);
					ExcelUtility.createCell(sheet, rowIndex, columnIndex++, reimburseItem.getAmount(), cellStyle);
					String useTime = reimburseItem.getBeginTime();
					if (StringUtils.isNotBlank(reimburseItem.getEndTime())) {
						useTime = "到" + reimburseItem.getEndTime();
					}
					ExcelUtility.createCell(sheet, rowIndex, columnIndex++, useTime, cellStyle);
					ExcelUtility.createCell(sheet, rowIndex, columnIndex++, reimburseItem.getNote(), cellStyle);
				}
			} 
		}
		
	}
	
}
