package com.titanic.netty.tcp.client;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by wb-liujuan.i on 2015/12/16.
 */
public class NettyTcpClientHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = Logger.getLogger(NettyTcpClientHandler.class);


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        //messageReceived方法,名称很别扭，像是一个内部方法.
        logger.info("client接收到服务器返回的消息:"+msg);

    }

    /**
     *  Netty新出来的 待测试
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception
    {
        //messageReceived方法,名称很别扭，像是一个内部方法.
        logger.info("client接收到服务器返回的消息:"+o+"-->messageReceived");
    }
}
