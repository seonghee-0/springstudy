package com.sh.app04.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sh.app04.dto.BookDTO;
import com.sh.app04.service.BookService;

/* @RestController 
*   모든 메소드가 @ResponseBody를 가진다.   
*   (자동으로 삽입 됨)
*/

//@RestController 
public class BookController2 {

  private BookService bookService;

  @Autowired
  public BookController2(BookService bookService) {
    super();
    this.bookService = bookService;
  }
  
  /* @GetMapping    : @RequestMapping(method = RequestMethod.GET)    - 조회용
   * @PostMapping   : @RequestMapping(method = RequestMethod.POST)   - 삽입용
   * @PutMapping    : @RequestMapping(method = RequestMethod.PUT)    - 수정용 (POST 처럼 동작)
   * @DeleteMapping : @RequestMapping(method = RequestMethod.DELETE) - 삭제용 (GET 처럼 동작)  */
  

/* MediaType.APPLICATION_JSON_VALUE == "application/json" */
  @GetMapping(value = {"/api/books", "/api/books.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> listJson() {
    // {"books": [{"bookNo": 1, "title": "소나기", "author": "황순원"}, {}, {}, {}, {}]}
    return Map.of("books", bookService.getBookList());
  }

  /* MediaType.APPLICATION_XML_VALUE == "application/xml" */
  @GetMapping(value = "/api/books.xml", produces = MediaType.APPLICATION_XML_VALUE)
  public Map<String, Object> listXml() {
    // <List><book><bookNo>1</bookNo><title>소나기</title><author>황순원</author></book>...</List>
    return Map.of("book", bookService.getBookList());
  }
  
  @GetMapping(value = {"/api/books/{bookNo}", "/api/books.json/{bookNo}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> detailJson(@PathVariable(name = "bookNo") int bookNo) {
    // {"book":{"bookNo":1,"title":"소나기","author":"황순원"}}
    return Map.of("book", bookService.getBookByNo(bookNo));
  }
  
  @GetMapping(value = "/api/books.xml/{bookNo}", produces = MediaType.APPLICATION_XML_VALUE)
  public Map<String, Object> detailXml(@PathVariable int bookNo) {  // @PathVariable 의 name 생략 가능
    // <Map><book><bookNo>1</bookNo><title>소나기</title><author>황순원</author></book></Map>
    return Map.of("book", bookService.getBookByNo(bookNo));
  }
  
  @PostMapping(value = "/api/books", produces = MediaType.APPLICATION_XML_VALUE)
                                    /* @RequestBody
                                     *     요청 본문(request body)에 저장된 데이터(JSON 데이터)를 꺼내서 BookDTO book 에 저장하시오. 
                                     * 요청 본문에 저장된 JSON 데이터 예시
                                     *     {
                                     *         "bookNo": 1,
                                     *         "title": "어린왕자",
                                     *         "author": "생텍쥐베리"
                                     *     }                            */
  public Map<String, Object> insertBook(@RequestBody BookDTO book) {
    // {"isSuccess": true, "inserted": "2024-06-28"}
    return Map.of("isSuccess", bookService.insertBook(book) == 1
                , "inserted", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
  }
  
  @PutMapping(value = "/api/books", produces = MediaType.APPLICATION_XML_VALUE)
  public Map<String, Object> updateBook(@RequestBody BookDTO book) {
    // {"isSuccess": true, "updated": "2024-06-28"}
    return Map.of("isSuccess", bookService.updateBook(book) == 1
                , "updated", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
  }
  
  @DeleteMapping(value = "/api/books/{bookNo}", produces = MediaType.APPLICATION_XML_VALUE)
  public Map<String, Object> deleteBook(@PathVariable int bookNo) {
    // {"isSuccess": true, "deleted": "2024-06-28"}
    return Map.of("isSuccess", bookService.deleteBook(bookNo) == 1
                , "deleted", new SimpleDateFormat("y-MM-dd").format(new Date()));
  }
  
}