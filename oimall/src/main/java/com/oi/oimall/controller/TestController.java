package com.oi.oimall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oi.oimall.model.Item;
import com.oi.oimall.model.TestModel;
import com.oi.oimall.service.TestService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {
	
	private final TestService ts;
	
	public TestController(TestService ts) {
		this.ts = ts;
	}	

	@GetMapping("/testList")
	@ResponseBody
	public Map<String, Object> test(TestModel testModel) {
		
		List<TestModel> testList = ts.testList(testModel);
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("testList", testList);
		
		return response;
	}
	
//	@GetMapping("/api/items")
//	public List<String> getItems() {
//		List<String> items = new ArrayList<>();
//		
//		items.add("alpha");
//		items.add("Bata");
//		items.add("Gama");
//		
//		return items;
//	}
	
	
	@GetMapping("/api/items")
	public List<Item> itemList(Item item) {
		System.out.println("Controller itemList start@@@@@");
		List<Item> itemList = ts.itemList(item);
		System.out.println("Controller itemList > " + itemList);
		return itemList;
	}
	
	
}
