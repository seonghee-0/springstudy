package com.sh.app01.ex03;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan : Component 하는 곳을 찾겠다
@ComponentScan(basePackages = "com.sh.app01.ex03")  // 둘 이상이면  = {"", "", ""} 


// @Configuration : Bean 을 만드는 클래스 ( Bean 설정에 관련된 클래스 )
@Configuration
public class AppConfig {


  
}
