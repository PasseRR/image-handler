package com.gome.image.config;

import com.gome.image.filter.ImageFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 图片filter配置
 * @author xiehai1
 * @date 2018/02/24 10:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Configuration
public class ImageFilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ImageFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}