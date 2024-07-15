package com.min.boot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FileUploadUtils {
  
  private static final LocalDate TODAY = LocalDate.now();
  
  public String getUploadPath() { //폴더 구조
    return "/upload/" + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(TODAY);// upload 라는 디렉터리를 하나 만들고 그 아래에
  }
  
  public String getSummernotePath() { //
    return "/summernote/" + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(TODAY);// summernote 라는 디렉터리를 하나 만들고 그 아래에
  }
  
  public String getFilesystemName(String originalFilename){//원래 파일이름을 가지고와서 확장자는 그대로 사용하게끔
    String extension;
    if(originalFilename.endsWith(".tar.gz")) { //특이사항 체크
      extension = ".tar.gz";
    }
    else {
      extension = originalFilename.substring(originalFilename.lastIndexOf("."));
      // 마지막 . 부터 맨 뒤까지 잘라낸다 (file.jpg => .jpg)로 확장자 뽑아냄
    }
    return UUID.randomUUID().toString().replace("-", "") + extension; 
    // UUID : 랜덤값 만들어줌
    /// 중복문제,인코딩문제까지 해결해 준다
  }
  
  
  
  
}


