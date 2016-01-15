
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.model
 * TblPropaNews.java
 * 
 * 2015年12月19日-下午8:29:18
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.model;

import java.io.Serializable;
import java.util.List;

/**
 * @description 资讯管理
 * 
 * @time 2015年12月19日 下午8:29:18
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblPropaNews implements Serializable {

	private static final long serialVersionUID = -6043199304052416076L;
	private String id;
	/** 标题 **/
	private String title;
	/** 分类 **/
	private String classify;
	/** 发布时间 **/
	private String publish_time;
	/** 浏览量 **/
	private String views;
	/** 图片地址 **/
	private String img_url;
	/** 内容 **/
	private String content;
	/** 备注 **/
	private String remark;

	private List<String> img_urls;

	public TblPropaNews() {
	}

	public TblPropaNews(String title, String classify, String publish_time, String views, String img_url,
			String content, String remark) {
		this.title = title;
		this.classify = classify;
		this.publish_time = publish_time;
		this.views = views;
		this.img_url = img_url;
		this.content = content;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getImg_urls() {
		return img_urls;
	}

	public void setImg_urls(List<String> img_urls) {
		this.img_urls = img_urls;
	}

}
