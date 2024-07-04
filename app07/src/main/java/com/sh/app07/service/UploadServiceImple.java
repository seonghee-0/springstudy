package com.sh.app07.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.sh.app07.dto.FileDTO;
import com.sh.app07.dto.UploadDTO;
import com.sh.app07.mapper.UploadMapper;
import com.sh.app07.utils.FileUploadUtils;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class UploadServiceImple implements IUploadService {
  private UploadMapper uploadMapper;
  private FileUploadUtils fileUploadUtils;
  

  @Autowired
  public UploadServiceImple(UploadMapper uploadMapper, FileUploadUtils fileUploadUtils) {
    super();
    this.uploadMapper = uploadMapper;
    this.fileUploadUtils = fileUploadUtils;
  }
  
  @Override
  public int registerUpload(HttpServletRequest request) {

    MultipartResolver resolver = new StandardServletMultipartResolver();
    MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
    
    String uploader = request.getParameter("uploader");
    String ip = request.getRemoteAddr();
    UploadDTO upload = UploadDTO.builder()
         .uploader(uploader)
         .ip(ip)
         .build();
    
    uploadMapper.insertUpload(upload);// UploadMapper 에서 upload에 uploadNo를 저장한다.
    
    int uploadNo = upload.getUploadNo(); // insert업로드 호출 후에 사용해야한다. 
    String uploadPath = fileUploadUtils.getUploadPath();
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists())// 업로드 디렉터리가 존재하지않으면
      uploadDir.mkdirs(); // 만들어라
    
    List<MultipartFile> files = multipartRequest.getFiles("files");
    
    files.stream().forEach(file ->{ // files 에서 each 를 하나씩 꺼내면 file 이라고 하겠다.
      String originalFilename = file.getOriginalFilename(); // 원본 파일이름 가지고오기
      String filesystemName = fileUploadUtils.getFilesystemName(originalFilename);
      File uploadFile = new File(uploadDir, filesystemName);
      try {
        //업로드한 파일은 여기로 저장된다 (저장하는 코드)
        file.transferTo(uploadFile);
      }catch(Exception e) {
        e.printStackTrace();
      }
      FileDTO fileDTO = FileDTO.builder()
          .uploadPath(uploadPath)
          .originalFilename(originalFilename)
          .filesystemName(filesystemName)
          .uploadNo(uploadNo)
          .build();
      uploadMapper.insertFile(fileDTO);
    });
    
    return 1; //무조건 성공
  }

  @Override
  public List<UploadDTO> getUploadList() {
    return uploadMapper.getUploadList();
  }
  
  @Override
  public void loadUpload(int uploadNo, Model model) {
    model.addAttribute("upload", uploadMapper.getUploadByNo(uploadNo));
    model.addAttribute("fileList", uploadMapper.getFileList(uploadNo));
  }


}
