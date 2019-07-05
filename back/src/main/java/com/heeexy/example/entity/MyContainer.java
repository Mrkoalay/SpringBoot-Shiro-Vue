package com.heeexy.example.entity;

import lombok.Data;

@Data
public class MyContainer {
    private String instanceId;
    private String cdKey;

    public MyContainer() {
    }

    public MyContainer(String instanceId, String cdKey) {
        this.instanceId = instanceId;
        this.cdKey = cdKey;
    }
}
