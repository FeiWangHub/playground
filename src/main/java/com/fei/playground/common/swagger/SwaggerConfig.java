//package com.fei.playground.common.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @author yuansq
// * @Description
// * @create 2021-04-20 17:44
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(
//                RequestHandlerSelectors.basePackage("cn.aresoft.idea.apiController")).paths(PathSelectors.any()).build();
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("使用Swagger2构建RESTful API").description("rest api 文档构建").version("1.0").build();
//    }
//}