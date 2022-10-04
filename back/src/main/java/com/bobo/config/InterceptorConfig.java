package com.bobo.config;

import com.bobo.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry reg) {
        reg.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePattern());
    }

    public List<String> excludePattern(){
        List<String> ret = new ArrayList<String>();
        ret.add("/login");
        ret.add("/category");
        ret.add("/getCategoryBooks/{cid}");
        ret.add("/getAllBooks");
        ret.add("/searchBook/{name}");
        ret.add("/getCNById/{id}");
        return ret;
    }
}