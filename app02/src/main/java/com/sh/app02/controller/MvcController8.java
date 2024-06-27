package com.sh.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sh.app02.vo.MemberVO;


@SessionAttributes(names = "memberVO") /*   Model 에 memberVO 가 저장되면 HttpSession 에 같은 이름으로 저장하시오.  */



@Controller
public class MvcController8 {
  
  @RequestMapping(value = "/member/login" , method= RequestMethod.POST)
  public String login(MemberVO member, @RequestParam String redirectURL) { // 타입으로 모델에 자동 저장함 (첫글자를 소문자-camelcase)
    /*  커맨드 객체 MemberVO member 는 자동으로 Model 에 memberVO 라는 이름으로 저장된다.
     *  해당 이름을 변경하고 싶다면 커맨드 객체 앞에 @ModelAttribute("member") 를 작성해서 member 로 변경이 가능함   */
    return "redirect:" + redirectURL;
  }  
  
  @RequestMapping("/member/logout" )
  public String logout(SessionStatus sessionStatus) {
    // 세션 완료 처리(세션 정보 사라짐)
    sessionStatus.setComplete();
    
    return "redirect:/main";
  }  
  
  @RequestMapping("/member/mypage" )
  public String mypage(@SessionAttribute("memberVO") MemberVO member) { 
    /*  HttpSession에 저장된 memberVO 를 커맨드 객체 MemberVO member 에 저장하시오. 
     *  커맨드 객체는 자동으로 Model 에 저장된다    */
    return "member/mypage";
    
    
  }
  
}
