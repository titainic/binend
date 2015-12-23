package com.titanic.netty.tcp;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wb-yangbin.d on 2015/12/21.
 */
public class NettyContextUtils
{
	/**
	 * 获取客户端IP
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getIPString(ChannelHandlerContext ctx) {
		String ipString = "";
		String socketString = ctx.channel().remoteAddress().toString();
		int colonAt = socketString.indexOf(":");
		ipString = socketString.substring(1, colonAt);
		return ipString;
	}

	/**
	 * 获取客户端ip加端口
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getRemoteAddress(ChannelHandlerContext ctx) {
		String socketString = "";
		socketString = ctx.channel().remoteAddress().toString();
		return socketString;
	}




}
