package com.sh.app07.service;

import java.util.List;

import org.springframework.ui.Model;

import com.sh.app07.dto.UploadDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface IUploadService { // serviceImple은 만들 때 앞에 대문자 I 작성금지
  int registerUpload(HttpServletRequest request);
  List<UploadDTO> getUploadList();
  void loadUpload(int uploadNo, Model model);
  
}
