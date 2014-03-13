package com.example.financial.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Report {
	protected myDate reportDate;
	protected Date date;
	
	public Report() {
		date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.reportDate = new myDate(df.format(date));
	}
	
	public String getCurrentYearMonth(){
		DateFormat df = new SimpleDateFormat("yyyyMM");
		date = new Date();
		return df.format(date);
	}
	
	abstract public String getTitle(String year, String month);
	
	public String getTotalTile(double total){
		return "The total is $" + total;
	}
	
}
