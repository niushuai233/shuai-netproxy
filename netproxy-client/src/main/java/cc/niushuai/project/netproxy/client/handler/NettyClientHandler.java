package cc.niushuai.project.netproxy.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * Netty 客户端处理器
 *
 * @author niushuai233
 * @date 2022/4/23 11:43
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    public NettyClientHandler() {
        super();
        log.info("Initialize NettyClientHandler");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        SocketAddress remoteAddress = ctx.channel().remoteAddress();
        log.info("client| register from: " + remoteAddress);

        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        SocketAddress remoteAddress = ctx.channel().remoteAddress();
        log.info("client| unregister from: " + remoteAddress);

        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SocketAddress remoteAddress = ctx.channel().remoteAddress();
        log.info("client| online from: " + remoteAddress);

        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Server This Is Client 1", CharsetUtil.UTF_8));

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("client| offline from: " + ctx.channel().remoteAddress());

        super.channelInactive(ctx);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Receive Message From Client: {} Message: {}", ctx.channel().remoteAddress(), ((ByteBuf) msg).toString(CharsetUtil.UTF_8));

        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(Unpooled.copiedBuffer("Read Complete.", CharsetUtil.UTF_8));
        log.info("Read Complete.");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        log.error("处理器发生异常: {}", cause.getMessage(), cause);
    }
}
