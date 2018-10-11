package org.soalr.controller;

import java.util.Date;

import org.soalr.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

	@RequestMapping(value = "/",
			method=RequestMethod.GET,
			produces="application/json")
	public String homePage() {
		return "Welcome to HomePage - " + new Date().toString();
	}
	
	
	@RequestMapping(value = "/user",
			method=RequestMethod.POST,
			consumes="application/json",
			produces="application/json")
	public User postPage(@RequestBody User user) {
		user.intro();
		user.setuStatus("change by Server");
		return user;
	}
}
