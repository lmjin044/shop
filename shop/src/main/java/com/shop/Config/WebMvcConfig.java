package com.shop.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    //application properties에서 설정한 저장 경로
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/image/**")
                .addResourceLocations(uploadPath);
    }
}

/*
그래서 이거 왜함?

1) 실제 업로드 경로-에서 사용하는 주소를 연결하기 위해
2) 실제 업로드 경로는 웹에서 사용할 수 없는 경로라 웹에서는 /image/를 쓰겠다는 일종의 선언
3) 즉
    웹 = /image/12.jpg           의 정보를 요청하면
    실제경로 = C:/shop/12.jpg     의 경로에서 이미지를 제공

    으로, 웹에서는 C드라이브를 인식할 수 없으므로 이렇게 하는거임.

그리고 이거 이후로 엔티티, 레포지터리, 서비스 순으로 만들거임.
*/
