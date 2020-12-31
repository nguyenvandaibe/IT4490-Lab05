package com.oms.serverapi;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oms.bean.Book;

public class BookApi implements IBookApi {
	public static final String PATH = "http://localhost:8080/";

	private Client client;

	public BookApi() {
		client = ClientBuilder.newClient();
	}

	@Override
	public ArrayList<Book> getBooks(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(PATH).path("books");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Book> res = response.readEntity(new GenericType<ArrayList<Book>>() {
		});
		System.out.println(res);
		return res;

	}

	@Override
	public Book updateBook(Book book) {
		WebTarget webTarget = client.target(PATH).path("books").path(book.getId());

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(book, MediaType.APPLICATION_JSON));

		Book res = response.readEntity(Book.class);
		return res;
	}

}
