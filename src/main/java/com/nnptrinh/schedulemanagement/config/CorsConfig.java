package com.nnptrinh.schedulemanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng CORS cho tất cả các URL
                .allowedOrigins("*") // Điều chỉnh nguồn gốc tùy theo ứng dụng Angular của bạn
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Các phương thức HTTP cho phép
                .allowedHeaders("*"); // Tất cả các header được phép (hãy điều chỉnh dựa trên yêu cầu của bạn)
    }
}