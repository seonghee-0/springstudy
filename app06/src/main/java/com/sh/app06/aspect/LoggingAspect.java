package com.sh.app06.aspect;

import java.util.Arrays;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
  
  /* PointCut 표현식 (어떤 클래스의 어떤 메소드에서 적용할 것인가?) */
  @Pointcut(value = " execution(* com.sh.app06.controller.*Controller.wp_*(..))")
  // 약속된 규칙의 사용
      
  // @Pointcut(value = "반환타입 클래스명.메소드(매개변수)())
  // 반환타입에상관관없음.
  
 public void setPoitecut() {
    
  }
  
  
  /* Advice 
   * 
   * Before Advice 생성 방법
   * 1. 반환타입 : void
   * 2. 매개변수 : JoinPoint */
  @Before(value = "setPoitecut()") //setPoitecut 호출
  public void logging(JoinPoint joinPoint) {
    /* 요청 Log 남기기 : 요청 방식/주소/파라미터 */
  
    HttpServletRequest request = 
        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); // 이걸 호출하면  ServletRequestAttributes 이 나온다
    
    String requestMethod = request.getMethod();
    String requestURI = request.getRequestURI();
    
    String params ="" ; 
    for(Map.Entry<String, String[]>entry : request.getParameterMap().entrySet()) {
      params += entry.getKey() + ":" + Arrays.toString(entry.getValue()) + " ";
     //entry.getValue()
      
    }
    log.debug("{} | {}",requestMethod, requestURI);
    log.debug("{}" , params);
    
  }
  
}
