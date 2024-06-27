package com.sh.app03.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sh.app03.dto.BbsDTO;
//@ComponentScan : Component 하는 곳을 찾겠다
//@Component :  @Component가 부착된 클래스 타입의 Bean 을 만들어서 Spring Container 에 보관한다.
//              Bean 의 타입은 클래스 타입이고, Bean 의 이름은 클래스 이름을 camel case 로 바꿔서 등록한다. 
//              (내가 만든 클래스에만 사용하는 방식)
//@Configuration : Bean 을 만드는 클래스 ( Bean 설정에 관련된 클래스 )

/*  
 @Component : Spring Container 에 생성된 Bean 의 타입은 => BbsDAO 타입과 BbsDAOImpl 타입
            : Spring Container 에 생성된 Bean 의 이름은 => bbsDAOImpl (camel case 적용)
*/

@Repository /* DAO 의 @Component 이다 */
public class BbsDAOImpl implements BbsDAO {
  
  List<BbsDTO> bbsList = Arrays.asList(
      new BbsDTO("제목1", "내용1")
    , new BbsDTO("제목2", "내용2")
    , new BbsDTO("제목3", "내용3")
      );
  
  @Override
  public List<BbsDTO> getBbsList() {
    return bbsList;
  }

  @Override
  public BbsDTO getBbsByNo(int bbsNo) {
    // TODO Auto-generated method stub
    return bbsList.get(bbsNo-1); // 인덱스는 0,1,2 게시글은1,2,3
  }

}
