package com.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
public class BoardVO {

	private String board_id, user_id, title, content, picture, category;
	private Date wirte_date;
	
	
	public void setCategory(String[] category) {
		      this.category="";
		      for(int i=0;i<category.length;i++) {
		         System.out.println(this.category);
		         this.category += category[i];
		         
		         
		      }

	}
}
