package com.oi.oimall.dao;

import java.util.List;

import com.oi.oimall.model.Item;
import com.oi.oimall.model.TestModel;

public interface TestDao {

	List<TestModel> testList(TestModel testModel);

	List<Item> itemList(Item item);

}
