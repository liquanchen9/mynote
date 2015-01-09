package cn.com.pengyue.vo;

import java.util.Date;

/**
 * Page entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Page implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private String title;
	private String desc;
	private String saveContentPath;
	private Date infoPushDate;
	private Date addDate;
	private Integer status;

	// Constructors

	/** default constructor */
	public Page() {
	}

	/** full constructor */
	public Page(String url, String title, String desc, String saveContentPath,
			Date infoPushDate, Date addDate, Integer status) {
		this.url = url;
		this.title = title;
		this.desc = desc;
		this.saveContentPath = saveContentPath;
		this.infoPushDate = infoPushDate;
		this.addDate = addDate;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSaveContentPath() {
		return this.saveContentPath;
	}

	public void setSaveContentPath(String saveContentPath) {
		this.saveContentPath = saveContentPath;
	}

	public Date getInfoPushDate() {
		return this.infoPushDate;
	}

	public void setInfoPushDate(Date infoPushDate) {
		this.infoPushDate = infoPushDate;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}