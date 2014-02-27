package com.example.financialreportmvp;

import com.example.financial.db.FinancialUserSource;
import com.example.financial.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDetailActivity extends Activity{

	private TextView name;
	private TextView userid;
	private TextView password;
	private TextView email;
	private User user;
	private FinancialUserSource datasource;
	AlertDialog.Builder builder;
	AlertDialog dialog;
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
		
		datasource = new FinancialUserSource(this);
		datasource.open();
	}
	
	private void display(){
		name.setText(user.getName());
		password.setText(user.getPassword());
		userid.setText(user.getUserid());
		email.setText(user.getEmail());
	}
	
	public void onResetPWClick(View v) {
		builder.setMessage(R.string.reset_pw);
		builder.setTitle("Password Reset!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				datasource.resetPW(user.getUserid());
				finish();
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		dialog = builder.create();
		dialog.show();
	}

	public void onDeleteUserClick(View v) {
		builder.setMessage(R.string.remove_user);
		builder.setTitle("Delete User!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				datasource.removeUser(user.getUserid());
				finish();
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		dialog = builder.create();
		dialog.show();
	}
}
