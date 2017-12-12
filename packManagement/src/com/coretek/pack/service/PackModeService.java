package com.coretek.pack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coretek.pack.dao.PackModeMapper;
import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.PackModeExample;

@Service("packModeService")
public class PackModeService implements IPackModeService {
	

	private PackModeMapper packModeMapper;
	
	public PackModeMapper getPackModeMapper() {
		return packModeMapper;
	}

	@Inject
	public void setPackModeMapper(PackModeMapper packModeMapper) {
		this.packModeMapper = packModeMapper;
	}

	@Override
	public int countByExample(PackModeExample example) {
		// TODO Auto-generated method stub
		return packModeMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(PackModeExample example) {
		// TODO Auto-generated method stub
		return packModeMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return packModeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PackMode record) {
		// TODO Auto-generated method stub
		return packModeMapper.insert(record);
	}

	@Override
	public int insertSelective(PackMode record) {
		// TODO Auto-generated method stub
		return packModeMapper.insertSelective(record);
	}

	@Override
	public List<PackMode> selectByExample(PackModeExample example) {
		// TODO Auto-generated method stub
		return packModeMapper.selectByExample(example);
	}

	@Override
	public PackMode selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return packModeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(PackMode record, PackModeExample example) {
		// TODO Auto-generated method stub
		return packModeMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(PackMode record, PackModeExample example) {
		// TODO Auto-generated method stub
		return packModeMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(PackMode record) {
		// TODO Auto-generated method stub
		return packModeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PackMode record) {
		// TODO Auto-generated method stub
		return packModeMapper.updateByPrimaryKey(record);
	}

}
