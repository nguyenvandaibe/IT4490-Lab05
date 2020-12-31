package com.oms.serverapi;

import java.util.ArrayList;
import java.util.Map;

import com.oms.bean.Book;

public interface IBookApi {
	public ArrayList<Book> getBooks(Map<String, String> queryParams);
	
	public Book updateBook(Book book);
}
