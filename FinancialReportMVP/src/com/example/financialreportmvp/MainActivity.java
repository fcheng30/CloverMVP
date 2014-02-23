package com.example.financialreportmvp;

import com.example.financial.presenter.MainPresenter;
import com.example.financial.view.IMainView;
import com.example.financialreportmvp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements IMainView{
	public static final String LOGTAG = "CLOVER";
	
	private MainPresenter mainPresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainPresenter = new MainPresenter(this);
	}
	
	/**
	 *  Method defined in activity_main.xml button1
	 * @param v
	 */
	public void onRegisterButtonClick(View v){
		mainPresenter.onRegisterClick();
	}

	/**
	 *  Method defined in activity_main.xml button2
	 * @param v
	 */
	public void onLoginButtonClick(View v){
		mainPresenter.onLoginClick();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void goRegister() {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
		
	}

	@Override
	public void goLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

}
