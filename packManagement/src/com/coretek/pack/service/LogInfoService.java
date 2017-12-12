package com.coretek.pack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coretek.pack.dao.logInfoMapper;
import com.coretek.pack.model.logInfo;
import com.coretek.pack.model.logInfoExample;

@Service("logInfoService")
public class LogInfoService implements IlogInfoService {

	private logInfoMapper logInfoMapper;
	
	
	public logInfoMapper getLogInfoMapper() {
		return logInfoMapper;
	}

	@Inject
	public void setLogInfoMapper(logInfoMapper logInfoMapper) {
		this.logInfoMapper = logInfoMapper;
	}

	@Override
	public int countByExample(logInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(logInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return logInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(logInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(logInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.insertSelective(record);
	}

	@Override
	public List<logInfo> selectByExample(logInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.selectByExample(example);
	}

	@Override
	public logInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return logInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(logInfo record, logInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(logInfo record, logInfoExample example) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(logInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(logInfo record) {
		// TODO Auto-generated method stub
		return logInfoMapper.updateByPrimaryKey(record);
	}

}
