package org.java.examples.concurrent.test;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {

	public static void main(String[] args) throws InterruptedException {
		LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
		Thread[] ts = new Thread[1000];

		for (int i = 0; i < 1000; i++) {
			ts[i] = new Thread(() -> {
				Random random = new Random();
				long value = random.nextLong();
				System.out.println(value);
				accumulator.accumulate(value); // 比较value和上一次的比较值，然后存储较大者
			});
			ts[i].start();
		}
		for (int i = 0; i < 1000; i++) {
			ts[i].join();
		}
		System.out.println(accumulator.longValue());
	}

}
