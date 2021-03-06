package com.wsl.homeplus;
import java.util.*;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class HomePlusVO {
	private String productcode;
	private int codeno;
	private String name;
	private int price;
	private String unitprice="";
	private int rate=0;
	private int reviewcount=0;
	private int lookup;
	private Date lookuptime;
	private int rank;
	private String img="";
}
