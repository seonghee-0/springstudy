package com.min.boot.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BlogDTO {
  private int blogNo;
  private String title;
  private String contents;
  private int Hit;
  // private UserDTO userDTO -- 통으로 dto 를 넣고 데이터를 꺼내 사용하는방법 
  // 통으로 넣지 않고 풀어서 사용하는방법
  private int userNo;
  private String name;
  private String email;
  
  private Date createDt;
  private Date modifyDt;
  
  
  
}
