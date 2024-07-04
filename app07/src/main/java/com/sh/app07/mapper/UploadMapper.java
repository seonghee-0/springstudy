package com.sh.app07.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sh.app07.dto.FileDTO;
import com.sh.app07.dto.UploadDTO;

@Mapper
public interface UploadMapper {
  int insertUpload(UploadDTO upload);
  int insertFile(FileDTO file);
  List<UploadDTO> getUploadList();
 UploadDTO getUploadByNo(int uploadNo);
 List<FileDTO> getFileList(int uploadNo);
}
