package com.wsl.product_detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MartAllDataDAO {

	// martAllData
	@Autowired
	private MartAllDataMapper MAData_mapper;
	
	public MartAllDataVO SearchDetail(String productcode){
		String Check = productcode.substring(0,2);
		MartAllDataVO vo = new MartAllDataVO();
		System.out.println(Check);
		if(Check.equals("em")){
			vo= MAData_mapper.EmartDetail(productcode);
		}else if(Check.equals("hp")){
			vo= MAData_mapper.HomeplusDetail(productcode);
		}else if(Check.equals("LM")){
			vo=MAData_mapper.LotteMartDetail(productcode);
		}else{
			vo=MAData_mapper.CoupangDetail(productcode);
		}
		return vo;
	}
	public int ProductAllCount(String productcode){
		return MAData_mapper.ProductAllCount(productcode);
	}
	public Detail_SearchKeyVO productKeyowrd(String productcode){
		return MAData_mapper.ProductKeyword(productcode);
	}
	public List<Integer> rno(String productcode){
		return MAData_mapper.RnoList(productcode);
	}
	public int RecipeCount(String productcode){
		return MAData_mapper.RecipeCount(productcode);
	}
	public Detail_RecipeVO RecipeList_detail(int no){
		return MAData_mapper.RecipeList_detail(no);
	}
	// 마트 별 출력
}
