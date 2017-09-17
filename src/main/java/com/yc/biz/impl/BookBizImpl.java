package com.yc.biz.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.yc.bean.Book;
import com.yc.bean.BookRank;
import com.yc.bean.BookType;
import com.yc.biz.BookBiz;
import com.yc.dao.BaseDao;

@Repository
public class BookBizImpl implements BookBiz {

	private BaseDao dao;
	
	@Resource(name = "baseDaoMybatisImpl")
	public void setBaseDao(BaseDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<BookType> getAllBookTypes() {
		List<BookType> list=this.dao.findAll(new BookType(), "getAllBookTypes");
		return list;
	}

	@Override
	public BookType getBookType(int id) {
		BookType type=new BookType();
		type.setTypeid(id);
		List<BookType> list=this.dao.findAll(type, "getBook");
		return list!=null && list.size()>0?list.get(0):null;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> list=this.dao.findAll(new Book(), "getAllBooks");
		return list;
	}

	@Override
	public Book save(Book book) {
		this.dao.add(book, "saveBook");
		return book;
	}
	

	@Override
	public void makeScores(Book book) {
		BookRank br=new BookRank();
		br.setBid(book.getBookid());
		br.setIp(book.getBookRank().getIp());
		br.setScores(book.getBookRank().getScores());
		this.dao.add(br, "makeScore");
	}

	@Override
	public Book update(Book book) {
		this.dao.update(book, "updateBook");
		return book;
	}

	@Override
	public Book get(Long id) {
		Book c=new Book();
		c.setBookid(id);
		List<Book> list=this.dao.findAll(c, "getBook");
		return list!=null && list.size()>0?list.get(0):null;
	}

	@Override
	public List<Book> getBookByBookType(int typeid) {
		if(typeid<=0){
			return getAllBooks();
		}
		BookType bookType=new BookType();
		bookType.setTypeid(typeid);
		Book book=new Book();
		book.setBookType(bookType);
		List<Book> list=this.dao.findAll(book, "getBookByBookType");
		return list;
	}

	

}
