package com.example.financial.presenter;

import com.example.financial.model.User;
import com.example.financial.view.ILoginView;

public class LoginPresenter {
	
	private final ILoginView logview;
	
	/**
	 * Constructor, make the login page presenter
	 * @param view the view to use
	 */
	public LoginPresenter(ILoginView lv){
		logview = lv;
	}
	
	/**
	 * Handle the login button click in the UI,
	 * Check if the input username and passwaord are valid
	 * if they are then go to the user page
	 */
	public void onLoginClick() {
		String uid = logview.getUserid();
		String pw = logview.getUserPassword();
		String text = "";
		User user = logview.findUser(uid);
		if(uid.equals("") || pw.equals("")){
			text = "Username or Password cannot be empty!";
		} else if(user==User.NULL_USER){
			text = "The userId does not exsit!";
		} else if(user.getPassword().equals(pw)){
			if(user.getUserid().equals("admin")){
				logview.goAdminPage();
			} else {
				logview.goUserPage();
			}
			
		} else {
			text = "The password is incorrect!";
		}
		logview.setResultText(text);
	}
}
