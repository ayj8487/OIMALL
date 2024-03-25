package com.oi.oimall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oi.oimall.model.TestModel;
import com.oi.oimall.service.TestService;

@Controller
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
	
}
