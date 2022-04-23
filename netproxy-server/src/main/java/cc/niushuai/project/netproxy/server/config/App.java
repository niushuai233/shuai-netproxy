package cc.niushuai.project.netproxy.server.config;

import cc.niushuai.project.netproxy.server.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * App配置常量类
 *
 * @author niushuai233
 * @date 2022/4/23 14:52
 */
public class App {

    public static ServerBootstrap serverBootstrap;
    /**
     * 负责处理客户端与服务端的连接请求
     */
    public static EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * 负责处理客户端与服务端的业务数据交换
     */
    public static EventLoopGroup workGroup = new NioEventLoopGroup();

    public static ChannelFuture channelFuture = null;

    public static NettyServerHandler serverHandler = new NettyServerHandler();

    public static class Netty {

        /**
         * netty server url
         */
        public static String BIND_HOST;

        /**
         * netty server port
         */
        public static Integer BIND_PORT;
    }
}
