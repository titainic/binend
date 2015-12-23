package com.titanic.netty.tcp.server;

import com.titanic.netty.tcp.NettyContextUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

/**
 * Created by wb-liujuan.i on 2015/12/16.
 */
//@Component
@ChannelHandler.Sharable
public class NettyTcpServerHandler extends SimpleChannelInboundHandler
{
	private static final Logger logger = Logger.getLogger(NettyTcpServerHandler.class);

	//@Resource
	NettyTcpServer nettyTcpServer;

	/**
	 * 记录client的channel，用于服务端发送消息到client 使用
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);

		logger.info("Cilent"+ NettyContextUtils.getRemoteAddress(ctx)+" 接入连接");
		//往channel map中添加channel信息
		nettyTcpServer.getMap().put(NettyContextUtils.getIPString(ctx), ctx.channel());
	}


	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("Server接收到消息:" + msg);
		ctx.channel().writeAndFlush("yes, server is accepted you ,nice !" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warn("Unexpected exception from downstream.", cause);
		ctx.close();
	}

	/**
	 *  Netty 5.0新出来的。待测试
	 * @param channelHandlerContext
	 * @param o
	 * @throws Exception
     */
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception
	{
//		logger.warn("Unexpected exception from downstream.", o.toString());
		channelHandlerContext.close();
	}
}
