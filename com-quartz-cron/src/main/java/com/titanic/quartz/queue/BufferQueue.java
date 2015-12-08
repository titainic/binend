package com.titanic.quartz.queue;

/**
 * 队列操作抽象类
 */
public interface BufferQueue<TbusMessage> {

	public boolean offer(TbusMessage e);

	public TbusMessage take() throws InterruptedException;

	public int getSize();

	public TbusMessage[] takeAll();

}
