package com.heeexy.example.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.heeexy.example.entity.MyContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

@Component
public class Docker {

    private DockerClient dockerClient;


    @PostConstruct
    private void init() {
        this.dockerClient = connectDocker();
    }

    @Value("${wechat.docker.host}")
    private String host;

    @Value("${wechat.docker.port}")
    private Integer port;

    /**
     * 连接docker服务器
     *
     * @return
     */
    public DockerClient connectDocker() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerTlsVerify(false)
                .withDockerConfig("/Users/yangtengcan/Documents/workspace/youduworkspace/SpringBoot-Shiro-Vue/back/target/classes/ca")
                .withDockerCertPath("/Users/yangtengcan/Documents/workspace/youduworkspace/SpringBoot-Shiro-Vue/back/target/classes/ca")
                .withDockerHost("tcp://" + host + ":" + port)
                .build();
        return DockerClientBuilder.getInstance(config).build();
    }

    /**
     * 创建容器
     *
     * @return
     */
    public CreateContainerResponse createContainers(String containerName, String imageName) {
        //映射端口8088—>80
        ExposedPort tcp80 = ExposedPort.tcp(80);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(8088));

        return dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80).exec();
    }

    public CreateContainerResponse createContainers(MyContainer containd) {
        return dockerClient.createContainerCmd(containd.getImage())
                .withName(containd.getName())
                .withEnv(containd.getEnvs())
                .exec();
    }

    public void Run(MyContainer containd) {
        CreateContainerResponse createContainerResponse = createContainers(containd);
        startContainer(createContainerResponse.getId());
    }

    /**
     * 启动容器
     *
     * @param containerId
     */
    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    /**
     * 根据名字判断容器是否存在
     *
     * @param name
     * @return Container|null
     */
    public Container getContainerByNames(String name) {
        List<String> names = new ArrayList<>();
        names.add(name);
        List<Container> list = dockerClient.listContainersCmd().withShowAll(true).withNameFilter(names).exec();
        return !list.isEmpty() ? list.get(0) : null;
    }

    public Boolean isRunning(Container container) {
        return container.getStatus().indexOf("Exited") <= 0;
    }

    /**
     * 启动容器
     *
     * @param containerId about container id
     */
    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void restartContainerByNames(String name) {
        Container container = getContainerByNames(name);
        if (container != null) {
            dockerClient.restartContainerCmd(container.getId()).exec();
        }
    }

    /**
     * 删除容器
     *
     * @param containerId about container id
     */
    public void removeContainer(String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void removeContainerByNames(String name) {
        Container container = getContainerByNames(name);
        if (container == null) {
            return ;
        }
        dockerClient.removeContainerCmd(container.getId()).exec();
    }

    public static void main(String[] args) {

        //  docker run --name 21 --env INSTANCE-ID=22 --env CDKEY=52bcdee5-b4b3-42a1-9b99-18f671b85d54 -d jeeves
        // docker info
        Docker docker = new Docker();
        Container c = docker.getContainerByNames("cbb272c0-18e4-4c5b-81cd-18a75357629f");
        System.out.println(c);
        //创建容器
        //CreateContainerResponse container = Docker.getInstance().createContainers("test1", "hello-world:latest");
        //启动容器
        //Docker.getInstance().startContainer(container.getId());

    }


}
