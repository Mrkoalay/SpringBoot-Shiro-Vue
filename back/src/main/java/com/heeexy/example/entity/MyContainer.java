package com.heeexy.example.entity;

import lombok.Data;

@Data
public class MyContainer {
    private String name;
    private String image;
    private String[] envs;

    public MyContainer() {
    }

    public MyContainer(String name,String image, String[] envs) {
        this.name = name;
        this.image = image;
        this.envs = envs;
    }
}
