package com.example.financialreportmvp;

import com.example.financial.presenter.SettingPresenter;
import com.example.financial.view.ISettingView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingPageActivity extends Activity implements ISettingView{

	private SettingPresenter setpresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_page);
		
		setpresenter = new SettingPresenter(this);
	}

	public void onAccountClick(View v) {
		setpresenter.onAccountClick();
	}
	
	public void onLogoutClick(View v) {
		setpresenter.onLogoutClick();
	}

	@Override
	public void goToAccount() {
		Intent intent = new Intent();
		
	}

	@Override
	public void logout() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}
