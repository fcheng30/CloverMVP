package com.example.financialreportmvp;

import com.example.financial.db.FinancialTransactionSource;
import com.example.financial.model.Transaction;
import com.example.financial.model.myDate;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TransactionDetailActivity extends Activity {
	
	private TextView tran_bkname;
	private TextView tran_name;
	private TextView tran_type;
	private TextView tran_status;
	private TextView tran_date;
	private TextView tran_amount;
	private Transaction tran;
	private myDate date;
	private FinancialTransactionSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_detail_activity);
		
		tran_bkname = (TextView) findViewById(R.id.tran_bkname);
		tran_name = (TextView) findViewById(R.id.tran_name);
		tran_type = (TextView) findViewById(R.id.tran_type);
		tran_status = (TextView) findViewById(R.id.tran_status);
		tran_date = (TextView) findViewById(R.id.tran_date);
		tran_amount = (TextView) findViewById(R.id.tran_amount);
		
		Bundle b = getIntent().getExtras();
		tran = b.getParcelable("com.example.financial.model.Transaction");
		date = b.getParcelable("com.example.financial.model.myDate");
		display();
		
		datasource = new FinancialTransactionSource(this);
		datasource.open();
	}

	private void display() {
		tran_bkname.setText(tran.getBkDisName());
		tran_name.setText(tran.getName());
		tran_type.setText(tran.getType());
		tran_status.setText(tran.getStatus());
		//Log.i(MainActivity.LOGTAG, "!!!!"+tran.getDate());
		tran_date.setText(date.toString());
		tran_amount.setText(Double.toString(tran.getAmount()));
	}

	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_detail, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_trans) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to delete this transaction?");
			builder.setCancelable(false);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					datasource.deleteTransaction(tran.getRecordTime());
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
