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
<title>Insert title here</title>
<script src="${contextPath}/resources/lib/jquery-3.7.1.min.js"></script>
</head>
<body>

  <script>  
   if('${insertResult}' !== ''){
   		alert('${insertResult}') ; 
   	  //location.href="${contextPath}";
   }
   
  </script>
</body>
</html>