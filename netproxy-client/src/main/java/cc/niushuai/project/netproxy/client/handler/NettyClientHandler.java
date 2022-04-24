package cc.niushuai.project.netproxy.client.handler;

import cc.niushuai.project.netproxy.client.config.App;
import cc.niushuai.project.netproxy.common.constant.KeyConstant;
import cc.niushuai.project.netproxy.common.entity.ProxyRequest;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("服务地址: {} 成功上线", ctx.channel().remoteAddress());

        ctx.writeAndFlush(Unpooled.copiedBuffer(JSONUtil.toJsonStr(new ProxyRequest(App.Netty.TOKEN, KeyConstant.REGISTER)), CharsetUtil.UTF_8));

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("从服务端: {} 掉线", ctx.channel().remoteAddress());

        // TODO 此处要重试  直到成功连上
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到服务端: {}消息, message: {}", ctx.channel().remoteAddress(), ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        log.error("Client端Handler发生异常: {}", cause.getMessage(), cause);
    }
}
