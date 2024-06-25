package com.sh.app01.ex01;

import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CalculatorEx {

  public static void main(String[] args) {

    // Spring Container 에 있는 Bean 처리       : ApplicationContext
    // close() 처리가 가능한 ApplicationContext : AbstractApplicationContext
    // 특히, XML 파일로 만든 Bean 처리          : GenericXmlApplicationContext 

    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/sh/app01/ex01/app-context.xml");
    
    Adder adder           = ctx.getBean("adder", Adder.class);
    Subtractor subtractor = ctx.getBean("subtractor", Subtractor.class);
    //Subtractor subtractor = (Subtractor)ctx.getBean("subtractor");
    
    Calculator calculator1 = ctx.getBean("calculator1", Calculator.class);
    Calculator calculator2 = ctx.getBean("calculator2", Calculator.class);
    
  //  Calculator calculator2  = (Calculator)ctx.getBean("calculator2");
    
    adder.add(10, 15);
    subtractor.sub(10, 5);
    System.out.println(calculator1.getMaker()+","+calculator1.getPrice()+","+calculator1.getBuied());
    System.out.println(calculator2.getMaker()+","+calculator2.getPrice()+","+calculator2.getBuied());
    // Date date = new Date();
    
    ctx.close();
  }
  
}
