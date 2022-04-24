package cc.niushuai.project.netproxy.server.handler;

import cc.niushuai.project.netproxy.common.constant.KeyConstant;
import cc.niushuai.project.netproxy.common.entity.ProxyRequest;
import cc.niushuai.project.netproxy.server.util.ServerChannelUtil;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * Netty Server 注册处理机制
 *
 * @author niushuai233
 * @date 2022/4/23 11:06
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public NettyServerHandler() {
        super();
        log.info("Initialize NettyServerHandler");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client {} online id = {}", ctx.channel().remoteAddress(), ctx.channel().id().asLongText());

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("client {} offline id = {}", ctx.channel().remoteAddress(), ctx.channel().id().asLongText());

        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String payload = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        log.info("Receive Message From Client: {} id = {}, message: {}", ctx.channel().remoteAddress(), ctx.channel().id().asLongText(), payload);

        ProxyRequest request = JSONUtil.toBean(payload, ProxyRequest.class);

        // 处理消息内容
        dispatcher(request, ctx.channel());
    }

    private void dispatcher(ProxyRequest request, Channel channel) {

        switch (request.getTopic()) {
            case KeyConstant.REGISTER:
                // 注册
                ServerChannelUtil.register(request.getProxyChannelId(), channel);
                break;
            case KeyConstant.RESPONSE:
                // 对于服务转发做出的响应

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        log.error("Server端Handler发生异常: {}", cause.getMessage(), cause);
    }


}
