package com.min.boot.interceptor;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.min.boot.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component // SigninCheck를 객체 만들기 위해서
public class SigninCheck implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    // pre -- 어떤 메소드 이전에 실행
    /* preHandle() 메소드 반환타입 
     true 반환 : 가로챈 요청을 수행해라
     false 반환 : 가로 챈 요청을 수행하지말아라. */
    // 여기서 가로챈 요청은 /write.page
    
    HttpSession session = request.getSession();
    UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
    if(loginUser == null) { // loginUser 가 null 이면
      
      // 1) 가입페이지로 바로 이동시키기
      response.sendRedirect(request.getContextPath()+"/user/signin.page");

      // 2) 메세지 보여준 뒤 로그인 페이지로 이동시키기
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.print("if(confirm('로그인이 필요한 기능입니다. 로그인 페이지로 이동할까요?')){");
      out.print("   location.href='"+request.getContextPath()+"/user/signin.page'");
      out.print(" }else{ ");
      out.print("  alert('요청이 취소되었습니다.')");
      out.print("  history.back()");
      out.print(" }");
      out.println("</script>");
      out.close();
  
      // 가로 챈 요청을 수행하지 않는다.
      return false;
      
    }
    
    // 가로 챈 요청을 수행한다
    return true;
  }
  
  
}
