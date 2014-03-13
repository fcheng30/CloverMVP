package com.example.financialreportmvp;

import java.util.List;

import com.example.financial.db.FinancialAccountSource;
import com.example.financial.model.BankAccount;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TransactionActivity extends ListActivity{
	private String userid;
	private FinancialAccountSource datasource;
	private List<BankAccount> accounts;
	Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_page);
		b = getIntent().getExtras();
		userid = b.getString("userid");
		datasource = new FinancialAccountSource(this);
		datasource.open();
		display();
	}

	public void display(){
		Log.i(MainActivity.LOGTAG, "userid: " + userid);
		accounts = datasource.getAccountList(userid);
		ArrayAdapter<BankAccount> adapter = new ArrayAdapter<BankAccount>(this, R.layout.list_view1, accounts);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Account List");
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
		display();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		BankAccount baccount = accounts.get(position);
		Intent intent = new Intent(this, AccountTransactionActivity.class);
		intent.putExtra("bankname", baccount.getDisname());
		intent.putExtra("userid", userid);
		Log.i(MainActivity.LOGTAG, "Pass in bankname and userid");
		startActivity(intent);
	}
	
}
