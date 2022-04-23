package cc.niushuai.project.netproxy.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 代理服务端
 *
 * @author niushuai
 * @date 2022/4/22 13:17
 */
@EnableAsync
@SpringBootApplication
public class ProxyServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProxyServerApplication.class);
    }
}
