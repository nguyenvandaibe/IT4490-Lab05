package com.oms.serverapi;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oms.bean.Media;

public class MediaApi implements IMediaApi {
	public static final String PATH = "http://localhost:8080/";

	private Client client;
	
	private static MediaApi instance;

	private MediaApi() {
		client = ClientBuilder.newClient();
	}
	
	public static MediaApi getInstance() {
		if (instance == null) {
			instance = new MediaApi();
		}
		return instance;
	}

	public ArrayList<Media> getAllMedias() {
		WebTarget webTarget = client.target(PATH).path("medias");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Media> res = response.readEntity(new GenericType<ArrayList<Media>>() {
		});
		System.out.println(res);
		return res;
	}

}