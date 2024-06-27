package com.sh.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
  
  @RequestMapping (value = {"/", "/index"}) //method = RequestMethod.GET은 디폴트라 생략가능
    public String main() {
     return "main";
  }
  
  
}
