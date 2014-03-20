package com.example.financial.model;

public class incomeReport extends Report {

	
	public incomeReport() {
		super();
	}

	@Override
	public String getTitle(String year, String month) {
		if(!year.equals("")|| !month.equals("")){
			reportDate.setYear(Integer.parseInt(year));
			reportDate.setMonth(Integer.parseInt(month));
		}
		return"Income Transactions for " + reportDate.getYear() + " "+ reportDate.getFormatMonth();
	}
	
}
