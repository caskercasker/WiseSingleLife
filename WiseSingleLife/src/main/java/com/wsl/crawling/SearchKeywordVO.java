package com.wsl.crawling;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchKeywordVO {
	private int codeno; // �˻��� �ڵ�
	private String keyword; // �˻��� �̸�
	private int count; // ��ȸ��
	private Date datetime; // ��ȸ��¥
}
