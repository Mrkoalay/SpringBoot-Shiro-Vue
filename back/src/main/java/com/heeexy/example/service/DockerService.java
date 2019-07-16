package com.heeexy.example.service;

import com.github.dockerjava.api.model.Container;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.entity.MyContainer;
import com.heeexy.example.service.impl.CdKeyServiceImpl;
import com.heeexy.example.util.Docker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DockerService {
    @Autowired
    private Docker docker;

    private static final Logger logger = LoggerFactory.getLogger(DockerService.class);

    @Value("${wechat.docker.image-name}")
    private String imageName;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.password}")
    private String redisAuth;

    @Value("${wechat.protocol.host}")
    private String wechatProtocolHost;

    @Value("${wechat.protocol.ws-port}")
    private String wechatWsPort;

    @Value("${wechat.protocol.http-port}")
    private String wechatHttpPort;

    @Async("taskExecutor")
    public void runContainer(CdKey cdKey) {
        String cdkey = cdKey.getCdkey();
        Container container = docker.getContainerByNames(cdkey);
        if (container == null) {
            logger.info("=====>开始启动容器 " + cdkey);
            createContainer(cdKey);
        } else if (!docker.isRunning(container)) {
            logger.info("=====>容器已经停止，正在重启 " + cdkey);
            restartContainer(cdKey);
        }
    }

    private void restartContainer(CdKey cdKey) {
        docker.restartContainerByNames(cdKey.getCdkey());
        logger.info("=====>容器启动完成 " + cdKey.getCdkey());
    }

    private void createContainer(CdKey cdKey) {
        String cdkey = cdKey.getCdkey();
        String image = imageName;
        String[] envs = new String[]{"CDKEY=" + cdkey, "REDIS_HOST=" + redisHost, "REDIS_PORT=" + redisPort, "REDIS_AUTH=" + redisAuth,
                "PROTOCOL_HOST=" + wechatProtocolHost, "WEBSOCKET_PORT=" + wechatWsPort, "HTTP_PORT=" + wechatHttpPort};
        System.out.println(Arrays.toString(envs));
        docker.Run(new MyContainer(cdkey, image, envs));
        logger.info("=====>容器启动完成 " + cdkey);
    }
}
