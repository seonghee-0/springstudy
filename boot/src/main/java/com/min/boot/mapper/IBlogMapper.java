package com.min.boot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.BlogDTO;
import com.min.boot.dto.ImageDTO;

@Mapper
public interface IBlogMapper {
  int insertSummernoteImage(ImageDTO imageDTO);
  int insertBlog(BlogDTO blogDTO);
  int getBlogCount();
  List<BlogDTO> getBlogList(Map<String, Object> params);
  
}
