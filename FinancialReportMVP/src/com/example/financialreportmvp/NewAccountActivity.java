package com.example.financialreportmvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.financial.db.FinancialAccountSource;
import com.example.financial.model.BankAccount;
import com.example.financial.presenter.NewAccountPresenter;
import com.example.financial.view.INewAccountView;

public class NewAccountActivity extends Activity implements INewAccountView {

	private NewAccountPresenter presenter;
	private EditText acname;
	private EditText disname;
	private EditText balance;
	private EditText mir;
	private TextView resultText;
	private Bundle b;
	private FinancialAccountSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newaccount_page);
		
		presenter = new NewAccountPresenter(this);
		datasource = new FinancialAccountSource(this);
		
		acname = (EditText) findViewById(R.id.newAcName);
		disname = (EditText) findViewById(R.id.newAcDisName);
		balance = (EditText) findViewById(R.id.newAcBalance);
		mir = (EditText) findViewById(R.id.newAcMIR);
		resultText = (TextView) findViewById(R.id.accountText);
		b = getIntent().getExtras();
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
	
	public void onSubmitClick(View v){
		presenter.onSubmitClick();
	}


	@Override
	public String getAcName() {
		return acname.getText().toString();
	}


	@Override
	public String getDisName() {
		return disname.getText().toString();
	}


	@Override
	public String getBalance() {
		return balance.getText().toString();
	}


	@Override
	public String getMIR() {
		return mir.getText().toString();
	}
	
	@Override
	public String getUserid() {
		return b.getString("userid");
	}

	@Override
	public void addAccount(BankAccount ba) {
		datasource.addAccount(ba);
	}


	@Override
	public void goAccountPage() {
		finish();
	}

	@Override
	public void setText(String text) {
		resultText.setText(text);
		
	}

	@Override
	public boolean checkAccount() {
		return datasource.checkAccount(getUserid(), getAcName());
	}
}
