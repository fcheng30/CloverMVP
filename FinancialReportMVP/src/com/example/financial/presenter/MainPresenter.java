package com.example.financial.presenter;

import com.example.financial.view.IMainView;

public class MainPresenter{
	
	private final IMainView view;
	
	/**
	 * Constructor, make the welcome entry page presenter
	 * @param view the view to use
	 */
	public MainPresenter(IMainView wv){
		view = wv;
	}
	
	/**
	 * Handle the register button click in the UI,
	 * Go to register page
	 * 
	 */
	public void onRegisterClick() {
		view.goRegister();
	}
	
	/**
	 * Handle the login button click in the UI,
	 * Go to Login page
	 */
	public void onLoginClick() {
		view.goLogin();
	}
}