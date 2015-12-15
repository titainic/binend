package com.titanic.quartz.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 内存队列管理实现 Created by wb-yangbin.d on 2015/12/5.
 */

public class RamBufferQueueManager implements BufferQueueManager
{

	private RamBufferQueueManager()
	{}

	private static RamBufferQueueManager ramBufferQueueManager ;

	public static RamBufferQueueManager getInstance()
	{
		if(ramBufferQueueManager == null)
		{
			ramBufferQueueManager = new RamBufferQueueManager();
		}
		return ramBufferQueueManager;
	}

	private Map<String, RamQueue<String>> mapQueue = new HashMap<String, RamQueue<String>>();

	//队列大小
	private int queueSize =10000;

	@PostConstruct
	public void initQueue()
	{
		createQueue("RamBufferQueue", queueSize);
	}

	public RamQueue getQueue(String key)
	{
		if (null == mapQueue.get(key))
		{
			createQueue(key, queueSize);
		}
		return mapQueue.get(key);
	}

	public boolean createQueue(String key, int queueSize)
	{
		if (mapQueue.get(key) == null)
		{
			mapQueue.put(key, new RamQueue<String>(queueSize));
			return true;
		}
		return false;
	}

	public boolean delQueue(String key)
	{
		if (null != mapQueue.get(key))
		{
			mapQueue.remove(key);
			return true;
		}
		return false;
	}
}
