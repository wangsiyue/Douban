package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long bookid;
	private String ISBN;
	private String name;
	private String author;
	private Double price;
	private Integer typeid;
	private String pdfs;//对应数据库
	private List<MultipartFile> pdfsUrl;//对应界面
	private String description;
	
	private BookType bookType;
	private BookRank bookRank;
	
	public List<String> getPdfsStringList(){
		List<String> list=new ArrayList<String>();
		if(pdfs!=null && pdfs.length()>0){
			String[] strs=pdfs.split(",");
			for(String s:strs){
				list.add(s);
			}
		}
		return list;
	}
	
	public List<MultipartFile> getPdfsUrl() {
		return pdfsUrl;
	}
	public void setPdfsUrl(List<MultipartFile> pdfsUrl) {
		this.pdfsUrl = pdfsUrl;
	}
	public String getPdfs() {
		return pdfs;
	}
	public void setPdfs(String pdfs) {
		this.pdfs = pdfs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getBookid() {
		return bookid;
	}
	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuther() {
		return author;
	}
	public void setAuther(String auther) {
		this.author = auther;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public BookRank getBookRank() {
		return bookRank;
	}
	public void setBookRank(BookRank bookRank) {
		this.bookRank = bookRank;
	}

	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", ISBN=" + ISBN + ", name=" + name + ", auther=" + author + ", price="
				+ price + ", typeid=" + typeid + ", pdfs=" + pdfs + ", pdfsUrl=" + pdfsUrl + ", description="
				+ description + ", bookType=" + bookType + ", bookRank=" + bookRank + "]";
	}
	
	
	
}
