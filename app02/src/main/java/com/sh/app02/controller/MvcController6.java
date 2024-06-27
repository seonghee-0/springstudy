package com.sh.app02.controller;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MvcController6 {
  
  /*
   * RedirectAttribute
   * 1. redirect 할 때 전달할 데이터를 저장한다
   * 2. addFlashAttribute 형태로 저장해야 최종 이동 장소까지 전달된다.
   * 3. attribute 형태로 저장하면 Model 에 저장된다. 
   */
 
  @RequestMapping("/blog/list") // 2 요청
  public String list() {
    return "blog/list"; /* forward 이동할 땐 JSP 경로 작성 : /WEBINF/views/blog/list.jsp */
  }
  
  
  @RequestMapping("/blog/register") // 1 요청
  public String register(RedirectAttributes rttr) { // 최근방식
    
    int result = Math.random() < 0.7 ? 1:0; //성공과 실패는 int 값으로 넘어옴
    rttr.addFlashAttribute("insertResult", result > 0 ? "블로그 등록 성공" : "블로그 등록 실패");
    // addFlashAttribute 를 사용해서 저장
    return "redirect:/blog/list";  /*  redirect 이동할 땐 Mapping 작성 @RequestMapping("/blog/list") */
    // 1 요청:"/blog/register" ,  2. 요청 :"blog/list" 이기때문에 insertResult 는 2에서 확인 가능
    
  }
  
  @RequestMapping("/blog/detail")
  public String detail(int blogNo, Model model){ // jsp로 forward하는곳이기때문에 model
    model.addAttribute("blogNo", blogNo); // forward 이동할 땐 @추가 설명적기
    
    
    return "blog/detail"; 
  }
  
  @RequestMapping("/blog/modify")
  public String modify(@RequestParam int blogNo, RedirectAttributes rttr) {// 요청 파라미터를 받는 @RequestParam
    int result = Math.random() < 0.7 ? 1:0;
    
    rttr.addAttribute("blogNo",blogNo) //blogNo저장    {} 형식으로 확인 가능 @추가 설명적기
      .addFlashAttribute("updateResult", result > 0 ? "블로그 수정 성공" : "블로그 수정 실패");
    
    return "redirect:/blog/detail?blogNo={blogNo}"; //모델에 저장되어있는(즉, request 에 저장된 변수) 값 꺼내서 사용
  }
  
  
  @RequestMapping("/blog/remove")
  public void remove(HttpServletRequest request ,HttpServletResponse response) { // 옛날방식

    // 응답을 직접 만드는 방식 ( 백 영역에 프론트 코드가 포함되는 방식)
    try {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
    
      int result = Math.random() < 0.7 ? 1 : 0;
      
      out.println("<script>");
      if(result > 0) {
        out.println("alert('블로그 삭제 성공');");
        out.println("location.href=" + request.getContextPath() + "/blog/list");
      } else {
        out.println("alert('블로그 삭제 실패');");
        out.println("history.back();");
      }
      out.println("</script>");
      out.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
  }
  

  
}
