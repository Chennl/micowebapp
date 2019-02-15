package com.micode.webapp.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestMessage {
    public RequestMessage() {}
    
    @JsonProperty("ToUserName")
	private String toUserName;
    @JsonProperty("FromUserName")
    private String fromUserName;
    @JsonProperty("CreateTime")
    private String createTime;
    @JsonProperty("MsgType")
    private String msgType;
    //text
    @JsonProperty("Content")
    private String content;
    //image 
    @JsonProperty("PicUrl")
    private String picUrl;
    //Link
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Url")
    private String Url;
    
    //video,voice,image link
    @JsonProperty("MediaId")
    private String mediaId;
    @JsonProperty("ThumbMediaId")
    private String thumbMediaId;
    //voice
    @JsonProperty("Format")
    private String format;
    //location
    @JsonProperty("Location_X")
    private String locationX;
    @JsonProperty("Location_Y")
    private String locationY;
    @JsonProperty("Scale")
    private String scale;
    @JsonProperty("Label")
    private String label;
    
    @JsonProperty("MsgId")
    private String msgId;
    
    @JsonProperty("Recognition")
    private String Recognition;
    
    // Event
    private String Event;	//事件类型，SCAN
    private String EventKey;	//事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    private String Ticket;
    
    //Event - LOCATION
    private String Latitude;
    private String Longitude;
    private String Precision;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}
 
 
}

 