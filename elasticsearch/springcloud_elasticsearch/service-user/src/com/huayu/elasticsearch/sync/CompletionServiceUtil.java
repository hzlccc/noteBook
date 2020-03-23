package com.huayu.elasticsearch.sync;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceUtil {

	private static int numThread = 5;
	private static CompletionService<String> completionService;
	private static ExecutorService executor = null;
	
	public static CompletionService<String> getService(){
		executor = Executors.newFixedThreadPool(numThread);
		completionService = new ExecutorCompletionService<String>(executor);
		return  completionService;
	}
	
	
	public static void result(int sum) throws InterruptedException, ExecutionException{
		for(int i = 0; i < sum; i++ ){
			Future<String> f = completionService.take();
			System.out.println(f.get());
		}
		
		executor.shutdown();
		
	}
}