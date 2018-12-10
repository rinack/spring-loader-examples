package org.java.examples.concurrent.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class CompareTest {

	public static final int THREAD_COUNT = 1000;
	static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
	static CompletionService<Long> completionService = new ExecutorCompletionService<Long>(pool);
	static final AtomicLong atomicLong = new AtomicLong(0L);
	static final LongAdder longAdder = new LongAdder();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < THREAD_COUNT; i++) {
			completionService.submit(new Callable<Long>() {
				@Override
				public Long call() throws Exception {
					for (int j = 0; j < 100000; j++) {
						longAdder.increment();
					}
					return 1L;
				}
			});
		}
		for (int i = 0; i < THREAD_COUNT; i++) {
			Future<Long> future = completionService.take();
			Long lg = future.get();
			System.out.println("Long：" + lg);
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		pool.shutdown();
	}

}
