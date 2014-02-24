package com.example.financialreportmvp;

import com.example.financial.db.FinancialUserSource;
import com.example.financial.model.User;
import com.example.financial.presenter.LoginPresenter;
import com.example.financial.view.ILoginView;
import com.example.financialreportmvp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements ILoginView{

	private LoginPresenter logPresenter;
	private EditText inputUn;
	private EditText inputPw;
	private TextView resultText;
	private FinancialUserSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		inputUn = (EditText) findViewById(R.id.usernameLog);
		inputPw = (EditText) findViewById(R.id.passwordLog);
		resultText = (TextView) findViewById(R.id.loginText);
		datasource = new FinancialUserSource(this);
		datasource.open();
		//datasource.update(datasource);
		logPresenter = new LoginPresenter(this);
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
	}
	
	public void onLoginCheckButtonClick(View v){
		logPresenter.onLoginClick();
	}
	

	@Override
	public String getUserid() {
		return inputUn.getText().toString();
	}

	@Override
	public String getUserPassword() {
		return inputPw.getText().toString();
	}

	@Override
	public void setResultText(String text) {
		resultText.setText(text);
	}

	@Override
	public void goUserPage() {
		Intent intent = new Intent(this, UserPageActivity.class);
		User user = findUser(getUserid());
		intent.putExtra("com.example.financial.model.User", user);
		startActivity(intent);
	}

	@Override
	public void goAdminPage() {
		Intent intent = new Intent(this, AdminPageActivity.class);
		startActivity(intent);
	}

	@Override
	public User findUser(String uid) {
		return datasource.findUser(uid);
	}
}
