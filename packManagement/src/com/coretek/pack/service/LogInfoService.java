package com.coretek.pack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coretek.pack.dao.LogInfoMapper;
import com.coretek.pack.model.LogInfo;
import com.coretek.pack.model.LogInfoExample;

@Service("logInfoService")
public class LogInfoService implements IlogInfoService {

	private LogInfoMapper logInfoMapper;
	
	
	public LogInfoMapper getLogInfoMapper() {
		return logInfoMapper;
	}

	@Inject
	public void setLogInfoMapper(LogInfoMapper logInfoMapper) {
		this.logInfoMapper = logInfoMapper;
	}

	@Override
	public int countByExample(LogInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(LogInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return logInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(LogInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.insertSelective(record);
	}

	@Override
	public List<LogInfo> selectByExample(LogInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.selectByExample(example);
	}

	@Override
	public LogInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return logInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(LogInfo record, LogInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(LogInfo record, LogInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(LogInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByPrimaryKey(record);
	}

	
}
