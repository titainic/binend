package com.titanic.quartz.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存队列管理实现 Created by wb-yangbin.d on 2015/12/5.
 */
@Component
public class RamBufferQueueManager implements BufferQueueManager {

	private static final Logger logger = LoggerFactory.getLogger("server");


	private Map<String, RamQueue<String>> mapQueue = new HashMap<String, RamQueue<String>>();

	private int queueSize ;

//	@PostConstruct
//	public void initQueue() {
//		int size = 10000;
//		if (queueParam != null) {
//			Map<String, Integer> queueMap = QueueUtils.parseQueueStr(queueParam.getQueueParam());
//			for (Map.Entry<String, Integer> entry : queueMap.entrySet()) {
//				createQueue(entry.getKey(), entry.getValue());
//				size = entry.getValue();
//			}
//			queueSize =size;
//		}
//
//	}

	public RamQueue getQueue(String key) {
		if (null == mapQueue.get(key)) {
			createQueue(key, queueSize);
			logger.error("没有标识为"+key+"的队列，创建一个标识为"+key+"的队列");
		}
		return mapQueue.get(key);
	}

	public boolean createQueue(String key, int queueSize) {
		if (mapQueue.get(key) == null) {
			mapQueue.put(key, new RamQueue<String>(queueSize));
			return true;
		}
		return false;
	}

	public boolean delQueue(String key) {
		if (null != mapQueue.get(key)) {
			mapQueue.remove(key);
			return true;
		}
		return false;
	}
}
