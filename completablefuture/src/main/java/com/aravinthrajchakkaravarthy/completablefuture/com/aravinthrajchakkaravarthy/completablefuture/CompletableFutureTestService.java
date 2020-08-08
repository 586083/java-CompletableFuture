package com.aravinthrajchakkaravarthy.completablefuture.com.aravinthrajchakkaravarthy.completablefuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CompletableFutureTestService {

	public List<String> fetchListString1() throws InterruptedException, ExecutionException {
		System.out.println("Thread Details Service Start"+Thread.currentThread().getId()+"-"+new Date());
		Thread.sleep(2000);
		List<String> name=new ArrayList<String>();
		name.add("Aravinth");
		System.out.println("result size 1 -"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
		CompletableFuture<List<String>> future1=CompletableFuture.supplyAsync(() -> {
			try {
				return fecthStringList1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			return null;
		}).thenCompose( s -> CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("result size 2.1 -"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
				name.addAll(s);
				name.addAll(fecthStringList2());
				System.out.println("result size 2.2 -"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
				return s;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			return null;
		}));
		
		name.addAll(future1.get()); //block the execution
		System.out.println("Thread Details Service End"+Thread.currentThread().getId()+"-"+new Date());
		System.out.println("result size 3 -"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
		return name;
	}
	
	@Async
	public List<String> fetchListString() throws InterruptedException, ExecutionException {
		System.out.println("Thread Details Service Start"+Thread.currentThread().getId()+"-"+new Date());
		Thread.sleep(2000);
		List<String> name=new ArrayList<String>();
		name.add("Aravinth");
		System.out.println("result size 1"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
		CompletableFuture<List<String>> future1=CompletableFuture.supplyAsync(() -> {
			try {
				return fecthStringList1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			return null;
		}).thenApply(obj -> {
			System.out.println("result size 2"+name.size()+","+obj.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
			name.addAll(obj); //triggered once completed
			return obj;
		});
		
		CompletableFuture<List<String>> future2=CompletableFuture.supplyAsync(() -> {
			try {
				return fecthStringList2();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			return null;
		});
		
		name.addAll(future1.get()); //block the execution
		System.out.println("result size 2.5"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
		name.addAll(future2.get()); //block the execution
		System.out.println("Thread Details Service End"+Thread.currentThread().getId()+"-"+new Date());
		//Thread.sleep(3000);
		System.out.println("result size 3"+name.size()+"-"+Thread.currentThread().getId()+"-"+new Date());
		return name;
	}
	
	public List<String> fecthStringList1() throws InterruptedException {
		System.out.println("Thread Details Service 1 Start"+Thread.currentThread().getId()+"-"+new Date());
		Thread.sleep(1000);
		List<String> name=new ArrayList<String>();
		name.add("Raj");
		System.out.println("Thread Details Service 1 End"+Thread.currentThread().getId()+"-"+new Date());
		return name;
	}
	
	public List<String> fecthStringList2() throws InterruptedException {
		System.out.println("Thread Details Service 2 Start"+Thread.currentThread().getId()+"-"+new Date());
		Thread.sleep(3000);
		List<String> name=new ArrayList<String>();
		name.add("Aravinth");
		System.out.println("Thread Details Service 2 End"+Thread.currentThread().getId()+"-"+new Date());
		return name;
	}

}
