package com.example.financialreportmvp;

import java.util.List;

import com.example.financial.db.FinancialUserSource;
import com.example.financial.model.User;
import com.example.financialreportmvp.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdminPageActivity extends ListActivity{

	FinancialUserSource datasource;
	List<User> users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_page);
		datasource = new FinancialUserSource(this);
		datasource.open();
		users = datasource.getUserList();
		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, R.layout.list_view1, users);
		setListAdapter(adapter);
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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		User user = users.get(position);
		Intent intent = new Intent(this, UserDetailActivity.class);
		intent.putExtra("com.example.financial.model.User", user);
		startActivity(intent);
	}
}
