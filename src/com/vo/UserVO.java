package com.vo;
//User��ü ����� ��

public class UserVO {
  
   private String id,pw,name,email,phone,address,gender;
    private int age,height,weight;
    

	public UserVO(String id, String pw, String name, String email, String phone,String gender, int age,
			int height, int weight) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
	
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}


	

	public UserVO() {
		super();
	}
  

    public UserVO(String id, String pw) {
        this.id=id;
        this.pw=pw;
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phone=" + phone
				+", gender=" + gender + ", age=" + age + ", height=" + height + ", weight="
				+ weight + "]";
	}




}