package com.example.financial.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
	private myDate reportDate;
	private Date date;
	
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
	
	public String getSpendingTitle(String year, String month){
		if(!year.equals("")|| !month.equals("")){
			reportDate.setYear(Integer.parseInt(year));
			reportDate.setMonth(Integer.parseInt(month));
		}
		return"Spending Report for " + reportDate.getYear() + " "+ reportDate.getFormatMonth();
	}
	
	public String getTotalTile(double total){
		return "The total is $" + total;
	}
	
}
