package com.planeta.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan ( basePackages = { "com.planeta" })
public class IndexController {
	
	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}
	

}
