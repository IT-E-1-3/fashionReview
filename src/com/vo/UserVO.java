package com.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @file Name : MemberVO.java
 * @project name : fashion_review
 * @package name : com.vo
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 회원 가입시 입력한 정보를 저장하는 VO(아이디, 비밀번호, 이름, 이메일, 전화번호, )
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserVO {
  
   private String id,pw,name,email,phone,gender;
    private int age,height,weight;


}