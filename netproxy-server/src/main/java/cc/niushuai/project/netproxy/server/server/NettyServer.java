package cc.niushuai.project.netproxy.server.server;

import cc.niushuai.project.netproxy.server.config.App;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 使用netty开启server
 *
 * @author niushuai
 * @date 2022/4/22 15:20
 */
@Slf4j
@Component
@Order(1)
public class NettyServer implements ApplicationRunner {



    @Async
    @Override
    public void run(ApplicationArguments args) {

        nettyServerStart();
    }

    private void nettyServerStart() {
        try {
            App.serverBootstrap = new ServerBootstrap();

            App.channelFuture = App.serverBootstrap.group(App.bossGroup, App.workGroup)
                    // 使用NioSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // bossGroup处理器
                    // .handler()
                    // workGroup处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(App.serverHandler);
                        }
                    })
//                    .bind(App.Netty.BIND_HOST, App.Netty.BIND_PORT)
                    .bind(App.Netty.BIND_PORT)
                    .sync();


            App.channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("Netty Server Started At {}:{}", App.Netty.BIND_HOST, App.Netty.BIND_PORT);
                } else {
                    log.error("Netty Server Start Failed!");
                }
            });

        } catch (InterruptedException e) {
            log.error("Netty Server Runtime Occurs Something {}", e.getMessage(), e);
        } finally {
//            App.bossGroup.shutdownGracefully();
//            App.workGroup.shutdownGracefully();
        }
    }
}
