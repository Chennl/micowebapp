package com.micode.webapp.wechat.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WxImage {
  
	public WxImage(String mediaId) {
		MediaId = mediaId;
	}
	 
	@JacksonXmlCData(value=true)
	private String MediaId;
	
	@JacksonXmlCData(value=true)
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
