package mzlalal.redisession.redisessioncore.config;

import com.gitee.sop.servercommon.configuration.AlipayServiceConfiguration;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description:
 * @author: Mzlalal
 * @date: 2019/9/26 20:53
 * @version: 1.0
 */
@Configuration
public class OpenServiceConfig extends AlipayServiceConfiguration {

    /**
     * 打开swagger - ui 静态资源
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 创建swagger-ui 界面信息
     */
    @Bean
    public Docket createRestApi() {
        return getDocket();
    }

    protected Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("redisession-core")
                // 文档描述
                .description("redisession-core文档描述")
                .termsOfServiceUrl("redisession-core文档")
                .version("1.0")
                .build();
    }
}
