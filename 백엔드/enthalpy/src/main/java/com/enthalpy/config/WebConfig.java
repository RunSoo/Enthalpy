package com.enthalpy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.enthalpy.interceptor.LoginInterceptor;
import com.enthalpy.interceptor.SessionInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	
	@Autowired
	private SessionInterceptor sessionInterceptor;
	 
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/api/login");
//	}
	
	//CORS 에러를 해결하기 위해서 컨트롤러에서 세분화 하여 처리할 수도 있지만
	//전역설정처럼 여기서 한방에 처리할 수도 있음.. 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:8080", "http://localhost:9999") // 허용할 도메인을 여기에 추가
        .allowedMethods("*")
        .allowedHeaders("*") // 허용할 요청 헤더
        .allowCredentials(true); // 인증 정보 허용 여부
	}
	
	

}
