package cc.niushuai.project.netproxy.server.proxy.service.impl;

import cc.niushuai.project.netproxy.server.proxy.service.ProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 代理转发处理
 *
 * @author niushuai233
 * @date 2022/4/23 15:53
 */
@Slf4j
@Service
public class ProxyServiceImpl implements ProxyService {

    /**
     * 处理get请求
     *
     * @param proxyChannelId 通道id
     * @param request
     * @param response
     * @return java.lang.Object
     *
     * @author niushuai233
     * @date 2022/4/23 15:56
     */
    @Override
    public Object get(String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        // 判断代理通道id是否存在

        // 找出要请求的本地地址

        // 拼接header

        // 拼接body

        // 利用通道发送消息

        // 解析通道返回的消息

        // 解包, 返回原始数据

        return null;
    }

    /**
     * 处理post请求
     *
     * @param proxyChannelId 通道id
     * @param request
     * @param response
     * @param contextType
     * @return java.lang.Object
     *
     * @author niushuai233
     * @date 2022/4/23 15:57
     */
    @Override
    public Object post(String proxyChannelId, HttpServletRequest request, HttpServletResponse response, String contextType) {
        return null;
    }
}
