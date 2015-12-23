package com.titanic.netty.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wb-liujuan.i on 2015/12/16.
 */
//@ spring注解
public class NettyTcpServer
{

    private Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

    private static final Logger logger = Logger.getLogger(NettyTcpServer.class);
    private static final String IP = "127.0.0.1";
    private static final int PORT = 9999;
    /**用于分配处理业务线程的线程组个数 */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//默认  CPU*2
    /** 业务出现线程大小*/
    protected static final int BIZTHREADSIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
    protected static void run() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new NettyTcpServerHandler());
            }
        });

        b.bind(IP, PORT).sync();  //绑定IP,端口
        logger.info("TCP服务器已启动");
    }

    /**
     * 服务端发送数据到客户端
     *
     * @param clientIP
     *            客户端IP
     * @param o
     *            发送的数据
     * @return
     */
    public void serverToClientMsg(String clientIP, Object o) {
        String str = (String) o;
        String enStr = null ;
//        try
//        {
            //服务端发送加密
//            enStr = AESUtil.encodeServerText(str);
//        } catch (InvalidKeyException e)
//        {
//            logger.error(e.getMessage());
//        } catch (IllegalBlockSizeException e)
//        {
//            logger.error(e.getMessage());
//        } catch (BadPaddingException e)
//        {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e)
//        {
//            logger.error(e.getMessage());
//        }
        map.get(clientIP).writeAndFlush(o);
    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

//    public static void main(String[] args) throws Exception {
//        logger.info("开始启动TCP服务器...");
//        TcpServer.run();
////		TcpServer.shutdown();
//    }


    public Map<String, Channel> getMap()
    {
        return map;
    }

    public void setMap(Map<String, Channel> map)
    {
        this.map = map;
    }
}
