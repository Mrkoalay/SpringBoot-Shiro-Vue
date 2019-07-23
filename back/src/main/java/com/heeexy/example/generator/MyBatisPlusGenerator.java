package com.heeexy.example.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        String author = System.getProperty("user.name");
        String restPathName = "back";
        String entityPathName = "back";
        String restPath = System.getProperty("user.dir") + "/" + restPathName + "/src/main/java";
        String entityPath = System.getProperty("user.dir") + "/" + entityPathName + "/src/main/java";

        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(restPath);//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(author);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("2019#docker");
        dsc.setUrl("jdbc:mysql://140.143.226.139:3306/chat?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"wechat_user"}); // 需要生成的表
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setSuperEntityClass("com.heeexy.example.entity.SuperEntity");
        mpg.setStrategy(strategy);

        // 包配置
        // 注意不同的模块生成时要修改对应模块包名
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity("com.heeexy.example.entity");
        pc.setMapper("com.heeexy.example.dao");
        pc.setXml("com.heeexy.example.dao.mapping");
        pc.setService("com.heeexy.example.service");
        pc.setServiceImpl("com.heeexy.example.service.impl");
        pc.setController("com.heeexy.example.controller");

        Map<String, String> pathMap = new HashMap<>();
        pathMap.put("entity_path", joinPath(entityPath, pc.getEntity()));
        pathMap.put("service_path", joinPath(restPath, pc.getService()));
        pathMap.put("service_impl_path", joinPath(restPath, pc.getServiceImpl()));
        pathMap.put("controller_path", joinPath(restPath, pc.getController()));
        pathMap.put("mapper_path", joinPath(restPath, pc.getMapper()));
        pc.setPathInfo(pathMap);

        mpg.setPackageInfo(pc);


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        tc.setXml("/templates/mapper.xml.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);


        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    private static String joinPath(String parentDir, String packageName) {
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(parentDir)) {
            parentDir = System.getProperty("java.io.tmpdir");
        }

        if (!com.baomidou.mybatisplus.core.toolkit.StringUtils.endsWith(parentDir, File.separator)) {
            parentDir = parentDir + File.separator;
        }

        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }

}