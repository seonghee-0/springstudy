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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>
    <h1>책 관리하기</h1>
    <div>
      <label for="bookNo">책번호</label>
      <input type="text" id="bookNo" >
    </div>
    <div>
      <label for="title">책제목</label>
      <input type="text" id="title">
    </div>
    <div>
      <label for="author">저자</label>
      <input type="text" id="author" >
    </div>
    <div>
      <button id="init-btn">초기화</button>
      <button id="register-btn">등록</button>
      <button id="modify-btn">수정</button>
      <button id="remove-btn">삭제</button>
    </div>
  </div>
  
  <hr>
  <div>
    <table border="1">
      <thead>
        <tr>
          <td>책번호</td>
          <td>제목</td>
          <td>저자</td>
          <td></td>
        </tr>
      </thead>
      <tbody id="books">
      
      </tbody>
    </table>
  </div>

  <script>
  	
  const books = $('#books');
  const bookNo = $('#bookNo');
  const title = $('#title');
  const author = $('#author');
  const initBtn = $('#init-btn');
  const registerBtn = $('#register-btn');
  const modifyBtn = $('#modify-btn');
  const removeBtn = $('#remove-btn');
  
  
  
  const GetBooks = (url, dataType) => {
  	$.ajax({
  	  type: 'get',
  	  url : '${contextPath}' + url,  
  	  dataType : dataType  // 받아올 데이터타입 (변수)
  	  }).done(resData =>{ // 성공했을 때 처리하는 함수
  	   
  	    books.empty();
  	  
    	  if(dataType === 'xml'){
        	 // XML
        	  $.each(resData.getElementsByTagName('book'), (i, book)=>{
        	  let str = '<tr>';
       	    str += '<td>' + book.getElementsByTagName('bookNo')[0].textContent + '</td>'; // book. : 배열에있는 요소 하나씩   	    
       	    str += '<td>' + book.getElementsByTagName('title')[0].textContent + '</td>'; // book. : 배열에있는 요소 하나씩   	    
       	    str += '<td>' + book.getElementsByTagName('author')[0].textContent + '</td>';
       	    str += '<td><button type="button" class="detail-btn" data-book-no="'+ book.getElementsByTagName('bookNo')[0].textContent +'">상세</button></td>';
       	   	str += '</tr>';
      	    books.append(str);
        	  //console.log(book.getElementsByTagName('bookNo')[0].textContent); //bookNo 에 있는 콘텐츠 텍스트 내용만 가지고오기
        	 });
  	  	}else if(dataType ==='json'){
  	    }
  	 }).fail(jqXHR =>{ // 동작 실패했을 때 파라미터 받아오는부분
  			alert(jqXHR.responseText);
    	})
    }
  	 
  	   /*
  	  // json
  	 // books.empty();
  	  $.each(resData.books, (i, book)=>{ // resData.books, (인덱스, 요소)=>{
  	    let str = '<tr>';
  	    str += '<td>' + book.bookNo + '</td>'; // book. : 배열에있는 요소 하나씩   	    
  	    str += '<td>' + book.title + '</td>'; // book. : 배열에있는 요소 하나씩   	    
  	    str += '<td>' + book.author + '</td>'; // book. : 배열에있는 요소 하나씩   	    
  	    str += '</tr>';
  	    books.append(str);
  	  })
  	 */
  	const GetBookByNo = () =>{
  	  $(document).on('click', '.detail-btn', evt => {
    		$.ajax({
    		  type: 'get',
      	  url : '${contextPath}/api/books/' + evt.target.dataset.bookNo,  
      	  dataType : 'json'
    		  }).done(resData=>{
      	    books.empty();
    		    bookNo.val(resData.book.bookNo).prop('readonly', true); // 속성값
    		    title.val(resData.book.title);
    		    author.val(resData.book.author);
    		    registerBtn.prop('disabled',true);
    		    modifyBtn.prop('disabled',false);
    		    removeBtn.prop('disabled',false);
    		  }).fail(jqXHR=>{
    		    alert(jqXHR.responseText);
				})
  	  })
  	}
  	

  const RegisterBook = () =>{
    registerBtn.on('click',evt=>{
      $.ajax({
        type:'post',
    		url : '${contextPath}/api/books',
    		contentType: 'application/json', // 내가 보내주는 데이터의 타입
    		data: JSON.stringify({
					bookNo: bookNo.val(),
					title : title.val(),
					author: author.val()
   			}),
   			dataType:'json'
    	}).done(resData => {
				if(resData.isSuccess){
				  alert(resData.inserted + "에 등록되었습니다.");
				  GetBooks('/api/books.json','json');
				  initBtn.trigger('click');
				}else{
				  alert("책 등록이 실패했습니다");
				  }
    	}).fail(jqXHR=>{
		    alert(jqXHR.responseText);
    })
    })
  }

  // 호출을 해야 main.jsp 동작했을 때 바로 실행이 됨
  //Init();
  //GetBooks('/api/books','json');
  // GetBooks('/api/books.json','json');
  GetBooks('/api/books.xml','xml');
  GetBookByNo(); 
  RegisterBook();
  </script>

  
</body>
</html>