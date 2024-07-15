package com.min.boot.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.min.boot.dto.BlogDTO;

import jakarta.servlet.http.HttpSession;

public interface IBlogService {
  ResponseEntity<Map<String,Object>> summernoteImageUpload(MultipartFile multipartFile);

  int saveBlog(BlogDTO blogDTO, HttpSession session);
  

}
