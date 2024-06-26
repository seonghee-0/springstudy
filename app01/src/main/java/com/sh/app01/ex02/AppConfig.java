package com.sh.app01.ex02;

import java.time.Instant;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : Bean 을 만드는 클래스 ( Bean 설정에 관련된 클래스 )


@Configuration
public class AppConfig {

  /* 메소드 == Bean */
  
  /*
   * 반환타입 : Bean 의 타입( <bean class=""> )
   * 메소드명 : Bean 의 이름( <bean id=""> )
   */
  
  // xml 에 있는 bean 태그를 @Bean 메소드를 활용해서 자바코드로 변경
  
  @Bean
  Adder adder() { // Adder 를 반환해주기로 했으니 return 해야함
    return new Adder();
  }
  
  @Bean  
  Subtractor subtractor() {
    return new Subtractor(); // Subtractor 를 반환해주기로 했으니 return 해야함
    
  }
  
  Date today(){ // new date 가 아닌 다른방식
    return Date.from(Instant.now()); // 오늘의날짜
  }
  
  @Bean
  Calculator calculator1() {
    // default contructor + setter
    Calculator calculator = new Calculator();
    calculator.setMaker("samsung");         // <property name ="maker" value="samsung" /> 동일
    calculator.setPrice(100);               // <property name ="price" value="100"/>  동일
    // 사용하기위해 위에 만들어둔 adder 메소드 호출
    calculator.setAdder(adder());           // <property name ="adder" ref="adder"/>
    calculator.setSubtractor(subtractor()); // <property name ="subtractor" ref="subtractor"/>
    calculator.setBuied(today());           // <property name ="buied" ref="today"/> 동일
    return calculator;
  }
  
  @Bean
  Calculator calculator2() {
    return new Calculator("LG"       // <constructor-arg value="LG">
        ,200                         // <constructor-arg value="200"/> 
        ,adder()                     // <constructor-arg ref="adder"/>
        ,subtractor()                // <constructor-arg ref="subtractor"/>
        ,today());                   // <constructor-arg ref="today"/>
  }
  
}
