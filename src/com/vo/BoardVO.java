package com.vo;

/**
 * @file Name : BoardVO.java
 * @project name : fashion_review
 * @package name : com.vo
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 게시글에 대한 정보를 저장하는 VO (게시글 id, 작성자 id, 제목, 내용, 첨부 사진, 작성 일자, 카테고리) 
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BoardVO {

   private String board_id, user_id, title, content, picture, category;
   private Date write_date;
  
   public void setCategoryList(String[] category) {
        this.category="";
        for(int i=0;i<category.length; i++) {
           if(i!=category.length-1) {
              this.category += (category[i]+",");
           }else {
              this.category += (category[i]);
           }         
        }

   }
   

  
   
}