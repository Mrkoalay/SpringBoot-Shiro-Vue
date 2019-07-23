package com.heeexy.example.util;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.heeexy.example.entity.MyContainer;


public class DockerTest {

    private static DockerTest instance;
    private DockerClient dockerClient;

    private DockerTest() {
        this.dockerClient = connectDocker();
    }

    public static DockerTest getInstance() {
        if (instance == null) {
            instance = new DockerTest();

        }
        return instance;
    }

    /**
     * 连接docker服务器
     *
     * @return
     */
    public DockerClient connectDocker() {

        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerTlsVerify(true)
                .withDockerCertPath("/Users/yangtengcan/Documents/workspace/youduworkspace/SpringBoot-Shiro-Vue/back/target/classes/ca")
                .withDockerHost("tcp://192.168.254.74:2376")
                .build();
        DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory()
                .withReadTimeout(1000)
                .withConnectTimeout(1000)
                .withMaxTotalConnections(100)
                .withMaxPerRouteConnections(10);

        return DockerClientBuilder.getInstance(config).withDockerCmdExecFactory(dockerCmdExecFactory).build();
    }



    public CreateContainerResponse createContainers(MyContainer containd) {
        CreateContainerResponse container = dockerClient.createContainerCmd(containd.getImage())
                .withName(containd.getName())
                .withEnv(containd.getEnvs())
                .exec();
        return container;
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



    public static void main(String[] args) {

        Info info = DockerTest.getInstance().dockerClient.infoCmd().exec();
        System.out.print(info);

    }


}
