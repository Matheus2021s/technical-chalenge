package br.com.sicredi.technicalchallenge.config.spring_fox_swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiV1_0() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.sicredi.technicalchallenge"))
                .paths(regex(".*/v1.0.*"))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title("Technical Challenge API")
                        .description("Documentation of voting system API v1.0")
                        .build());

    }


    @Bean
    public Docket apiV2_0() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-2.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.sicredi.technicalchallenge"))
                .paths(regex(".*/v2.0.*"))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("2.0")
                        .title("Technical Challenge API")
                        .description("Documentation of voting system API v2.0")
                        .build());
    }
}
