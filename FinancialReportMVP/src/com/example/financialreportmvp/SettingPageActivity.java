package com.example.financialreportmvp;

import com.example.financial.presenter.SettingPresenter;
import com.example.financial.view.ISettingView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SettingPageActivity extends Activity implements ISettingView{

	private SettingPresenter setpresenter;
	private String userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_page);
		Bundle b = getIntent().getExtras();
		userid = b.getString("userid");
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
		Intent intent = new Intent(this, AccountPageActivity.class);
		intent.putExtra("userid", userid);
		Log.i(MainActivity.LOGTAG, "Pass in userid");
		startActivity(intent);
		
	}

	@Override
	public void logout() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}
