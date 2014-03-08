package com.example.financial.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
	myDate currentDate;
	Date date;
	
	public Report() {
		date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.currentDate = new myDate(df.format(date));;
	}
	
	public String getYearMonth(){
		DateFormat df = new SimpleDateFormat("yyyyMM");
		date = new Date();
		return df.format(date);
	}
	
	public String getSpendingReportTitle(){
		return "Spending Report for " + currentDate.getYear() + " "+ currentDate.getFormatMonth() + " 1 - 30";
	}
	
}
