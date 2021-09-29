package com.wuzx.springcloudalibaba.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName Knife4jConfiguration.java
 * @Description Knife4j配置
 * @createTime 2021年09月29日 16:42:00
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    /**
     * Spring容器注入的Bean对象OpenApiExtensionResolver
     * 通过调用Docket对象的extensions方法进行插件赋值
     * 插件赋值需要调用OpenApiExtensionResolver提供的buildExtensions方法，该方法需要一个逻辑分组名称，就是开发者在yml配置文件中配置的group名称
     */
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }


    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        String groupName="1.2.x";
        Docket docket=new Docket(DocumentationType.OAS_30)
                .host("https://www.baidu.com")
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wuzx.springcloudalibaba.conotroller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //.title("swagger-bootstrap-ui-demo RESTful APIs")
                .description("# swagger-bootstrap-ui-demo RESTful APIs")
                .termsOfServiceUrl("http://www.xx.com/")
                .contact(new Contact("wuzhixuan","www.baidu.com","wuzhixuano@qq.com"))
                .version("1.0")
                .build();
    }

}
