package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class SpendAfterReimburseHistoryItemVO {

	private String reimburseItemName;
	private String amount;
	private String useTime;
	private String note;
	
	public static SpendAfterReimburseHistoryItemVO toVO(SpendAfterReimburseUploadVoucherVO vo) throws Exception{
		SpendAfterReimburseHistoryItemVO itemVO = new SpendAfterReimburseHistoryItemVO();
		
		itemVO.setAmount(String.valueOf(vo.getAmount()));
		itemVO.setNote(vo.getNote());
		itemVO.setReimburseItemName(vo.getReimburseSubTypeName());
		String userTime = vo.getBeginTime();
		String endTime = vo.getEndTime();
		if(StringUtils.isNotBlank(endTime)) {
			userTime = userTime + "到" + endTime;
		}
		itemVO.setUseTime(userTime);
		return itemVO;
	}
	
	public static List<SpendAfterReimburseHistoryItemVO> toVOs(List<SpendAfterReimburseUploadVoucherVO> vos) throws Exception{
		List<SpendAfterReimburseHistoryItemVO> itemVOs = new ArrayList<SpendAfterReimburseHistoryItemVO>();
		
		if(vos == null ) {
			return itemVOs;
		}
		for(SpendAfterReimburseUploadVoucherVO vo : vos) {
			SpendAfterReimburseHistoryItemVO itemVO = toVO(vo);
			itemVOs.add(itemVO);
		}
		
		return itemVOs;
	}
	
	public String getReimburseItemName() {
		return reimburseItemName;
	}
	public void setReimburseItemName(String reimburseItemName) {
		this.reimburseItemName = reimburseItemName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
