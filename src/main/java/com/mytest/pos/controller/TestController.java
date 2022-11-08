package com.mytest.pos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	@GetMapping
	public String getHome() {
		return "This is home page";
	}
	
	@GetMapping("/test")
	public String getTest() {
		return "This is test page";
	}

}
