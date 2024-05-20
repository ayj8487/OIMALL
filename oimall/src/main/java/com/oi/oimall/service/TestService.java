package com.oi.oimall.service;

import java.util.List;

import com.oi.oimall.model.Item;
import com.oi.oimall.model.TestModel;

public interface TestService {

	List<TestModel> testList(TestModel testModel);

	List<Item> itemList(Item item);

}
