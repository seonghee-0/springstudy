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
public class BbsDTO {
  private int bbsNo;
  private String contents;
  // private int userNo;  userNo 대신 userDTO넣기
  private UserDTO userDTO;
  private Date createDt;
  private int state;
  private int depth;
  private int groupNo;
  private int groupOrder;
}