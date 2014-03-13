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
		FinancialDBOpenHelper.COLUMN_TRBKDISNAME,
		FinancialDBOpenHelper.COLUMN_TRUSERID
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
		values.put(FinancialDBOpenHelper.COLUMN_TRUSERID, tr.getUserid());
		
		
		double total = getBalance(tr.getBkDisName(), tr.getUserid());
		if (tr.getType().equals("Withdrawl")) {
			newbalance = total - tr.getAmount();
		} else {
			newbalance = total + tr.getAmount();
		}
		if(newbalance > 0){
			db.insert(FinancialDBOpenHelper.TABLE_TRANSACTIONS, null, values);
			updateBalance(newbalance, tr.getBkDisName(), tr.getUserid());
			flag = true;
		}
		Log.i(LOGTAG,
				"Add a new transaction " + tr.getName() + " in "
						+ tr.getBkDisName());
		Log.i(LOGTAG, "Add a new transaction " + tr.getName()+ "in " + tr.getBkDisName());
		return flag;
	}
	
	public List<Transaction> getTransactionList(String bankname, String userid){
		List<Transaction> trs = new ArrayList<Transaction>();
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_TRANSACTIONS, transactionColumns,
				FinancialDBOpenHelper.COLUMN_TRBKDISNAME + " = " + "'"+ bankname + "' AND " +
				FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'"+ userid + "'",
				null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		return cursorTransaction(cursor, trs);
	}
	
	public double getBalance(String disname, String userid) {
		double result = 0;
		Cursor c = db.query(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				FinancialAccountSource.accountColumns, FinancialDBOpenHelper.COLUMN_DISNAME + " = "
						+ "'" + disname + "' AND " + FinancialDBOpenHelper.COLUMN_ACUSERID + " = "
						+ "'"+ userid + "'", null, null, null, null);
		Log.i(LOGTAG, "Find " + c.getCount() + " rows in getBalance");
		if (c != null) {
			c.moveToFirst();
			//Balance is at the third column so index is 2
			result = c.getDouble(2);
		}
		Log.i(LOGTAG, "Get balance $" + result);
		return result;
	}
	
	public void updateBalance(double nb, String disname, String userid){
		ContentValues cd = new ContentValues();
		cd.put(FinancialDBOpenHelper.COLUMN_BALANCE, nb);
		db.update(FinancialDBOpenHelper.TABLE_ACCOUNTS, cd,
				FinancialDBOpenHelper.COLUMN_DISNAME + " = " + "'" + disname +"' AND "
				+ FinancialDBOpenHelper.COLUMN_ACUSERID + " = " + "'"+ userid + "'", null);
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
					tr.setUserid(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRUSERID)));
					trs.add(tr);
			}
		}
		return trs;
	}
}
