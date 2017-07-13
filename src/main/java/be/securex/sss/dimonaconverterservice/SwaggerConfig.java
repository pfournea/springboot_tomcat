package be.securex.sss.dimonaconverterservice;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

/**
 * Created by 6060 on 7/03/2016.
 */
@EnableSwagger2
@Configuration
@Profile({"dev", "tst", "acc"})
public class SwaggerConfig {

    @Value("${version}")
    private String version;

    @Bean
    public Docket disclaimerServiceApi() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String[].class)
                .directModelSubstitute(XMLGregorianCalendar.class,String.class)
                .groupName("Manage Contract Event")
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.regex("/api/.*"))
                .paths(Predicates.not(PathSelectors.regex("/public/HealthPage")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SSS Services - Dimona Converter Services")
                .description("Manage the creation and conversion of a ContractEvent")
                .version(version)
                .build();
    }

}
