package cc.niushuai.project.netproxy.server.config.runner;

import cc.niushuai.project.netproxy.server.config.App;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 加载配置信息
 *
 * @author niushuai233
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppInitConfig implements ApplicationRunner {

    @Value("${app.bindHost:0.0.0.0}")
    private String bindHost;

    @Value("${app.bindPort:30010}")
    private Integer bindPort;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        App.Netty.BIND_HOST = bindHost;
        App.Netty.BIND_PORT = bindPort;

        log.info("Init ServerBootstrap Info Success!");
    }
}
