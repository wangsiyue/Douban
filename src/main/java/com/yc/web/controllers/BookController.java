package com.yc.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yc.bean.Book;
import com.yc.bean.BookType;
import com.yc.biz.BookBiz;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;

@Controller
public class BookController {
	private static final Log logger=LogFactory.getLog(BookController.class);
	
	private BookBiz bookbiz;
	
	@Resource(name="bookBizImpl")
	public void setBookBiz(BookBiz bookbiz){
		this.bookbiz=bookbiz;
	}
	
	/**
	 * 到登录界面
	 * @return
	 */
	@RequestMapping(value="/toLogin")
	public String toLogin(){
		return "login";
	}
	
	/**
	 * 判断登录页面
	 */
	@RequestMapping(value="/adminLogin")
	public String adminLogin(@RequestParam String validateCode,@RequestParam String uname,
			@RequestParam String password, HttpSession session){
		String randCode=(String) session.getAttribute("rand");
		if(!validateCode.equals(randCode)){
			session.setAttribute("errmsg", "验证码错误");
			return "login";
		}
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("adminLogin.properties");
		Properties p=new Properties();
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(p.getProperty("uname").equals(uname) && p.getProperty("password").equals(password)){
			//登录成功
			session.setAttribute("uname", uname);
			return "redirect:/admin/listBook";
		}else{
			session.setAttribute("errmsg", "用户名或密码错误");
			return "login";
		}
		
	}
	/**
	 * 书籍列表
	 * @param model
	 * @return
	 */
	//http://localhost:8080/douban1/listBook
	@RequestMapping(value="/admin/listBook")
	public String listBook(Model model){
		//model由spring在dispathcherServlet中创建(request.setAttribute("model",model))
		//由di方式注入到这个listBook方法
		//Model model=new Model();
		//request.setAttribute("model",model);
		logger.info("listBook call....");
		List<Book> bookList=this.bookbiz.getAllBooks();
		model.addAttribute("bookList",bookList);
		return "listBookForm";//将数据转发至该页面
	}
	/**
	 * 到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/toAddBookForm")
	public String toAddBookForm(Model model){
		logger.info("toAddBookForm called...");
		//向model中存入模型对象
		List<BookType> bookTypes=this.bookbiz.getAllBookTypes();
		for(BookType bt:bookTypes){
			System.out.println(bt.getTypename());
		}
		model.addAttribute("bookTypes",bookTypes);
		model.addAttribute("book",new Book());//在addBookFom页面中用了spring标签库,所以这里就多传一个对象过去与界面上的标签绑定
		return "addBookForm";
	}
	
	
	/**
	 * 添加书籍
	 * @param book
	 * @return
	 */
	private String pdfRootName="uploadPdfs";
	
	@RequestMapping(value="/admin/addBook")
	public String addBook(HttpServletRequest request, @ModelAttribute Book book){
		logger.info("AddBookForm called...");
		String pdfs="";
		//上传
		Map<String, UploadFile> map=UploadFileUtil.uploadFile(request, book.getPdfsUrl(), pdfRootName);
		for(Entry<String, UploadFile> entry:map.entrySet()){
			UploadFile uploadFile=entry.getValue();
			pdfs+= uploadFile.getNewFileUrl()+",";
		}
		book.setPdfs(pdfs);
		int a=book.getBookType().getTypeid();
		BookType boolType=this.bookbiz.getBookType(a);
		book.setBookType(boolType);
		book.setDescription("Description");
		System.out.println(book.toString());
		this.bookbiz.save(book);
		//使用重定向而不是用转发：防止重复提交
		return "redirect:/admin/listBook";
	}
	/**
	 * 到编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/toEditBookForm/{id}")
	public String toEditBookForm(Model model,@PathVariable Long id){
		logger.info("toEditBookForm called...");
		//向model中存入模型对象
		List<BookType> bookTypes=this.bookbiz.getAllBookTypes();
		model.addAttribute("bookTypes",bookTypes);
		Book book=this.bookbiz.get(id);
		model.addAttribute("book",book);//在addBookFom页面中用了spring标签库,所以这里就多传一个对象过去与界面上的标签绑定
		return "EditBookForm";
	}
	/**
	 * 编辑书籍信息
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/admin/updateBook")
	public String updateBook(HttpServletRequest request,Book book){
		logger.info("updateBook called...");
		
		String pdfs="";
		//上传
		Map<String, UploadFile> map=UploadFileUtil.uploadFile(request, book.getPdfsUrl(), pdfRootName);
		for(Entry<String, UploadFile> entry:map.entrySet()){
			UploadFile uploadFile=entry.getValue();
			pdfs+= uploadFile.getNewFileUrl()+",";
		}
		book.setPdfs(pdfs);
		
		BookType boolType=this.bookbiz.getBookType(book.getBookType().getTypeid());
		book.setBookType(boolType);
		this.bookbiz.update(book);
		//使用重定向而不是用转发：防止重复提交
		return "redirect:/admin/listBook";
	}
	
	/**
	 * 到前端页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toStudentBookListBook")
	public String toStudentBookListForm(Model model){
		logger.info("toStudentBookListForm called...");
		//向model中存入模型对象
		List<BookType> bookTypes=this.bookbiz.getAllBookTypes();
		model.addAttribute("bookTypes",bookTypes);
		List<Book> bookList=this.bookbiz.getAllBooks();
		model.addAttribute("bookList",bookList);
		return "studentBookListForm";
	}
	/**
	 * 前端:根据类型查询书籍
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findBookByBookType/{typeid}")
	//@ResponseBody是指返回的内容（string）当成是响应协议中实体部分的内容
	public @ResponseBody String findBookByBookType(Model model,@PathVariable int typeid){
		logger.info("findBookByBookType called...");
		//向model中存入模型对象
		List<Book> bookList= this.bookbiz.getBookByBookType(typeid);
		
		Gson gson=new Gson();
		return gson.toJson(bookList);
	}
	/**
	 * 查看详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toDetailBookForm/{bookid}")
	public String toDetailBookForm(Model model,@PathVariable Long bookid){
		logger.info("toDetailBookForm called...");
		//向model中存入模型对象
		Book book=this.bookbiz.get(bookid);
		System.out.println(book.toString());
		model.addAttribute("book",book);
		return "detailBookForm";
	}
	
}
