package com.q18idc.jwt.demo.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com" })
public class Swagger2Config  implements WebMvcConfigurer {

    @Override
   public  void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("web")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                // base，最终调用接口后会和paths拼接在一起
                .pathMapping("/")
                .select()
                //过滤的接口
                .paths(or(regex("/api/.*")))
                .build()
                .apiInfo(webControllerApiInfo());
    }

    private ApiInfo webControllerApiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("web接口")
                //详细描述
                .description("http://q18idc.com")
                //版本
                .version("1.0")
                //作者
                .contact(new Contact("q18idc.com", "https://q18idc.com/", "admin@q18idc.com"))
                .license("2.0apache许可证")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }



    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class) {
                    // 排除
                    return false;
                }
                if(declaringClass.isAnnotationPresent(RestController.class)) {
                    // 被注解的类
                    return true;
                }
                if(input.isAnnotatedWith(ResponseBody.class)) {
                    // 被注解的方法
                    return true;
                }
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("接口")
                //版本
                .version("1.0")
                //作者
                .contact(new Contact("q18idc.com", null,null))
                .build();
    }

}
