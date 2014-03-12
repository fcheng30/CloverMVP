package com.example.financial.presenter;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.Transaction;
import com.example.financial.model.myDate;
import com.example.financial.view.IAddTransactionView;


public class AddTransactionPresenter {
	private final IAddTransactionView view;
	
	public AddTransactionPresenter(IAddTransactionView v){
		view = v;
	}
	
	public ArrayList<String> getTypeList(){
		List<String> categories = new ArrayList<String>();
		categories.add("Withdrawl");
		categories.add("Deposit");
		return (ArrayList<String>) categories;
	}
	
	public void onSubmitClick(){
		String name;
		myDate date;
		String amount;
		String type;
		String bankName;
		String resultText = "";
		name = view.getName();
		date = new myDate(view.getDate());
		amount = view.getAmount();
		type = view.getType();
		if(name.equals("") || date.equals("") ||amount.equals("")||type.equals("")){
			resultText = "Please fill out all the fields";
		}
		else{
			bankName = view.getBKDisname();
			double dbamount = Double.parseDouble(amount);
			if(view.addTrans(new Transaction(name,type,date,dbamount,bankName))){
				view.goBack();
			} else {
				resultText = "Don't have enough balance.";
			}
		}
		view.setText(resultText);
	}
	
}
