package com.sist.web.vo;

import lombok.Data;

/*
 *  NO                                        NOT NULL NUMBER
 TITLE                                     NOT NULL VARCHAR2(4000)
 POSTER                                    NOT NULL VARCHAR2(260)
 CHEF                                      NOT NULL VARCHAR2(500)
 LINK                                               VARCHAR2(100)
 HIT                                                NUMBER
 LIKECOUNT                                          NUMBER
 JJIMECOUNT                                         NUMBER
 REPLYCOUNT                                         NUMBER
 */
@Data
public class RecipeVO {
	private int no,hit;
	private String title,poster,chef;
}
