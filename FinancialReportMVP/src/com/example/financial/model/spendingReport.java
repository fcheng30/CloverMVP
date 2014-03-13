package com.example.financial.model;

import java.util.Date;

public class spendingReport extends Report{

	
	public spendingReport() {
		super();
	}

	@Override
	public String getTitle(String year, String month) {
		if(!year.equals("")|| !month.equals("")){
			reportDate.setYear(Integer.parseInt(year));
			reportDate.setMonth(Integer.parseInt(month));
		}
		return"Spending Report for " + reportDate.getYear() + " "+ reportDate.getFormatMonth();
	}
	
	
}
