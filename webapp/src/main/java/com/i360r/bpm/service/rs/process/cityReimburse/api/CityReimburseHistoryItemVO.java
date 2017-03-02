package com.i360r.bpm.service.rs.process.cityReimburse.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class CityReimburseHistoryItemVO {
	
	private String reimburseItemName;
	private BigDecimal amount;
	private String useTime;
	private String note;
	
	public static CityReimburseHistoryItemVO toVO(CityReimburseItemVO vo) throws Exception {
		CityReimburseHistoryItemVO itemVO = new CityReimburseHistoryItemVO();
		
		itemVO.setReimburseItemName(vo.getReimburseSubTypeName());
		itemVO.setAmount(vo.getAmount());
		itemVO.setNote(vo.getNote());
		String useTime = vo.getBeginTime();
		if (StringUtils.isNotBlank(vo.getEndTime())) {
			useTime = useTime + "到" +  vo.getEndTime();
		} 
		itemVO.setUseTime(useTime);		
		return itemVO;
	}
	
	public static List<CityReimburseHistoryItemVO> toVOs(List<CityReimburseItemVO> vos) throws Exception {
		List<CityReimburseHistoryItemVO> itemVOs = new ArrayList<CityReimburseHistoryItemVO>();
		
		if(vos == null) {
			return itemVOs;
		}
		
		for(CityReimburseItemVO vo : vos) {
			CityReimburseHistoryItemVO itemVo = toVO(vo);
			itemVOs.add(itemVo);
		}
		
		return itemVOs;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReimburseItemName() {
		return reimburseItemName;
	}

	public void setReimburseItemName(String reimburseItemName) {
		this.reimburseItemName = reimburseItemName;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	
}
