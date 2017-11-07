package com.sapient.wellington.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.sapient.wellington.pojo.Order;

@Service
public class GroupingService implements IGroupingService {

	ExecutorService executor;
	
	@PostConstruct
	void init()
	{
		executor= Executors.newFixedThreadPool(10);
		System.out.println("executor created="+executor);
	}
	
	@Override
	public void processOrderForGrouping(Order order)
	{
		
	}
}

class GroupOrderTask implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
