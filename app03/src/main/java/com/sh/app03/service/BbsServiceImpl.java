package com.sh.app03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.app03.dao.BbsDAO;
import com.sh.app03.dto.BbsDTO;
/*
@Component : SpringContainer에 생성되는 Bean 의 타입은 => BbsServiceImpl, BbsService 두개
           : SpringContainer에 생성되는 Bean 의 이름은 => bbsServiceImpl  (camel case 적용)
*/
import com.sh.app03.utils.PageUtils;

@Service  /* 서비스의 @Component */
public class BbsServiceImpl implements BbsService {

  //  ApplicationContext ctx = new AnnotationConfigApplicationContext("com.sh.app03.dao");
  //  Bbs bbsDATO = ctx.getBean("bbsDAOImpl",BbsDAO.class);
  
  /* 서비스는 DAO 객체를 필드로 선언하고 사용함 */
  
  @Autowired /*  Spring Container 에 저장된 Bean 중에서 BbsDAO 타입의 Bean 을 가져와라 (자동으로 타입비교해서 가지고옴) */
  private BbsDAO bbsDAO; // 필드선언
  
  @SuppressWarnings("unused")
  @Autowired 
  private PageUtils pageUtils; // component 를 해놔야 가지고올 수 있다.
  

  
  // @Repository 싱글톤작업을 안해도 객체가 하나만 만들어짐

  
  @Override
  public List<BbsDTO> getBbsList() {
    return bbsDAO.getBbsList();
  }

  @Override
  public BbsDTO getBbsByNo(int bbsNo) {
    return bbsDAO.getBbsByNo(bbsNo);
  }

}
