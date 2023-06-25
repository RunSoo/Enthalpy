package com.enthalpy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enthalpy.model.dao.DailyCalDao;
import com.enthalpy.model.dto.DailyCal;

@Service
public class DailyCalServiceImpl implements DailyCalService {
	
	@Autowired
	private DailyCalDao dailyCalDao;
	
	@Override
	public List<DailyCal> getAllDailyCal(String userId) {
		return dailyCalDao.selectAll(userId);
	}

	@Override
	public boolean existingDailyCal(DailyCal dailyCal) {
		return dailyCalDao.selectDailyCal(dailyCal) != 0;
	}
	
	@Transactional
	@Override
	public boolean writeDailyCal(DailyCal dailycal) {
		return dailyCalDao.insertDailyCal(dailycal) == 1;
	}

	@Transactional
	@Override
	public boolean modifyDailyCal(DailyCal addCal) {
		return dailyCalDao.updateDailyCal(addCal) == 1;
	}

	@Override
	public boolean subtractDailyCal(DailyCal subCal) {
		boolean flag = dailyCalDao.subtractDailyCal(subCal) == 1;
		dailyCalDao.delDailyCal(subCal);
		return flag;
	}

	@Override
	public boolean delDailyCal(DailyCal dailycal) {
		return dailyCalDao.delDailyCal(dailycal) == 1;
	}

	@Override
	public int getMonthlyCalSum(DailyCal dailyCal) {
		System.out.println(dailyCal);
		return dailyCalDao.selectMonthlyDailyCal(dailyCal);
	}

}
