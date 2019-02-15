package com.micode.webapp.wechat.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

 
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//Include.NON_DEFAULT
/*
 * 1.实体上

@JsonInclude(Include.NON_NULL) 

//将该标记放在属性上，如果该属性为NULL则不参与序列化 
//如果放在类上边,那对这个类的全部属性起作用 
//Include.Include.ALWAYS 默认 
//Include.NON_DEFAULT 属性为默认值不序列化 
//Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化 
//Include.NON_NULL 属性为NULL 不序列化 

2.代码上
ObjectMapper mapper = new ObjectMapper();

mapper.setSerializationInclusion(Include.NON_NULL);  

//通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化 
//Include.Include.ALWAYS 默认 
//Include.NON_DEFAULT 属性为默认值不序列化 
//Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化 
 */
@JsonInclude(JsonInclude.Include.NON_NULL) 
@JacksonXmlRootElement(localName = "xml")
public class ReplyMessage {

	@JacksonXmlCData(value =true)
	private String ToUserName;
	@JacksonXmlCData(value =true) 
    private String FromUserName;
    private long CreateTime;
    @JacksonXmlCData(value =true)
    private String MsgType;
    @JacksonXmlCData(value =true)
    private String Content;
    //image
  //  @JsonProperty("Image")
    private WxImage Image;
    public ReplyMessage() {};
    public ReplyMessage(String fromUserName,String toUserName) {
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = new Date().getTime();
	}
	@JsonProperty("Voice")
    private WxVoice Voice;
    
    @JsonProperty("Video")
    private WxVideo  Video;
    
    @JsonProperty("Music")
    private WxMusic  Music;
    
 
    
    @JsonProperty
    @JacksonXmlElementWrapper(localName = "Articles")
    @JacksonXmlProperty(localName = "article")
    private List<WxItem> Articles;
    
    @JsonProperty("ArticleCount")
    private String ArticleCount;
    
    @JacksonXmlCData(value =true)
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	@JacksonXmlCData(value =true)
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	@JacksonXmlCData(value =true)
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	 @JacksonXmlCData(value =true)
	public String getContent() {
		return Content;
	}
	 
	public void setContent(String content) {
		Content = content;
	}
	@JacksonXmlCData(value =true)
	public WxImage getImage() {
		return Image;
	}
	public void setImage(WxImage image) {
		this.Image = image;
	}
	
	@JacksonXmlCData(value =true)
	public WxVoice getVoice() {
		return Voice;
	}
	public void setVoice(WxVoice voice) {
		Voice = voice;
	}
	public WxVideo getVideo() {
		return Video;
	}
	public void setVideo(WxVideo video) {
		Video = video;
	}
	public WxMusic getMusic() {
		return Music;
	}
	public void setMusic(WxMusic music) {
		Music = music;
	}
 
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public List<WxItem> getArticles() {
		return Articles;
	}
	public void setArticles(List<WxItem> articles) {
		Articles = articles;
	}
 
 

}

 