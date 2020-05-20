package com.wsl.emart;

import java.net.URLEncoder;
import java.util.*;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EmartManager3 {
	
	@Autowired
	private EmartDAO dao;
	
	@RequestMapping("test.do")
	public void emartData() {
		
		List<SearchKeywordVO> list=dao.searchKeywordData();
		System.out.println(list.size());
		
		
		try {
			int totalcount=1;
			for(int i=0;i<list.size();i++) 
			{	
				try {
					System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
					System.out.println((i+1)+"."+list.get(i).getKeyword());
					System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
					
					Document doc=Jsoup.connect("http://emart.ssg.com/search.ssg?target=all&query="+list.get(i).getKeyword()+"&src_area=late&page=1&count=40").get();
//					Elements link=doc.select("div#divProductImg div.cunit_info");
					Elements link=doc.select("div#divProductImg li.cunit_t232");
					
					int rank=1;
					for(int j=0;j<link.size();j++) 
					{
						try {
							
							Element elem=link.get(j);
							
							if(!elem.select("div.cunit_prod div.tt_adinfo_n a").text().equals("����"))
							{
								System.out.println("totalcount="+totalcount);
								
								// productcode ��ǰ�ڵ�
								String productCode="";
								try {
									productCode=elem.select("div.cunit_md a").attr("href");
									productCode="em"+productCode.substring(productCode.indexOf("itemId=")+7,productCode.indexOf("&siteNo"));
									System.out.println("��ǰ�ڵ�: "+productCode);
								} catch(Exception ex) {
									productCode="";
								}
								
								
								// codeno �˻��� �ڵ�
								int codeno=0;
								try {
									codeno=list.get(i).getCodeno();
									System.out.println("�˻����ڵ�: "+codeno);
								} catch(Exception ex) {
									codeno=0;
								}
								
								
								// name ��ǰ�̸�
								String name="";
								try {
									name=elem.select("div.cunit_md a.clickable em.tx_ko").text();
									System.out.println("��ǰ�̸�: "+name);
								} catch(Exception ex) {
									name="";
								}
								
								
								// price �� ����
								int price2=0;
								try {
									String price=elem.select("div.cunit_price div.opt_price em.ssg_price").text();
									String strPrice="";
									StringTokenizer st=new StringTokenizer(price,",");
									while(st.hasMoreTokens()) {
										strPrice+=st.nextToken();
									}
									price2=Integer.parseInt(strPrice);
									System.out.println("����: "+price2);
								} catch(Exception ex) {
									price2=0;
								}
								
								
								// unitprice 100g�� ����
								String unitPrice="";
								try {
									unitPrice=elem.select("div.cunit_prw div.unit").text();
									unitPrice=unitPrice.substring(unitPrice.indexOf("(")+1,unitPrice.lastIndexOf(")"));
									System.out.println("������ ����: "+unitPrice);
								} catch(Exception ex) {
									unitPrice="";
								}
								
								
								// rate ����
								double rate2=0.0;
								try {
									String rate=elem.select("div.cunit_app span.blind").text();
									rate=rate.substring(2,rate.lastIndexOf("��"));
									rate2=Double.parseDouble(rate);
									System.out.println("����: "+rate2);
								} catch(Exception ex) {
									rate2=0.0;
								}
								
								
								// reviewcount �������
								int reviewCount2=0;
								try {
									String reviewCount=elem.select("div.cunit_app span.rate_tx em").text();
									String strReviewCount="";
									StringTokenizer st2=new StringTokenizer(reviewCount,",");
									while(st2.hasMoreTokens()) {
										strReviewCount+=st2.nextToken();
									}
									reviewCount2=Integer.parseInt(strReviewCount);
									System.out.println("���� ��: "+reviewCount2);
								} catch(Exception ex) {
									reviewCount2=0;
								}
								
								
								// tags �±�
								String tags="";
								try {
									tags=elem.select("div.cunit_tag a").text();
									System.out.println("�±�: "+tags);
								} catch(Exception ex) {
									tags="";
								}
								
								
								// look ��ȸ��
								// loopuptime ��ȸ�� ��¥
								
								
								// rank ����
								System.out.println("���� : "+rank);
								
								
								// img �̹���
								String image="";
								try {
									image=elem.select("div.cunit_prod div.thmb img.i1").attr("src");
									System.out.println("�̹���: "+image);
								} catch(Exception ex) {
									image="";
								}
								
								
								
								
								System.out.println("-----------------------------------------------");
								
								
								// DB�� �ֱ�
								EmartVO vo=new EmartVO();
								vo.setProductcode(productCode);
								vo.setCodeno(codeno);
								vo.setName(name);
								vo.setPrice(price2);
								vo.setUnitprice(unitPrice);
								vo.setRate(rate2);
								vo.setReviewcount(reviewCount2);
								vo.setTags(tags);
//								vo.setLookup(0);
//								vo.setLookuptime();
								vo.setRank(rank);
								vo.setImg(image);
								dao.insertEmartData(vo);
								
								rank++;
								totalcount++;					
								
								Thread.sleep(1500);
							}
							
						} catch(Exception ex) {
							ex.printStackTrace();
							Thread.sleep(1000);
						}
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					Thread.sleep(1000);
				}
				
				if(i==(list.size()-1)) {
					System.exit(0);
				}
					
			}
			
		} catch(Exception ex) {
			System.out.println("EmartManager : ");
			ex.printStackTrace();
		}
		
	}

}
