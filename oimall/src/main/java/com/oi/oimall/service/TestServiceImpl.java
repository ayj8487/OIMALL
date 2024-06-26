package com.oi.oimall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oi.oimall.dao.TestDao;
import com.oi.oimall.model.Item;
import com.oi.oimall.model.TestModel;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{
	
	private final TestDao td;
	
	@Override
	public List<TestModel> testList(TestModel testModel) {

		List<TestModel> testList = td.testList(testModel);
		
		return testList;
	}

	@Override
	public List<Item> itemList(Item item) {
		System.out.println("Service itemList start@@@@");
		List<Item> itemList = td.itemList(item);
		System.out.println("Service itemList > " + itemList);
		return itemList;
	}

}
