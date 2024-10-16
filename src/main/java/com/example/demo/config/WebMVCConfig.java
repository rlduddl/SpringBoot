package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebMVCConfig implements WebMvcConfigurer {
	
	private SessionInterceptor sessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// /admin/** 怨?媛숈? ?⑦꽩?쇰줈 ?몄쬆???꾩슂??URL???낅젰?쒕떎.
		// 泥댄겕??URL ?⑦꽩???щ윭媛쒖씪 寃쎌슦 ?쇳몴濡?援щ텇?댁꽌 ?ｋ뒗??
		// ?? "admin/**", "member/**"
		registry.addInterceptor(sessionInterceptor)
				.addPathPatterns(
							"/admin/**", "/board/write"
						);
	}
	
	
	
}
