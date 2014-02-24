package com.example.financial.presenter;

import com.example.financial.model.User;
import com.example.financial.view.IRegisterView;

public class RegisterPresenter {
	private final IRegisterView regview;
	
	/**
	 * Constructor, make the login page presenter
	 * @param rv the view to use
	 */
	public RegisterPresenter(IRegisterView rv){
		regview = rv;
	}
	
	/**
	 * Handle the sign up button click in the UI,
	 * Check if the all fields are filled out
	 * if they are then add the new user into database
	 * then redirect to the user page
	 */
	public void onSignUpClick() {
		String uid = regview.getUsername();
		String pw = regview.getPassword();
		String na = regview.getName();
		String em = regview.getEmail();
		String text = "";
		if(uid.equals("") || pw.equals("") || na.equals("")|| em.equals("")){
			text = "Please fill out all fields!";
		} else if(regview.findUser(uid)!=User.NULL_USER){
			text = "The username already exsit, please try another one!";
		} else {
			regview.addUser(new User(uid,pw,na,em));
			regview.goLoginPage();
		}
		regview.setRegisterText(text);
	}
}
