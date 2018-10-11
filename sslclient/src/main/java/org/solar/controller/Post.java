package org.solar.controller;

import org.solar.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Post {

	@RequestMapping(value="/",
			method=RequestMethod.GET,
			produces="application/json")
	public ResponseEntity<User> mainPost() {
		String uAge, uName, uStatus;
		User user = new User();
		
		uAge = "18";
		uName = "soalr";
		uStatus = "RawData";
		
		user.setuAge(uAge);
		user.setuName(uName);;
		user.setuStatus(uStatus);
		
		System.out.println("Before Posting");
		user.intro();
		
		
		//
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);
		RestTemplate httpreq = new RestTemplate();
		
		
//		return httpreq.exchange("https://localhost:8443/user", HttpMethod.POST, requestEntity, User.class);
		return httpreq.exchange("http://localhost:8080/user", HttpMethod.POST, requestEntity, User.class);
		
	
	}
}
