<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="Blog" name="title"/>
</jsp:include>


<h1 class="title">BLOG List</h1>

<div>
  <a href="${contextPath}/blog/write.page">작성하러가기</a>
</div>
<div id="blog-list"></div>

<script>
  
  if('${saveBlogMessage}' !== '')
    alert('${saveBlogMessage}');

  var page = 1;
  const paging = (p)=>{
    page = p;
    getBlogList();
  }
  
  const getBlogList = () =>{
    $ajax({
      type: 'get',
      url: '${contextPath}/blog/getBlogList.do',
      data:'page=' + page, // page를 변수로 만들어서 어떻게 처리할까
      }).done(resData=>{ // {"blogList:[{},{},...], "paging":"< 1 2 3 4 5 6 7 8 9 10>"}
  			document.getElementById('paging').innerHTML = resData.paging;
  			const blogList = document.getElementById('blog-list');
  			const paging = document.getElementById('paging');
  			if(resData.blogList.length === 0){
  			  blogList.innerHTML = '<div>등록된 블로그가 없습니다 </div>';
  			  paging.innerHTML ='';
  			  return;
  			}
  			paging.innerHTML = resData.paging;
  			blogList.innerHTML = ''; // 빈문자열 시작
  			for(const blog of resData.blogList){
  			  let str = '<div class="blog" data-blog-no="'+blog.blogNo+'">';
  			  str += '<div>' + blog.name +'</div>';
  			  str += '<div>' + blog.title +'</div>';
  			  str += '<div>' + blog.hit +'</div>';
  			  str += '<div>' + blog.createDt +'</div>';
  			  str += '</div>';
  			  blogList.innerHTML += str; 
  			}
      })
   }
  
  const detail = () =>{
   /* $('.blog').on('click', evt=>{ 
   		원래 있던 코드가 아닌 47번 라인에서 innerhtml추가된 코드이기때문에 해당 코드는 작동안한다.
   		아래와 같이 작성해야 click이벤트 발동함*/
    $(document).on('click', '.blog', evt=>{
      if('${sessionScope.loginUser.userNo}' == evt.target.dataset.userNo){
        location.href='${contextPath}/blog/detail.do?blogNo=' + evt.target.dataset.blogNo;// 블로그 번호
      }else {
        location.href='${contextPath}/blog/updateHit.do?blogNo=' + evt.target.dataset.blogNo;// 블로그 번호
      }
    })
  }
  
  getBlogList();
  detail();
  </script>

<%@ include file="../layout/footer.jsp" %>