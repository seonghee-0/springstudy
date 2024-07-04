<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width='device-width', initial-scale=1.0">
<title>app07</title>
</head>
<body>
  <!-- 
   method="post" enctype="multipart/form-data"
   파일첨부 (input type="file")할 때 꼭 필요 
   (enctype 없으면 파일첨부되었다고 인식을 못함)
   -->
  <div>
    <form action="${contextPath}/register.do" method="post" enctype="multipart/form-data"> 
      <div>
        <label for="uploader">작성자</label>
        <input type="text" name="uploader" id="uploader">
      </div>
      <div>
        <input type="file" name="files" id="files" multiple="multiple">
      </div>
      <div>
        <button type="submit">업로드하기</button>
      </div>
    </form>
  </div>
  <a href="${contextPath}/list.do">list</a>
  <hr>
  <div>
  <!-- uploadList 에서 가지고온 하나의 upload -->
    <c:forEach items = "${uploadList}" var = "upload"> 
      <div class="upload">
        <span class="uploadNo">${upload.uploadNo}</span>
        <span class="uploader"><a href="${contextPath}/detail.do?uploadNo=${upload.uploadNo}">${upload.uploader}</a></span>
        <span class="fileCnt">(${upload.fileCnt})</span>
      </div> 
      </c:forEach>
  </div>
</body>
</html>