package cc.niushuai.project.netproxy.server.proxy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求转发接口
 *
 * @author niushuai233
 * @date 2022/4/23 15:58
 */
public interface ProxyService {

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
    Object get(String proxyChannelId, HttpServletRequest request, HttpServletResponse response);

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
    Object post(String proxyChannelId, HttpServletRequest request, HttpServletResponse response, String contextType);
}
