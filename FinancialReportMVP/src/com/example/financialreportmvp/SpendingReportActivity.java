package com.example.financialreportmvp;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.db.FinancialReportGenerator;
import com.example.financial.model.Report;
import com.example.financial.model.Transaction;
import com.example.financial.presenter.ReportPresenter;
import com.example.financial.view.IReportView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SpendingReportActivity extends ListActivity implements
		IReportView, OnItemSelectedListener {

	private FinancialReportGenerator datasource;
	private List<Transaction> spendingList;
	private TextView text;
	private TextView amountText;
	private Report rp;
	private ReportPresenter presenter;
	private Spinner yearSpinner;
	private List<String> yearList;
	private Spinner monthSpinner;
	private List<String> monthList;
	private String year = "";
	private String month = "";
	private String userid;
	Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		datasource = new FinancialReportGenerator(this);
		presenter = new ReportPresenter(this);
		rp = new Report();
		
		b = getIntent().getExtras();
		userid = b.getString("userid");
		
		text = (TextView) findViewById(R.id.reportText);
		amountText = (TextView) findViewById(R.id.reportTotalAmountText);

		yearSpinner = (Spinner) findViewById(R.id.reportYearSpinner);
		monthSpinner = (Spinner) findViewById(R.id.reportMonthSpinner);
		yearSpinner.setOnItemSelectedListener(this);
		monthSpinner.setOnItemSelectedListener(this);
		yearList = getYearList();
		monthList = getMonthList();

		// Create Adapter for spinners
		ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, yearList);
		yearAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpinner.setAdapter(yearAdapter);
		// ------------
		ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, monthList);
		monthAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpinner.setAdapter(monthAdapter);

		datasource.open();
		display(rp.getCurrentYearMonth());

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		switch (parent.getId()) {
		case R.id.reportYearSpinner:
			year = parent.getItemAtPosition(position).toString();
			break;
		case R.id.reportMonthSpinner:
			month = parent.getItemAtPosition(position).toString();
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	public void display(String yearmonth) {
		spendingList = datasource.getSpendingList(yearmonth, userid);
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this,
				R.layout.list_view1, spendingList);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Spending List");
		text.setText(rp.getSpendingTitle(year, month));
		amountText.setText(rp.getTotalTile(datasource.getTotal(spendingList)));
	}

	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
		display(rp.getCurrentYearMonth());
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
		intent.putExtra("com.example.financial.model.myDate", tran.getDate());
		Log.i(MainActivity.LOGTAG, "Open Transaction Detail");
		startActivity(intent);
	}

	public void onViewClick(View v) {
		presenter.onClickView();
	}

	@Override
	public ArrayList<String> getYearList() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("2007");
		al.add("2008");
		al.add("2009");
		al.add("2010");
		al.add("2011");
		al.add("2012");
		al.add("2013");
		al.add("2014");
		return al;
	}

	@Override
	public ArrayList<String> getMonthList() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("01"); al.add("02"); al.add("03"); al.add("04");
		al.add("05"); al.add("06"); al.add("07"); al.add("08");
		al.add("09"); al.add("10"); al.add("11"); al.add("12");
		return al;
	}

	@Override
	public String getYearMonth() {
		return year+month;
	}

	@Override
	public void refresh() {
		display(getYearMonth());

	}

}
