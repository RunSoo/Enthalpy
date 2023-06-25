package com.enthalpy.model.dao;

import java.util.List;

import com.enthalpy.model.dto.DailyCal;

public interface DailyCalDao {
	List<DailyCal> selectAll(String userId);
	
	int selectDailyCal(DailyCal dailyCal);
	
	int insertDailyCal(DailyCal dailycal);
	
	int updateDailyCal(DailyCal addCal);
	
	int subtractDailyCal(DailyCal subCal);
	
	int delDailyCal(DailyCal dailycal);
	
	int selectMonthlyDailyCal(DailyCal dailycal);
}
