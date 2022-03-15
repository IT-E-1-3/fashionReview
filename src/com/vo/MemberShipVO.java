package com.vo;

public class MemberShipVO {
	private String id, grade;
	private int post_count, point;
	public MemberShipVO(String id, String grade, int post_count, int point) {
		super();
		this.id = id;
		this.grade = grade;
		this.post_count = post_count;
		this.point = point;
	}
	
	public MemberShipVO() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getPost_count() {
		return post_count;
	}
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "MemberShipVO [id=" + id + ", grade=" + grade + ", post_count=" + post_count + ", point=" + point + "]";
	}
	
	

}
