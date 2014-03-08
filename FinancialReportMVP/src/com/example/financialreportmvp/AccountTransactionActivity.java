package com.example.financialreportmvp;

import java.util.List;

import com.example.financial.db.FinancialTransactionSource;
import com.example.financial.model.Transaction;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AccountTransactionActivity extends ListActivity{

	private FinancialTransactionSource datasource;
	private List<Transaction> transactions;
	private String bankname;
	Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank_transaction);
		b = getIntent().getExtras();
		bankname = b.getString("bankname");
		datasource = new FinancialTransactionSource(this);
		datasource.open();
		display();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.transaction_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void display(){
		transactions = datasource.getTransactionList(bankname);
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, R.layout.list_view1, transactions);
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
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, TransactionDetailActivity.class);
		Transaction tran = transactions.get(position);
		intent.putExtra("com.example.financial.model.Transaction", tran);
		intent.putExtra("com.example.financial.model.myDate", tran.getDate());
		Log.i(MainActivity.LOGTAG, "Pass in account transaction");
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.add_new_account:
	        	Intent intent = new Intent(this, AddTransactionActivity.class);
	        	intent.putExtra("bankname", bankname);
	        	Log.i(MainActivity.LOGTAG, "Pass in userid");
	        	startActivity(intent);
	        	break;
	        default:
	        	break;
	    }
	            return super.onOptionsItemSelected(item);
	}
}
