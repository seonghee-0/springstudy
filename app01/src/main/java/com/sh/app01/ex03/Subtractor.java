package com.sh.app01.ex03;

import org.springframework.stereotype.Component;

//@Component :  @Component가 부착된 클래스 타입의 Bean 을 만들어서 Spring Container 에 보관한다.
//              Bean 의 타입은 클래스 타입이고, Bean 의 이름은 클래스 이름을 camel case 로 바꿔서 등록한다. 
//              (내가 만든 클래스에만 사용하는 방식! )


@Component
public class Subtractor {

  public void sub(int a, int b) {
    System.out.println(a + "-" + b + "=" + (a - b));
  }

}
