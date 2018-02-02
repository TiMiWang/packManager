package com.coretek.pack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coretek.pack.dao.InstallPackViewMapper;
import com.coretek.pack.model.InstallPackView;
import com.coretek.pack.model.InstallPackViewExample;

@Service("installPackViewService")
public class InstallPackViewService implements IInstallPackViewService{

	InstallPackViewMapper installPackViewMapper;
	
	@Inject
	public void setInstallPackViewMapper(InstallPackViewMapper installPackViewMapper) {
		this.installPackViewMapper = installPackViewMapper;
	}

	@Override
	public int countByExample(InstallPackViewExample example) {
		// TODO Auto-generated method stub
		return installPackViewMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(InstallPackViewExample example) {
		// TODO Auto-generated method stub
		return installPackViewMapper.deleteByExample(example);
	}

	@Override
	public int insert(InstallPackView record) {
		// TODO Auto-generated method stub
		return installPackViewMapper.insert(record);
	}

	@Override
	public int insertSelective(InstallPackView record) {
		// TODO Auto-generated method stub
		return installPackViewMapper.insertSelective(record);
	}

	@Override
	public List<InstallPackView> selectByExample(InstallPackViewExample example) {
		// TODO Auto-generated method stub
		return installPackViewMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(InstallPackView record,
			InstallPackViewExample example) {
		// TODO Auto-generated method stub
		return installPackViewMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(InstallPackView record,
			InstallPackViewExample example) {
		// TODO Auto-generated method stub
		return installPackViewMapper.updateByExample(record, example);
	}

}
