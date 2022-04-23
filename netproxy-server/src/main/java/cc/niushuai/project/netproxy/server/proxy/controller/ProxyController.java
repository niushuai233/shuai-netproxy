package cc.niushuai.project.netproxy.server.proxy.controller;

import cc.niushuai.project.netproxy.server.proxy.service.ProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求转发控制器
 *
 * @author niushuai233
 * @date 2022/4/23 15:14
 */
@Slf4j
@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Resource
    private ProxyService proxyService;

    @GetMapping("/**")
    public Object getRequestProxy(@RequestHeader String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        return proxyService.get(proxyChannelId, request, response);
    }

    @PostMapping(value = "/**", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object postRequestProxyForm(@RequestHeader String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        return proxyService.post(proxyChannelId, request, response, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    @PostMapping(value = "/**", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object postRequestProxyMultipartFormData(@RequestHeader String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        return proxyService.post(proxyChannelId, request, response, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    @PostMapping(value = "/**", consumes = MediaType.APPLICATION_XML_VALUE)
    public Object postRequestProxyXml(@RequestHeader String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        return proxyService.post(proxyChannelId, request, response, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    @PostMapping(value = "/**", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object postRequestProxyJson(@RequestHeader String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        return proxyService.post(proxyChannelId, request, response, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    @PostMapping(value = "/**", consumes = MediaType.ALL_VALUE)
    public Object postRequestProxy(@RequestHeader String proxyChannelId, HttpServletRequest request, HttpServletResponse response) {

        return proxyService.post(proxyChannelId, request, response, MediaType.ALL_VALUE);
    }
}
