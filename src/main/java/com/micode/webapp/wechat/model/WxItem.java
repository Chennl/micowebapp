package com.micode.webapp.wechat.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
@JacksonXmlRootElement(localName = "item")
public class WxItem {
 
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
    public WxItem() {};
    /**
     * @param title
     * @param description
     * @param picUrl
     * @param url
     */ 
    public WxItem(String title, String description, String picUrl, String url) {
		Title = title;
		Description = description;
		PicUrl = picUrl;
		Url = url;
	}
	@JacksonXmlCData(value =true)
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	@JacksonXmlCData(value =true)
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JacksonXmlCData(value =true)
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	@JacksonXmlCData(value =true)
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
 
 
}
