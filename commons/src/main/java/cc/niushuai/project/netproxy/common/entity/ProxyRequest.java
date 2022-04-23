package cc.niushuai.project.netproxy.common.entity;

import lombok.Data;

import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Map;

/**
 * server与client通信数据
 *
 * @author niushuai233
 * @date 2022/4/23 16:12
 */
@Data
public class ProxyRequest implements Serializable {

    private static final long serialVersionUID = -6187271995387229835L;

    /**
     * 通道id
     */
    private String proxyChannelId;

    /**
     * 话题元素
     */
    private String topic;

    /**
     * 请求方法 HttpMethod
     */
    private String method;

    /**
     * 实际请求类型 MediaType
     */
    private String contentType;

    /**
     * url 若是get 则参数已经拼接上
     */
    private String url;

    /**
     * 请求头 要求全部转发
     */
    private Map<String, String[]> headers;

    /**
     * cookies
     */
    private Cookie[] cookies;

    /**
     * 请求体
     */
    private Object body;

    public ProxyRequest(String proxyChannelId, String topic) {
        this.proxyChannelId = proxyChannelId;
        this.topic = topic;
    }
}
