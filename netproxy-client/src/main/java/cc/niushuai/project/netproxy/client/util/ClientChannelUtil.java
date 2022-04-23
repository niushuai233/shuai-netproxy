package cc.niushuai.project.netproxy.client.util;

import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


/**
 * 消息工具类
 *
 * @author niushuai233
 * @date 2022/4/23 14:41
 */
@Slf4j
public class ClientChannelUtil {

    private ClientChannelUtil(){}

    private static ClientChannelUtil util = new ClientChannelUtil();

    private ChannelFuture channelFuture;

    public ChannelFuture getChannelFuture() {
        Optional.ofNullable(channelFuture).orElseThrow(() -> new RuntimeException("通信频道未进行初始化操作"));
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    /**
     * 初始化
     *
     * @param channelFuture
     * @return void
     *
     * @author niushuai233
     * @date 2022/4/23 14:43
     */
    public static ClientChannelUtil init(ChannelFuture channelFuture) {
        util = new ClientChannelUtil();
        util.setChannelFuture(channelFuture);
        log.info("初始化通道成功");
        return util;
    }


    /**
     * 发消息
     *
     * @param topic 主题
     * @param data 发送数据
     * @return void
     *
     * @author niushuai233
     * @date 2022/4/23 14:48
     */
    public static Object send(String topic, Object data) {
        log.info("发送消息: {}, {}", topic, data);

        return null;
    }

}
