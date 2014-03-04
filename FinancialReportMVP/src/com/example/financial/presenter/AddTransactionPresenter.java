package com.example.financial.presenter;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.Transaction;
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
		String date;
		String amount;
		String type;
		String bankName;
		String resultText = "";
		name = view.getName();
		date = view.getDate();
		amount = view.getAmount();
		type = view.getType();
		if(name.equals("") || date.equals("") ||amount.equals("")||type.equals("")){
			resultText = "Please fill out all the fields";
		}
		else{
			bankName = view.getBKDisname();
			double dbamount = Double.parseDouble(amount);
			view.addTrans(new Transaction(name,type,date,dbamount,bankName));
			view.goBack();
		}
		view.setText(resultText);
	}
	
}
