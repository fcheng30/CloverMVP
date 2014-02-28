package com.example.financial.presenter;


import com.example.financial.db.FinancialAccountSource;
import com.example.financial.model.BankAccount;
import com.example.financial.view.INewAccountView;

public class NewAccountPresenter {
	private final INewAccountView view;
	
	public NewAccountPresenter(INewAccountView v){
		view = v;
	}
	
	/**
	 * Handle the submit button click in the UI,
	 * Check if the all fields are filled out
	 * if they are then add the new account into database
	 * then redirect back to account page
	 */
	public void onSubmitClick(){
		String acname = view.getAcName();
		String disname = view.getDisName();
		String uid = view.getUserid();
		String balance = view.getBalance();
		String mir = view.getMIR();
		String text = "";
		if(acname.equals("") || disname.equals("") || balance.equals("") || mir.equals("")){
			text = "Please fill out the names";
		} else if(view.checkAccount()){
			text = "This display name already exsit, please try another one!";
		} else {
			double dbbalance = Double.parseDouble(balance);
			double dbmir = Double.parseDouble(mir);
			view.addAccount(new BankAccount(acname,disname,dbbalance,dbmir,uid));
			view.goAccountPage();
		}
		view.setText(text);
		
	}
}
