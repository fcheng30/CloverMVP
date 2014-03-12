package com.example.financial.presenter;

import com.example.financial.view.IReportView;

public class ReportPresenter {

	private final IReportView view;
	
	public ReportPresenter(IReportView view) {
		this.view = view;
	}
	
	public void onClickView(){
		view.refresh();
	}

}
