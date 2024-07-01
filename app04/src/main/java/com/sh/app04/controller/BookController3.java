package com.sh.app04.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sh.app04.dto.BookDTO;
import com.sh.app04.service.BookService;
import com.sun.net.httpserver.Headers;


@Controller 
public class BookController3 {

  private BookService bookService;

  @Autowired
  public BookController3(BookService bookService) {
    super();
    this.bookService = bookService;
  }
  
  /* ResponseEntity 클래스
   *    @ResponseBody 를 내장하고 있는 클래스이다. 
        ( @ResponseBody를 명시할 필요가 없음 )
   */
  @GetMapping(value = {"/api/books", "/api/books.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> listJson() {
    // {"books": [{"bookNo": 1, "title": "소나기", "author": "황순원"}, {}, {}, {}, {}]}
    return new ResponseEntity<Map<String,Object>>(Map.of("books", bookService.getBookList()), HttpStatus.OK);
  }

  @GetMapping(value = "/api/books.xml", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Map<String, Object>> listXml() {
    // <List><book><bookNo>1</bookNo><title>소나기</title><author>황순원</author></book>...</List>
    return ResponseEntity.ok(Map.of("book", bookService.getBookList()));
    // => ok() 메서드는 HTTP 200 Status Code 와 함께 Response 를 생성한다.가장 일반적으로 사용되는 메소드 중 하나
  }
  
  @GetMapping(value = {"/api/books/{bookNo}", "/api/books.json/{bookNo}"})
  public ResponseEntity<Map<String, Object>> detailJson(@PathVariable(name = "bookNo") int bookNo) {
    // 응답 헤더정보에 produces = MediaType.APPLICATION_JSON_VALUE 정보 넣기
    HttpHeaders headers = new HttpHeaders();
    headers.add("Contents-Type", MediaType.APPLICATION_JSON_VALUE);
    
    // {"book":{"bookNo":1,"title":"소나기","author":"황순원"}}
    return new ResponseEntity<Map<String,Object>>(Map.of("book", bookService.getBookByNo(bookNo)), headers, HttpStatus.OK);
  }
  
  @GetMapping(value = "/api/books.xml/{bookNo}")
  public ResponseEntity<Map<String, Object>> detailXml(@PathVariable int bookNo) {  // @PathVariable 의 name 생략 가능
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
    
    // <Map><book><bookNo>1</bookNo><title>소나기</title><author>황순원</author></book></Map>
    return new ResponseEntity<>(Map.of("book", bookService.getBookByNo(bookNo)), headers, HttpStatus.OK);
  }
  
  @PostMapping(value = "/api/books", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Map<String, Object>> insertBook(@RequestBody BookDTO book) {
    // {"isSuccess": true, "inserted": "2024-06-28"}
    return ResponseEntity.ok(Map.of("isSuccess", bookService.insertBook(book) == 1
                , "inserted", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now())));
    
  }
  
  @PutMapping(value = "/api/books", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Map<String, Object>> updateBook(@RequestBody BookDTO book) {
    // {"isSuccess": true, "updated": "2024-06-28"}
    return ResponseEntity.ok(Map.of("isSuccess", bookService.updateBook(book) == 1
                , "updated", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())));
  }
  
  @DeleteMapping(value = "/api/books/{bookNo}", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable int bookNo) {
    // {"isSuccess": true, "deleted": "2024-06-28"}
    return ResponseEntity.ok(Map.of("isSuccess", bookService.deleteBook(bookNo) == 1
                , "deleted", new SimpleDateFormat("y-MM-dd").format(new Date())));
  }
  
}