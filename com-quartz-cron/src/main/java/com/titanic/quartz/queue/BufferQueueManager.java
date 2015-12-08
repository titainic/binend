package com.titanic.quartz.queue;

/**
 * 队列管理 Created by wb-yangbin.d on 2015/12/5.
 */
public interface BufferQueueManager<T> {
	/**
	 * 根据key获取队列
	 * 
	 * @param key
	 * @return
	 */
	public T getQueue(String key);

	/**
	 * 根据key创建队列
	 * 
	 * @param key
	 * @param queueSize
	 *            指定队列大小
	 * @return
	 */
	public boolean createQueue(String key, int queueSize);

	/**
	 * 删除队列
	 * 
	 * @param key
	 * @return
	 */
	public boolean delQueue(String key);
}
