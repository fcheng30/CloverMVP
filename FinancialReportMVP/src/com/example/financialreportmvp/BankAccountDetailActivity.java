package com.example.financialreportmvp;


import com.example.financial.db.FinancialAccountSource;
import com.example.financial.model.BankAccount;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class BankAccountDetailActivity extends Activity {
	public static final String LOGTAG = "CLOVER";
			String test;
	private TextView bankname;
	private TextView accountname;
	private TextView userid;
	private TextView balance;
	private TextView mir;
	private BankAccount baccount; 
	private FinancialAccountSource datasource;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_account_detail);
		bankname = (TextView) findViewById(R.id.bankname);
		accountname = (TextView) findViewById(R.id.accountname);
		balance = (TextView) findViewById(R.id.balance);
		mir = (TextView) findViewById(R.id.mir);
		
		Bundle b = getIntent().getExtras();
		baccount = b.getParcelable("com.example.financial.model.BankAccount");
		display();
		

		datasource = new FinancialAccountSource(this);
		datasource.open();
		
		
	}
	
	private void display(){
		bankname.setText(baccount.getName());
		accountname.setText(baccount.getDisname());
		balance.setText(Double.toString(baccount.getBalance()));
		mir.setText(Double.toString(baccount.getMir()));		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank_account_detail, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_bank_account) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to delete this account?");
			builder.setCancelable(false);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					datasource.removeAccount(baccount.getUserid(), baccount.getDisname());
					finish();
				}
			});
			
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();			
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
		return super.onOptionsItemSelected(item);
	}

}
