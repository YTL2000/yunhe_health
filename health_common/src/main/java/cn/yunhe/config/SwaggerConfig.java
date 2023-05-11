package cn.yunhe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @version 1.0
 * @auther YTL
 * @className SwaggerConfig
 * since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.yunhe.controller"))//要扫描的包
                .paths(PathSelectors.any())//指定的请求路径
                .build();
    }

    //配置swagger信息  apiinfo
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云和健康")
                .description("云体检健康管理系统")
                .version("v3.0")
                .contact("云和数据")
                .build();

    }

}
