package com.micode.webapp.wechat.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WxVideo {
  
	public WxVideo() {}
	public WxVideo(String mediaId, String title, String description) {
 
		MediaId = mediaId;
		Title = title;
		Description = description;
	}

	private String MediaId;
	private String Title;
	private String Description;
	  
	@JacksonXmlCData(value =true)
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
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

}
