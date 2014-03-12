package com.example.financial.view;

import java.util.ArrayList;

public interface IReportView {
	
	 ArrayList<String> getYearList();
	 ArrayList<String> getMonthList();
	 
	 String getYearMonth();
	 void refresh();
	 
}
