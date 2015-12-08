package com.titanic.quartz.queue;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 队列实现
 * Created by wb-yangbin.d on 2015/12/5.
 */
@Component
public class RamQueue<String> implements BufferQueue<String> {

	private BlockingQueue<String> queue;

	public RamQueue(int queueSize) {
		queue = new ArrayBlockingQueue<String>(queueSize);
	}


	public boolean offer(String o) {

		return queue.offer( o);
	}

	public String take() throws InterruptedException {
		return queue.take();
	}

	public int getSize() {
		return queue.size();
	}

	public String[] takeAll() {
		return (String[]) queue.toArray();
	}
}
