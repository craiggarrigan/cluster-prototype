package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.service.AwaitingPollRepository;

@RestController
public class PollController {
	
	@Autowired
	private AwaitingPollRepository repo;
	
	@RequestMapping(path="/api/poll")
	public DeferredResult<String> poll(){
		DeferredResult<String> dr = new DeferredResult<>(10000L, "timeout");
		repo.addAwaitingPoll(dr);
		return dr;
	}

}
