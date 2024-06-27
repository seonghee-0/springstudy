package com.sh.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * ${contextPath}/api/user
 * ${contextPath}/api/board
 * ${contextPath}/api/news
 */

//   /api 로 시작하는 요청을 담당하는 컨트롤러로 만들기
//   ( value만 작성하는 경우엔 value (value = "/api")생략이 가능함 )
@RequestMapping("/api")  


@Controller  // 컨트롤러로 등록하기 
public class MvcController2 {
  /* 반환타입이 void 인 경우  /api/user 요청을 응답 JSP 경로와 이름으로 인식한다. */

  @RequestMapping(value="/user")
  public void user() {
  }
  
  @RequestMapping(value="/board")
  public void board() {
  }
  
  @RequestMapping(value="/news")
  public void news() {

  }
  
  
}
