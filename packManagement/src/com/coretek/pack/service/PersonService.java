package com.coretek.pack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coretek.pack.dao.PersonMapper;
import com.coretek.pack.model.Person;
import com.coretek.pack.model.PersonExample;

@Service("personService")
public class PersonService implements IPersonSerivce {

	private PersonMapper personMapper;
	
	
	public PersonMapper getPersonMapper() {
		return personMapper;
	}

	@Inject
	public void setPersonMapper(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

	@Override
	public int countByExample(PersonExample example) {
		// TODO Auto-generated method stub
		return personMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(PersonExample example) {
		// TODO Auto-generated method stub
		return personMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return personMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Person record) {
		// TODO Auto-generated method stub
		return personMapper.insert(record);
	}

	@Override
	public int insertSelective(Person record) {
		// TODO Auto-generated method stub
		return personMapper.insertSelective(record);
	}

	@Override
	public List<Person> selectByExample(PersonExample example) {
		// TODO Auto-generated method stub
		return personMapper.selectByExample(example);
	}

	@Override
	public Person selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return personMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Person record, PersonExample example) {
		// TODO Auto-generated method stub
		return personMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Person record, PersonExample example) {
		// TODO Auto-generated method stub
		return personMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Person record) {
		// TODO Auto-generated method stub
		return personMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Person record) {
		// TODO Auto-generated method stub
		return personMapper.updateByPrimaryKey(record);
	}

}
