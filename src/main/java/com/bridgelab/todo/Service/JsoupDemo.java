package com.bridgelab.todo.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupDemo {
	public UrlInfo getUrlData(String url) throws URISyntaxException, IOException {

		String url_Title = null;
		String url_Image = null;
		String url_Domain = null;

		URI uri = new URI(url);

		url_Domain = uri.getHost();

		Document document = Jsoup.connect(url).get();

		Elements urlTitle = document.select("meta[property=og:title]");

		if(urlTitle != null) {

			url_Title = urlTitle.attr("content");
			System.out.println("URL title is: "+url_Title);

			if(url_Title == null) {
				url_Title = document.title();
				System.out.println("URL title is: "+url_Title);


			}
		}

		Elements urlImage = document.select("meta[property=og:image");

		if(urlImage != null) {

			url_Image = urlImage.attr("content");	
			System.out.println(document.select("meta[property=og:image]").attr("content"));
		}


		return new UrlInfo(url_Title, url_Image, url_Domain);

	}

}