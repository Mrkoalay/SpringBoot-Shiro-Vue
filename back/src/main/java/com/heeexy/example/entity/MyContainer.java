package com.heeexy.example.entity;

import lombok.Data;

@Data
public class MyContainer {
    private String image;
    private String[] envs;

    public MyContainer() {
    }

    public MyContainer(String image, String[] envs) {
        this.image = image;
        this.envs = envs;
    }
}
