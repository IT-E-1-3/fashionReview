package com.vo;

import java.util.Date;

public class BoardVO {

	private String board_id, user_id, title, content, picture, category;
	private Date wirte_date;
	
	public BoardVO() {
		super();
	}

	public BoardVO(String board_id, String user_id, String title, String content, String picture, String category,
			Date wirte_date) {
		super();
		this.board_id = board_id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.category = category;
		this.wirte_date = wirte_date;
	}

	public String getCategory() {
		return category;
	}

	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getWirte_date() {
		return wirte_date;
	}
	public void setWirte_date(Date wirte_date) {
		this.wirte_date = wirte_date;
	}
	
	public void setCategory(String[] category) {
        this.category="";
        for(int i=0;i<category.length; i++) {
           this.category += (category[i]+",");
           System.out.println(this.category);
           
           
        }

}

	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", user_id=" + user_id + ", title=" + title + ", content=" + content
				+ ", picture=" + picture + ", category=" + category + ", wirte_date=" + wirte_date + "]";
	}
	
}
