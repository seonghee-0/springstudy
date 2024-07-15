package com.min.boot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.BbsDTO;

@Mapper
public interface IBbsMapper {
  int insertBbsParent(BbsDTO bbsParent);
  int selectBbsCount();
  List<BbsDTO> selectBbsList(Map<String, Object> params);
  int updateGroupOrder(BbsDTO bbsParent);
  int insertBbsChild(BbsDTO bbsChild);
  int deleteBbs(int bbsNo);
  int selectFindCount(Map<String, Object> params);
  List<BbsDTO> selectFindList(Map<String, Object> params);
}