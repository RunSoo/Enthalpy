package com.enthalpy.model.service;

import java.util.List;

import com.enthalpy.model.dto.DailyCal;

public interface DailyCalService {
	public List<DailyCal> getAllDailyCal(String userId);
	
	public boolean existingDailyCal(DailyCal dailyCal);
	
	public boolean writeDailyCal(DailyCal dailycal);
	
	public boolean modifyDailyCal(DailyCal addCal);
	
	public boolean subtractDailyCal(DailyCal subCal);
	
	public boolean delDailyCal(DailyCal dailycal);
	
	public int getMonthlyCalSum(DailyCal dailyCal);
}
