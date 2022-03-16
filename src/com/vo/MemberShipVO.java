package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @file Name : MemberShipVO.java
 * @project name : fashion_review
 * @package name : com.vo
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 회원들에게 부여되는 등급, 포인트, 게시글 수, 댓글 수를 저장하는 VO 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MemberShipVO {
   private String id, grade;
   private int post_count, point, reply_count;
   

}