package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.example.service.AwaitingPollRepository;

@RestController
public class NotifyController {
	
	@Autowired
	private AwaitingPollRepository repo;
	
	@RequestMapping(path="/api/notify", method=RequestMethod.POST)
	public void notifyAwaitingPolls(){
		repo.notifyAwaitingPolls();
	}

}
