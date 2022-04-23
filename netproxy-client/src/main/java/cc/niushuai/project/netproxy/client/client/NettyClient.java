package cc.niushuai.project.netproxy.client.client;

import cc.niushuai.project.netproxy.client.config.App;
import cc.niushuai.project.netproxy.client.handler.NettyClientHandler;
import cc.niushuai.project.netproxy.client.util.ClientChannelUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * netty 客户端 初始化入口
 *
 * @author niushuai233
 * @date 2022/4/23 11:43
 */
@Slf4j
@Component
@Order(1)
public class NettyClient implements ApplicationRunner {

    /**
     * 客户端循环Group
     */
    private NioEventLoopGroup loopGroup = new NioEventLoopGroup();

    @Async
    @Override
    public void run(ApplicationArguments args) {

        clientStart();
    }

    private void clientStart() {
        try {
            Bootstrap bootstrap = new Bootstrap();

            ChannelFuture channelFuture = bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    })
                    // 与服务端建立连接
                    .connect(App.Netty.URL, App.Netty.PORT).sync();

            ClientChannelUtil.init(channelFuture);

        } catch (InterruptedException e) {
            log.error("初始化客户端失败: {}", e.getMessage(), e);
        } finally {
//            loopGroup.shutdownGracefully();
        }
    }
}
