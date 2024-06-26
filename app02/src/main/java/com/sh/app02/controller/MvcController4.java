package com.sh.app02.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// app 로 시작하는 요청을 담당하는 컨트롤러로 만들기  ( value 만 작성하는 경우엔 value (value = "/app")생략이 가능함 )
   
@RequestMapping("/app")  
@Controller  // 컨트롤러로 등록하기 
public class MvcController4{
  
  /* Path Variable  : 경로에 포함된 변수*/
  
  @RequestMapping("/users/{id}")
  public String users(@PathVariable(value = "id") int userId) { //value 값은 {id} 로 맞춤
    System.out.println(userId);
     
    return "index";
  }
  
  
  @RequestMapping(value = {"/members","/members/{id}"}) // 값이 있을때와 없을때의 경로 구분해서 작성
  public String members(@PathVariable(value = "id", required = false) Optional<String> opt) {
    int memberId = Integer.parseInt(opt.orElse("1"));
    System.out.println(memberId);
    return "index";
  }
 
  @RequestMapping("/page/{page}/sort/{sort}")
  public String page( @PathVariable(value="page") int page, @PathVariable(value="sort") String sort) {
    System.out.println(page);
    System.out.println(sort);
    return "index";
    
  }
  
}