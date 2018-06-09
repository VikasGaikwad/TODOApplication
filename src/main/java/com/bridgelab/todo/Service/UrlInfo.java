package com.bridgelab.todo.Service;

public class UrlInfo {

	private String url_Title;
	private String url_Image;
	private String url_Domain;
	
public UrlInfo() {
	// TODO Auto-generated constructor stub
}
	public UrlInfo(String url_Title, String url_Image, String url_Domain) {
		this.url_Title = url_Title;
		this.url_Image = url_Image;
		this.url_Domain = url_Domain;
		
		}


	public String getUrl_Title() {
		return url_Title;
	}


	public void setUrl_Title(String url_Title) {
		this.url_Title = url_Title;
	}


	public String getUrl_Image() {
		return url_Image;
	}


	public void setUrl_Image(String url_Image) {
		this.url_Image = url_Image;
	}


	public String getUrl_Domain() {
		return url_Domain;
	}


	public void setUrl_Domain(String url_Domain) {
		this.url_Domain = url_Domain;
	}
	
	
}