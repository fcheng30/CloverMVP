package com.example.financialreportmvp;

import com.example.financial.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetailActivity extends Activity{

	private TextView name;
	private TextView userid;
	private TextView password;
	private TextView email;
	private User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_page_detail);
		name = (TextView) findViewById(R.id.name);
		userid = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		email = (TextView) findViewById(R.id.email);
		Bundle b = getIntent().getExtras();
		user = b.getParcelable("com.example.financial.model.User");
		display();
	}
	
	private void display(){
		name.setText(user.getName());
		password.setText(user.getPassword());
		userid.setText(user.getUserid());
		email.setText(user.getEmail());
	}
	
}
