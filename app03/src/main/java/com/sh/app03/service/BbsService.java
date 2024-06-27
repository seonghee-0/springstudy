package com.sh.app03.service;

import java.util.List;

import com.sh.app03.dto.BbsDTO;

public interface BbsService {
    List<BbsDTO> getBbsList();
    BbsDTO getBbsByNo(int bbsNo);
    // 인터페이스는 객체 생성이 안되기때문에 
    // 본문이 없는(추상메소드)는 미완성이기때문에 객체를 만들 수 없다.
    
}
