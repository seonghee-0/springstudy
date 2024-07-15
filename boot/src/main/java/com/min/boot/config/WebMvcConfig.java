package com.min.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.min.boot.interceptor.SigninCheck;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    
    /* 정적 자원 관리
     * 정적자원의 주소와 매핑된 디렉터리 명시 */

  registry.addResourceHandler("/static/**") // static 으로 시작하는 모든 경로
    .addResourceLocations("classpath:/static/"); // src/main/resources/static 디렉터리
  
  registry.addResourceHandler("/summernote/**")
    .addResourceLocations("file:C:/summernote/");
  } 
  
  /*
   * @Autowired private SigninCheck signinCheck;
   */
  
  private final SigninCheck signinCheck;
  // final 처리하면 반드시 초기화가 필요 (null 값이면 안됨 )
  // 상단에 @RequiredArgsConstructor 추가 (final 은 값을 전달해야하기때문)
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    
    // signinCheck 인터셉터 추가
    registry.addInterceptor(signinCheck).addPathPatterns("/bbs/write.page");
    //signinCheck 를 언제 동작시킬지 작성 ("/bbs/write.page", "", "" ...);
  }
  
  
  
}
