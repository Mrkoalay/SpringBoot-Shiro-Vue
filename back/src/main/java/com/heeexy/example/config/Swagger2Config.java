package com.heeexy.example.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author yangtc
 * @Description MVC视图配置
 * @Date 19:20 2018/12/17
 * @Param
 * @return
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
    public Docket createRestApi() {
        ParameterBuilder langBuilder = new ParameterBuilder();
        ParameterBuilder authorizationBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        langBuilder.name("Accept-Language").description("默认中文，en英文。非必需，一般是需要显示英文时传参")
                .modelRef(new ModelRef("String")).parameterType("header")
                .required(false).build();
        authorizationBuilder.name("Authorization").description("登录之后返回的鉴权码")
                .modelRef(new ModelRef("String")).parameterType("header")
                .required(false).build();

        parameters.add(langBuilder.build());
        parameters.add(authorizationBuilder.build());

     ;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.POST,
                        Arrays.asList(new ResponseMessageBuilder()
                                        .code(2)
                                        .message("未登录")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(200)
                                        .message("请求成功")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(401)
                                        .message("鉴权错误")
                                        .build()))
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("cssAPI在线文档")
                .description("能看文档的尽量别找我")
                .termsOfServiceUrl("http://www.css.cn")
                .version("1.0")
                .build();
    }
}
