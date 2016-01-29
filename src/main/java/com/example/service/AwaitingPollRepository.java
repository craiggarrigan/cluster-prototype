package com.example.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.hazelcast.core.ITopic;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

@Component
public class AwaitingPollRepository implements MessageListener<AwaitingPollRepository.NotifyAwaitingPollEvent> {
	
	/**
	 * Local set of awaiting polls.
	 */
	private final Set<DeferredResult<String>> awaitingPolls = new HashSet<>();

	/**
	 * Distributed Topic used to communicate with remote AwaitingPollRepository instances.
	 */
	private final ITopic topic;
	
	@Autowired
	public AwaitingPollRepository(HazelcastInstance hazelcastInstance){
		topic = hazelcastInstance.getTopic("AwaitingPollRepositoryEventBus");
		topic.addMessageListener(this);
	}
	
	public void addAwaitingPoll(DeferredResult<String> dr){
		awaitingPolls.add(dr);
	}
	
	public void notifyAwaitingPolls(){
		notifyLocalAwaitingPolls();
		notifyRemoteAwaitingPolls();
	}

	private void notifyLocalAwaitingPolls(){
		for(Iterator<DeferredResult<String>> it = awaitingPolls.iterator(); it.hasNext();){
			DeferredResult<String> dr = it.next();
			it.remove();
			dr.setResult(UUID.randomUUID().toString());
		}
	}

	private void notifyRemoteAwaitingPolls(){
		topic.publish(new NotifyAwaitingPollEvent());
	}

	public void onMessage(Message<NotifyAwaitingPollEvent> message){
		notifyLocalAwaitingPolls();
	}

	public static class NotifyAwaitingPollEvent implements Serializable {}

}
