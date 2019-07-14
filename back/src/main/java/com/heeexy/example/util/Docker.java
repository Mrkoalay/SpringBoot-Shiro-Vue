package com.heeexy.example.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.heeexy.example.entity.MyContainer;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

public class Docker {

    private static Docker instance;
    private DockerClient dockerClient;

    private Docker() {
        this.dockerClient = connectDocker();
    }

    public static Docker getInstance() {
        if (instance == null) {
            instance = new Docker();

        }
        return instance;
    }

    /**
     * 连接docker服务器
     *
     * @return
     */
    public DockerClient connectDocker() {

        System.out.println(1);
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerTlsVerify(false)
                .withDockerConfig("/Users/yangtengcan/Documents/workspace/youduworkspace/SpringBoot-Shiro-Vue/back/target/classes/ca")
                .withDockerCertPath("/Users/yangtengcan/Documents/workspace/youduworkspace/SpringBoot-Shiro-Vue/back/target/classes/ca")
                .withDockerHost("tcp://140.143.226.139:2375")
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

        CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80).exec();
        return container;
    }

    public CreateContainerResponse createContainers(MyContainer containd) {
        CreateContainerResponse container = dockerClient.createContainerCmd(containd.getImage())
                .withName(containd.getName())
                .withEnv(containd.getEnvs())
                .exec();
        return container;
    }

    public String Run(MyContainer containd){
        CreateContainerResponse createContainerResponse = createContainers(containd);
        return startContainer(createContainerResponse.getId());
    }

    /**
     * 启动容器
     *
     * @param containerId
     */
    public String startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
        return  containerId;
    }

    /**
     * 启动容器
     *
     * @param containerId
     */
    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     *
     * @param client
     * @param containerId
     */
    public void removeContainer( String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public static void main(String[] args) {

        //  docker run --name 21 --env INSTANCE-ID=22 --env CDKEY=52bcdee5-b4b3-42a1-9b99-18f671b85d54 -d jeeves
        // docker info

        //创建容器
        CreateContainerResponse container = Docker.getInstance().createContainers("test1", "hello-world:latest");
        //启动容器
        Docker.getInstance().startContainer(container.getId());

    }


}
