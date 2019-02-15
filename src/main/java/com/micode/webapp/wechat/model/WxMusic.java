package com.micode.webapp.wechat.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WxMusic {
  
	public WxMusic() {};

	public WxMusic(String title, String description, String musicUrl, String hQMusicUrl, String thumbMediaId) {
		Title = title;
		Description = description;
		MusicUrl = musicUrl;
		HQMusicUrl = hQMusicUrl;
		ThumbMediaId = thumbMediaId;
	}

	private String Title;
	private String Description;
	private String MusicUrl;
	private String HQMusicUrl;
	private String ThumbMediaId;
	 
 

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
	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	@JacksonXmlCData(value =true)
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	@JacksonXmlCData(value =true)
	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
