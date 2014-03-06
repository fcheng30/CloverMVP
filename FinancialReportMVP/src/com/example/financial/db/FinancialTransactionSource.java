package com.example.financial.db;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.Transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancialTransactionSource {
public static final String LOGTAG="CLOVER";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase db;
	
	public static final String[] transactionColumns = {
		FinancialDBOpenHelper.COLUMN_TRNAME,
		FinancialDBOpenHelper.COLUMN_TRTYPE,
		FinancialDBOpenHelper.COLUMN_TRDATE,
		FinancialDBOpenHelper.COLUMN_TRAMOUNT,
		FinancialDBOpenHelper.COLUMN_TRSTATUS,
		FinancialDBOpenHelper.COLUMN_TRRECORD,
		FinancialDBOpenHelper.COLUMN_TRBKDISNAME
	};
	
	public FinancialTransactionSource(Context context) {
		dbhelper = new FinancialDBOpenHelper(context);
	}
	
	public void open(){
		Log.i(LOGTAG, "Databases opened");
		db = dbhelper.getWritableDatabase();
	}
	
	public void close(){
		Log.i(LOGTAG, "Databases closed");
		db.close();
	}
	
	public void addTransaction(Transaction tr){
		double newbalance =0;
		ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_TRNAME, tr.getName());
		values.put(FinancialDBOpenHelper.COLUMN_TRTYPE, tr.getType());
		values.put(FinancialDBOpenHelper.COLUMN_TRDATE, tr.getDate());
		values.put(FinancialDBOpenHelper.COLUMN_TRAMOUNT, tr.getAmount());
		values.put(FinancialDBOpenHelper.COLUMN_TRSTATUS, tr.getStatus());
		values.put(FinancialDBOpenHelper.COLUMN_TRRECORD, tr.getRecordTime());
		values.put(FinancialDBOpenHelper.COLUMN_TRBKDISNAME, tr.getBkDisName());
		db.insert(FinancialDBOpenHelper.TABLE_TRANSACTIONS, null, values);
		
		double total = getBalance(tr.getBkDisName());
		if (tr.getType().equals("Withdrawl")) {
			newbalance = total - tr.getAmount();
		} else {
			newbalance = total + tr.getAmount();
		}
		updateBalance(newbalance, tr.getBkDisName());
		Log.i(LOGTAG,
				"Add a new transaction " + tr.getName() + " in "
						+ tr.getBkDisName());
		Log.i(LOGTAG, "Add a new transaction " + tr.getName()+ "in " + tr.getBkDisName());
	}
	
	public List<Transaction> getTransactionList(String bankname){
		List<Transaction> trs = new ArrayList<Transaction>();
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_TRANSACTIONS, transactionColumns,
				FinancialDBOpenHelper.COLUMN_TRBKDISNAME + " = " + "'"+ bankname + "'", null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		if(cursor.getCount() >0){
			while(cursor.moveToNext()){
				Transaction tr = new Transaction();
					tr.setName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRNAME)));
					tr.setType(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRTYPE)));
					tr.setDate(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRDATE)));
					tr.setAmount(cursor.getDouble(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRAMOUNT)));
					tr.setStatus(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRSTATUS)));
					tr.setRecordTime(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRRECORD)));
					tr.setBkDisName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRBKDISNAME)));
					trs.add(tr);
			}
		}
		return trs;
	}
	
	public double getBalance(String disname) {
		double result = 0;
		Cursor c = db.query(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				FinancialAccountSource.accountColumns, FinancialDBOpenHelper.COLUMN_DISNAME + " = "
						+ "'" + disname + "'", null, null, null, null);
		Log.i(LOGTAG, "Find " + c.getCount() + " rows in getBalance");
		if (c != null) {
			c.moveToFirst();
			result = c.getDouble(2);
		}
		Log.i(LOGTAG, "Get balance $" + result);
		return result;
	}
	
	public void updateBalance(double nb, String disname){
		ContentValues cd = new ContentValues();
		cd.put(FinancialDBOpenHelper.COLUMN_BALANCE, nb);
		db.update(FinancialDBOpenHelper.TABLE_ACCOUNTS, cd,
				FinancialDBOpenHelper.COLUMN_DISNAME + " = " + "'" + disname +"'", null);
		Log.i(LOGTAG, "Update new balance $"+ nb +  " in" + disname);
	}
}
