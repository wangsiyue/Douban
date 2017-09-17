package com.yc.biz;

import java.util.List;

import com.yc.bean.Book;
import com.yc.bean.BookType;


public interface BookBiz {

	List<BookType> getAllBookTypes();
	
	BookType getBookType(int id);
	
	List<Book> getAllBooks();
	
	Book save(Book book);
	
	Book update(Book book);
	
	/**
	 * 打分
	 * @param book
	 */
	void makeScores(Book book);
	
	Book get(Long id);
	
	List<Book> getBookByBookType(int typeid);
}
