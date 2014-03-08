package com.example.financialreportmvp;

import java.util.List;

import com.example.financial.db.FinancialReportGenerator;
import com.example.financial.model.Report;
import com.example.financial.model.Transaction;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SpendingReportActivity extends ListActivity{
	
	private FinancialReportGenerator datasource;
	private List<Transaction> spendingList;
	private TextView text;
	private Report rp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		datasource = new FinancialReportGenerator(this);
		
		text = (TextView) findViewById(R.id.reportText);
		rp = new Report();
		
		text.setText(rp.getSpendingReportTitle());
		
	    datasource.open();
		display();
		
	}
	
	public void display(){
		spendingList = datasource.getSpendingList(rp.getYearMonth());
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, R.layout.list_view1, spendingList);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Spending List");
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
		Transaction tran = spendingList.get(position);
		intent.putExtra("com.example.financial.model.Transaction", tran);
		Log.i(MainActivity.LOGTAG, "Open Transaction Detail");
		startActivity(intent);
	}

}
