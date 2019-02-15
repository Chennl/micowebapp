package com.micode.webapp.wechat.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WxVoice {

  public WxVoice(String mediaId) {
		MediaId = mediaId;
	}

	private String MediaId;
	@JacksonXmlCData(value =true)
	public String getMediaId() {
		return MediaId;
	}
 
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
