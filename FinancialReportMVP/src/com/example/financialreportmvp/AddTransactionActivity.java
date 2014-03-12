package com.example.financialreportmvp;

import java.util.List;

import com.example.financial.db.FinancialTransactionSource;
import com.example.financial.model.Transaction;
import com.example.financial.presenter.AddTransactionPresenter;
import com.example.financial.view.IAddTransactionView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddTransactionActivity extends Activity implements IAddTransactionView, OnItemSelectedListener{

	AddTransactionPresenter presenter;
	private EditText name;
	private EditText date;
	private EditText amount;
	private String type;
	private String bankname;
	private TextView text;
	private Spinner typeSpinner;
	private List<String> categories;
	private FinancialTransactionSource datasource;
	Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		presenter = new AddTransactionPresenter(this);
		datasource = new FinancialTransactionSource(this);
		b = getIntent().getExtras();
		bankname = b.getString("bankname");
		
		text = (TextView) findViewById(R.id.tranText);
		name = (EditText) findViewById(R.id.tranName);
		date = (EditText) findViewById(R.id.tranDate);
		amount = (EditText) findViewById(R.id.tranAmount);
		typeSpinner = (Spinner) findViewById(R.id.tranTypeSpinner);
		typeSpinner.setOnItemSelectedListener(this);
		
		categories = presenter.getTypeList();
		// Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new 
        		ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		type = parent.getItemAtPosition(position).toString();
	
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
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
	public String getName() {
		return name.getText().toString();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getDate() {
		return date.getText().toString();
	}

	@Override
	public String getAmount() {
		return amount.getText().toString();
	}


	@Override
	public String getBKDisname() {
		return bankname;
	}

	@Override
	public boolean addTrans(Transaction t) {
		boolean flag = false;
		if(datasource.addTransaction(t)){
			flag = true;
		}
		return flag;
	}

	@Override
	public void setText(String t) {
		text.setText(t);
	}

	@Override
	public void goBack() {
		finish();
	}



}
