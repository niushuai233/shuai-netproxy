package cc.niushuai.project.netproxy.client.config.runner;

import cc.niushuai.project.netproxy.client.config.App;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppInitConfig implements ApplicationRunner {

    @Value("${app.url}")
    private String url;

    @Value("${app.port}")
    private Integer port;

    @Value("${app.token}")
    private String token;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (!validate()) {
            // 不满足直接停止程序
            System.exit(0);
        }

        App.Netty.URL = url;
        App.Netty.PORT = port;
        App.Netty.TOKEN = token;

        log.info("Init Bootstrap Info Success! {}:{}, {}", App.Netty.URL, App.Netty.PORT, App.Netty.TOKEN);
    }

    /**
     * 校验所需参数是否满足
     *
     * @return boolean
     *
     * @author niushuai233
     * @date 2022/4/23 15:03
     */
    private boolean validate() {

        boolean urlFlag = StrUtil.isNotEmpty(url);
        if (!urlFlag) {
            log.error("url is null");
        }

        boolean portFlag = null != port;
        if (!urlFlag) {
            log.error("port is null");
        }

        boolean tokenFlag = StrUtil.isNotEmpty(token);
        if (!urlFlag) {
            log.error("token is null");
        }
        return urlFlag && portFlag && tokenFlag;
    }
}
