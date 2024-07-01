package com.sh.app04.service;

import java.util.List;

import com.sh.app04.dto.BookDTO;

public interface BookService {
  List<BookDTO> getBookList();
  BookDTO getBookByNo(int bookNo);
  int insertBook(BookDTO book);
  int updateBook(BookDTO book);
  int deleteBook(int bookNo);
}