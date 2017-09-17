package com.yc.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.Book;
import com.yc.bean.BookRank;
import com.yc.bean.BookType;
import com.yc.biz.BookBiz;

import junit.framework.TestCase;

public class BookSpringTest extends TestCase {

	 //@Test
	public void testSpringtest() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println(ac.getBean("dataSource"));
	}

	// @Test
	public void testSpringtest1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		BookType b = bb.getBookType(3);
		System.out.println(b);
	}

	// @Test
	public void testSpringtest2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		List<BookType> list = bb.getAllBookTypes();
		for (BookType bt : list) {
			System.out.println(bt);
		}
	}

	//@Test
	public void testSpringtest3() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		List<Book> list = bb.getAllBooks();
		for (Book bt : list) {
			System.out.println(bt);
		}
	}

	// @Test
	public void testSpringtest4() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		Book b = bb.get(1L);
		System.out.println(b);
	}

	@Test
	public void testSpringtest5() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		Book b = new Book();
		b.setAuther("东野圭吾");
		b.setISBN("2304-7957");
		b.setName("白夜行");
		b.setPrice(38.5);
		b.setBookRank(new BookRank());
		BookType bt = new BookType();
		bt.setTypeid(3);
		b.setBookType(bt);
		b.setPdfs("658705120758346573475808hfkdhjkf.txt,");
		b.setDescription("好看好看真好看");
		b = bb.save(b);
		System.out.println(b);
	}

	// @Test
	public void testSpringtest6() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		Book b = new Book();
		b.setAuther("东野圭吾");
		b.setISBN("2504-7957");
		b.setName("宿命");
		b.setPrice(38.5);
		b.setBookid(22L);
		b.setBookRank(new BookRank());
		BookType bt = new BookType();
		bt.setTypeid(3);
		b.setBookType(bt);
		System.out.println(b.toString());
		b = bb.update(b);
		System.out.println(b);
	}

	//@Test
	public void testSpringtest7() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BookBiz bb = ac.getBean("bookBizImpl", BookBiz.class);
		List<Book>list=bb.getBookByBookType(1);
		for(Book b:list){
			System.out.println(b.toString());
		}
	}

}
