package com.dl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dl.service.Challenge1Service;

@RestController
public class Challenge1Controller {

	@Autowired
	private Challenge1Service service;

	@GetMapping("/connected")
	public String connected(@RequestParam(value = "origin", required = true) String origin,
			@RequestParam(value = "destination", required = true) String destination) {
		return service.connected(origin, destination);
	}

}
