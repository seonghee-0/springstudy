package com.sh.app07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class UploadConfig {

  @Bean // 반드시 Bean 의 이름은 항상 MultipartResolver 여야 한다!!
  MultipartResolver multipartResolver() { // 반드시 Bean 의 이름은 항상 MultipartResolver 여야 한다!!
    return new StandardServletMultipartResolver();
  }
  
}
