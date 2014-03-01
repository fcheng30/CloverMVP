package com.example.financialreportmvp;

import java.util.List;

import com.example.financial.db.FinancialTransactionSource;
import com.example.financial.model.Transaction;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

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
}
