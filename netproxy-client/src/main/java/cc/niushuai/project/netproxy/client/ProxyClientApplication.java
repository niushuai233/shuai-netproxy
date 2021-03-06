package cc.niushuai.project.netproxy.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 代理客户端
 *
 * @author niushuai
 * @date 2022/4/22 13:18
 */
@EnableAsync
@SpringBootApplication
public class ProxyClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProxyClientApplication.class);
    }
}
