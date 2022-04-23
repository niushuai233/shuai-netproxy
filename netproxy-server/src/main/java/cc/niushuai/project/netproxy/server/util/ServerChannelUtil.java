package cc.niushuai.project.netproxy.server.util;

import cc.niushuai.project.netproxy.common.entity.ProxyRequest;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 通道工具
 *
 * @author niushuai233
 * @date 2022/4/23 16:03
 */
@Slf4j
public class ServerChannelUtil {

    private ServerChannelUtil() {
    }

    private static Map<String, Channel> channels = new HashMap<>();


    public static Map<String, Channel> channels() {
        return channels;
    }

    public static boolean exist(String proxyChannelId) {
        return channels.containsKey(proxyChannelId);
    }

    private static void validate(String proxyChannelId) {
        if (exist(proxyChannelId)) {
            throw new RuntimeException("proxyChannelId " + proxyChannelId + " always exists");
        }
    }

    public static void register(String proxyChannelId, Channel channel) {
        validate(proxyChannelId);

        channels.put(proxyChannelId, channel);

        log.info("proxyChannelId {} register succeed remoteAddress: {}", proxyChannelId, channel.remoteAddress());
    }

    public static void unregister(String proxyChannelId) {
        channels.remove(proxyChannelId);

        log.info("proxyChannelId: {} unregister succeed", proxyChannelId);
    }

    public static Object sendMessage(String proxyChannelId, ProxyRequest proxyRequest) throws Exception {

        validate(proxyChannelId);

        Channel channel = channels().get(proxyChannelId);
        ChannelPromise promise = channel.newPromise();

        channel.writeAndFlush(Unpooled.copiedBuffer(JSONUtil.toJsonStr(proxyRequest), CharsetUtil.UTF_8));

        promise.await(3, TimeUnit.MINUTES);

        return promise;
    }

}


