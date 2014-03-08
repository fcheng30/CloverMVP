package com.example.financial.db;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.Transaction;
import com.example.financial.model.myDate;

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
	
	protected static final String[] transactionColumns = {
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
	
	public boolean addTransaction(Transaction tr){
		boolean flag = false;
		double newbalance =0;
		ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_TRNAME, tr.getName());
		values.put(FinancialDBOpenHelper.COLUMN_TRTYPE, tr.getType());
		values.put(FinancialDBOpenHelper.COLUMN_TRDATE, tr.getDate().getRawDate());
		values.put(FinancialDBOpenHelper.COLUMN_TRAMOUNT, tr.getAmount());
		values.put(FinancialDBOpenHelper.COLUMN_TRSTATUS, tr.getStatus());
		values.put(FinancialDBOpenHelper.COLUMN_TRRECORD, tr.getRecordTime());
		values.put(FinancialDBOpenHelper.COLUMN_TRBKDISNAME, tr.getBkDisName());
		
		
		double total = getBalance(tr.getBkDisName());
		if (tr.getType().equals("Withdrawl")) {
			newbalance = total - tr.getAmount();
		} else {
			newbalance = total + tr.getAmount();
		}
		if(newbalance > 0){
			db.insert(FinancialDBOpenHelper.TABLE_TRANSACTIONS, null, values);
			updateBalance(newbalance, tr.getBkDisName());
			flag = true;
		}
		Log.i(LOGTAG,
				"Add a new transaction " + tr.getName() + " in "
						+ tr.getBkDisName());
		Log.i(LOGTAG, "Add a new transaction " + tr.getName()+ "in " + tr.getBkDisName());
		return flag;
	}
	
	public List<Transaction> getTransactionList(String bankname){
		List<Transaction> trs = new ArrayList<Transaction>();
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_TRANSACTIONS, transactionColumns,
				FinancialDBOpenHelper.COLUMN_TRBKDISNAME + " = " + "'"+ bankname + "'", null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		return cursorTransaction(cursor, trs);
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
	
	public void deleteTransaction(String recordTime){
		String[] values = new String[]{recordTime};
		db.delete(FinancialDBOpenHelper.TABLE_TRANSACTIONS, FinancialDBOpenHelper.COLUMN_TRRECORD + "=?", values);
		Log.i(LOGTAG, "transaction deleted");
	}
	
	protected static List<Transaction> cursorTransaction(Cursor c, List<Transaction> trs){
		if(c.getCount() >0){
			while(c.moveToNext()){
				Transaction tr = new Transaction();
					tr.setName(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRNAME)));
					tr.setType(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRTYPE)));
					Log.i(LOGTAG, c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRDATE)));
					tr.setDate(new myDate(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRDATE))));
					tr.setAmount(c.getDouble(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRAMOUNT)));
					tr.setStatus(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRSTATUS)));
					tr.setRecordTime(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRRECORD)));
					tr.setBkDisName(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRBKDISNAME)));
					trs.add(tr);
			}
		}
		return trs;
	}
}
