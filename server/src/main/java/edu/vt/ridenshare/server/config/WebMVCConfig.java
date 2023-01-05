package edu.vt.ridenshare.server.config;

import edu.vt.ridenshare.server.common.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    public void addInterceptors(InterceptorRegistry registry){
        //Interceptors for login interceptor, when there are pages needed to be login, this will triggered
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/sso/**","/","/js/**","/html/**","/image/**","/css/**");
    }

}
