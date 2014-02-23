package com.example.financialreportmvp;

import com.example.financialreportmvp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
				} catch (Exception e){
					e.printStackTrace();
				} finally{
					Intent openMainActivity = new Intent("com.example.financialreportmvp.MainActivity");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	

}
