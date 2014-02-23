package com.example.financialreportmvp;

import com.example.financial.model.User;
import com.example.financialreportmvp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class UserPageActivity extends Activity{

	TextView userName;
	User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_page);
		userName = (TextView) findViewById(R.id.userPageName);
		
		Bundle b = getIntent().getExtras();
		user = b.getParcelable("com.example.financial.model.User");
		
		userName.setText("Hello, "+ user.getName());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.user_page_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.user_page_setting:
	        	Intent intent = new Intent(this, SettingPageActivity.class);
	        	startActivity(intent);
	        	break;
	        default:
	        	break;
	    }
	            return super.onOptionsItemSelected(item);
	}
	
	
	
}
