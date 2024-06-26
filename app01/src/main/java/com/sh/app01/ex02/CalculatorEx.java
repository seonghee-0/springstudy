package com.sh.app01.ex02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class CalculatorEx {

  public static void main(String[] args) {

    // Spring Container 에 있는 Bean 처리       : ApplicationContext
    // close() 처리가 가능한 ApplicationContext : AbstractApplicationContext
    // @Bean으로 만든 Bean 처리                 : AnnotationConfigApplicationContext 

    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    Adder adder           = ctx.getBean("adder", Adder.class); 
    Subtractor subtractor = ctx.getBean("subtractor", Subtractor.class);
    // Subtractor subtractor = (Subtractor)ctx.getBean("subtractor"); -- 이름만 전달하는 방식 (캐스팅해야함)
    
    Calculator calculator1 = ctx.getBean("calculator1", Calculator.class);
    Calculator calculator2 = ctx.getBean("calculator2", Calculator.class);
    
    // Calculator calculator2  = (Calculator)ctx.getBean("calculator2");
    

    System.out.println(calculator1.getMaker()+","+calculator1.getPrice()+","+calculator1.getBuied());
    System.out.println(calculator2.getMaker()+","+calculator2.getPrice()+","+calculator2.getBuied());
    // Date date = new Date();
    
    calculator1.getAdder().add(10, 15);
    calculator1.getSubtractor().sub(10, 15);

    calculator2.getAdder().add(10, 15);
    calculator2.getSubtractor().sub(10, 15);
    
    ctx.close();
  }
  
}
