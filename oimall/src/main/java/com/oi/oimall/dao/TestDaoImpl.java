package com.oi.oimall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oi.oimall.model.TestModel;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestDaoImpl implements TestDao{

	private final SqlSession session;
	
	@Override
	public List<TestModel> testList(TestModel testModel) {
		
		List<TestModel> testList = session.selectList("testList", testModel);
		
		return testList;
	}

	
}
