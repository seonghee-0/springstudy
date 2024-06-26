<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
  <body>
  
  
  <%-- 
    Model 객체는 Controller 에서 생성된 데이터를 View 로 전달할 때 사용하는 객체다.
    전달하는 방법은?
    
    model.addAttribute(String name, Object value)
     * value 객체를 name 이름으로 추가해줌
     * View 에서 name 으로 지정된 value 를 사용
     
    model.addAttribute("serverTime", formattedDate);
    데이터 : ${serverTime}                              --%>
  
    <%-- servlet-context.xml's <resources> 태그 동작 확인 --%>
    <div>
      <img src="${contextPath}/resources/images/404.jpg" width="50px">
    </div>
    
    <hr>
    
    <%-- MvcController2's @RequestMapping--%>
    <div>
      <div><a href="${contextPath}/api/user">user</a></div>
      <div><a href="${contextPath}/api/board">board</a></div>
      <div><a href="${contextPath}/api/news">news</a></div>
    </div>
    
    <hr>
    
    <%-- MvcController3's Query String 방식의 요청 파라미터 처리 방식 확인 --%>
    <div>
      <div><a href="${contextPath}/param1">요청1</a></div>
      <div><a href="${contextPath}/param2?page=1">요청2</a></div>
      <div><a href="${contextPath}/param3?page=1&sort=DESC">요청3</a></div>
    </div>
    
    <hr>
    
    <%-- MvcController4's Path Variable 방식의 요청 파라미터 처리 방식 확인 --%>
    <div>
      <div><a href="${contextPath}/app/users/1">요청1</a></div>
      <div><a href="${contextPath}/app/members">요청1</a></div>
      <div><a href="${contextPath}/app/page/1/sort/DESC">요청3</a></div>
    </div>
    
    <hr>
    
    <div>
      <form action="${contextPath}/param"> <!-- method 생략하면 디폴트 get 방식 -->
        <div>
          <label for =""></label>
          <input type="text" name="" id=""/>
        </div> 
        <div>
          <label for =""></label>
          <input type="text" name="" id=""/>
        </div> 
      </form>
    </div>
    
	   <hr>
     
  </body>
</html>