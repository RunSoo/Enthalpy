package com.enthalpy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enthalpy.model.dao.METDao;
import com.enthalpy.model.dto.MET;

@Service
public class METServiceImpl implements METService {

	@Autowired
	private METDao METdao;
	
	@Override
	public List<MET> getMETList() {
		return METdao.selectAll();
	}

}
