package com.aravinthrajchakkaravarthy.completablefuture.com.aravinthrajchakkaravarthy.completablefuture;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CompletableFutureTestController {
	
	@Autowired
	private CompletableFutureTestService service;
	
	@PostMapping(value = "testCompletableFuture")
	public @ResponseBody List<String> fetchListString() throws InterruptedException, ExecutionException{
		System.out.println("Thread Details Controller Start"+Thread.currentThread().getId()+"-"+new Date());
		//List<String> result=service.fetchListString();
		List<String> result=service.fetchListString1();
		System.out.println("Thread Details Controller End"+Thread.currentThread().getId()+"-"+new Date());
		return result;
	}

}
