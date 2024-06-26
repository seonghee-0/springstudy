package com.sh.app02.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageVO { // setter 필요! (없으면 초기값 0 과 null 이 나옴)
  private int page;
  private String sort;
}
