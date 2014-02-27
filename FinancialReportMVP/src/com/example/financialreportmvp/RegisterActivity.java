package com.example.financialreportmvp;

import com.example.financial.db.FinancialUserSource;
import com.example.financial.model.User;
import com.example.financial.presenter.RegisterPresenter;
import com.example.financial.view.IRegisterView;
import com.example.financialreportmvp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity implements IRegisterView{
	
	private RegisterPresenter rp;
	private EditText username;
	private EditText password;
	private EditText email;
	private EditText name;
	private TextView resultText;
	private FinancialUserSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		rp = new RegisterPresenter(this);
		username = (EditText) findViewById(R.id.regUserid);
		password = (EditText) findViewById(R.id.regPassword);
		name = (EditText) findViewById(R.id.regName);
		email = (EditText) findViewById(R.id.regEmail);
		resultText = (TextView) findViewById(R.id.regText);
		datasource = new FinancialUserSource(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
		finish();
	}
	
	public void onSignUpButtonClick(View v){
		rp.onSignUpClick();
	}
	
	@Override
	public String getUsername() {
		return username.getText().toString();
	}
	@Override
	public String getPassword() {
		return password.getText().toString();
	}
	@Override
	public String getName() {
		return name.getText().toString();
	}
	
	@Override
	public String getEmail() {
		return email.getText().toString();
	}
	
	@Override
	public void setRegisterText(String text) {
		resultText.setText(text);
	}
	@Override
	public void goLoginPage() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	@Override
	public void addUser(User user) {
		datasource.addUser(user);		
	}

	@Override
	public User findUser(String uid) {
		return datasource.findUser(uid);
	}



	
}
