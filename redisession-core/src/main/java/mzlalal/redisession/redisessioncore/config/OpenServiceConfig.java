package mzlalal.redisession.redisessioncore.config;

import com.gitee.sop.servercommon.configuration.AlipayServiceConfiguration;
import com.gitee.sop.servercommon.swagger.SwaggerSupport;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger ui 以及 sop - service - common 文档配置中心
 * @author: Mzlalal
 * @date: 2019/9/26 20:53
 * @version: 1.0
 */
@Configuration
public class OpenServiceConfig extends AlipayServiceConfiguration {

    /**
     * 开启文档，本地微服务文档地址：http://localhost:8080/doc.html
     * http://ip:port/v2/api-docs
     */
    @Configuration
    @EnableSwagger2
    public static class Swagger2 extends SwaggerSupport {
        @Override
        protected String getDocTitle() {
            return "redisession-core";
        }

        @Override
        protected boolean swaggerAccessProtected() {
            return false;
        }
    }
    // 使用sop文档扩展
//    /**
//     * 打开swagger - ui 静态资源
//     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        super.addResourceHandlers(registry);
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    /**
//     * 创建swagger-ui 界面信息
//     */
//    @Bean
//    public Docket createRestApi() {
//        return getDocket();
//    }
//
//    protected Docket getDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    protected ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                // 文档标题
//                .title("redisession-core")
//                // 文档描述
//                .description("redisession-core文档描述")
//                .termsOfServiceUrl("redisession-core文档")
//                .version("1.0")
//                .build();
//    }
}
