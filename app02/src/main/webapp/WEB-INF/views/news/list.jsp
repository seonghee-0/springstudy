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
  <c:forEach items="${newsList}" var="news" varStatus="vs"> <%-- varStatus="vs": items 에서 인덱스 뽑아올 때 사용 --%>
    <div class="news" data-news-no="${vs.index + 1}">
      <div>${news.title}</div>
      <div>조회수 ${news.hit}</div>
    </div> 
  </c:forEach>
  
  <script>  
    // js 로 뉴스 전체 불러오기 
  	const newsList = document.getElementsByClassName('news');
    for(const news of newsList) { // newsList(전체뉴스) 에 있는 news(개별뉴스) 하나씩 뽑는 향상for문 
      news.addEventListener('click',evt=>{
        const newsNo = evt.currentTarget.dataset.newsNo;
        location.href='${contextPath}/news/detail?newsNo=' + newsNo;
      })
    }
  	// event.target은 자식 요소인를 리턴하고, event.currentTarget은 부모 요소를 반환
  	
  </script>
  <script>
		//jquery로 뉴스 전체 불러오기
  /*
  	$('.news').on('click', evt =>{
  	  const newsNo = $(evt.currentTarget).data('newsNo') // data 속성을 끌어오는data()
  	  location.href='${contextPath}/news/detail?newsNo=' + newsNo;
  	})
 */
  </script>
</body>
</html>