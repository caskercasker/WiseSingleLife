package com.wsl.crawling;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
/*
	PRODUCTCODE NOT NULL VARCHAR2(4000) 
	CODENO      NOT NULL NUMBER         
	NAME        NOT NULL VARCHAR2(4000) 
	PRICE       NOT NULL NUMBER         
	UNITPRICE            VARCHAR2(4000) 
	RATE                 NUMBER(2,1)    
	REVIEWCOUNT          NUMBER         
	TAGS                 VARCHAR2(4000) 
	LOOKUP      NOT NULL NUMBER         
	LOOKUPTIME  NOT NULL DATE           
	RANK        NOT NULL NUMBER         
	IMG                  VARCHAR2(4000) 
 */
@Getter
@Setter
public class EmartVO {
	
	private String productcode;  // 1.��ǰ�ڵ�
	private int codeno; // 2.�˻��� �ڵ�
	private String name; // 3.��ǰ�̸�
	private int price; // 4.�Ѱ���
	private String unitprice; // 5.100g�� ����
	private double rate; // 6.����
	private int reviewcount; // 7.�������
	private String tags; // 8.�±�
	private int lookup; // 9.��ȸ��
	private Date lookuptime; // 10.��ȸ�� ��¥
	private int rank; // 11.����
	private String img; // 12.�̹���
}
