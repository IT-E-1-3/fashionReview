package com.vo;

/**
 * @file Name : ReplyVO.java
 * @project name : fasion_review
 * @package name : com.vo
 * @작성일 : 2022.03.14
 * @작성자 : 김정휴, 심다혜
 * @Method 설명 : 게시글에 대한 댓글 정보를 저장하는 VO(답장 id, 게시글 id, 작성자 id, 내용, 작성 날짜)
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
public class ReplyVO {
	
	private String user_id, board_id, content;
	private Date reply_date;

}
