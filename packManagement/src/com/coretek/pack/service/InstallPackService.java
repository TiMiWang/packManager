package com.coretek.pack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coretek.pack.dao.InstallPackMapper;
import com.coretek.pack.model.InstallPack;
import com.coretek.pack.model.InstallPackExample;

@Service("InstallPackService")
public class InstallPackService implements IInstallPackService {
	
	private InstallPackMapper installPackMapper;
	
	@Inject
	public void setInstallPackMapper(InstallPackMapper installPackMapper) {
		this.installPackMapper = installPackMapper;
	}

	@Override
	public int countByExample(InstallPackExample example) {
		// TODO Auto-generated method stub
		return installPackMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(InstallPackExample example) {
		// TODO Auto-generated method stub
		return installPackMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return installPackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(InstallPack record) {
		// TODO Auto-generated method stub
		return installPackMapper.insert(record);
	}

	@Override
	public int insertSelective(InstallPack record) {
		// TODO Auto-generated method stub
		return installPackMapper.insertSelective(record);
	}

	@Override
	public List<InstallPack> selectByExample(InstallPackExample example) {
		// TODO Auto-generated method stub
		return installPackMapper.selectByExample(example);
	}

	@Override
	public InstallPack selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return installPackMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(InstallPack record,
			InstallPackExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(InstallPack record, InstallPackExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(InstallPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(InstallPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
