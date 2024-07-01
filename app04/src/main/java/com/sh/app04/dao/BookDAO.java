package com.sh.app04.dao;

import java.util.List;

import com.sh.app04.dto.BookDTO;

public interface BookDAO {
  List<BookDTO> getBookList();
  BookDTO getBookByNo(int bookNo);
  int insertBook(BookDTO book);
  int updateBook(BookDTO book);
  int deleteBook(int bookNo);
}
