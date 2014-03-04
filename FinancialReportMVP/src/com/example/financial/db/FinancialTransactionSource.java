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
	
	private static final String[] transactionColumns = {
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
		ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_TRNAME, tr.getName());
		values.put(FinancialDBOpenHelper.COLUMN_TRTYPE, tr.getType());
		values.put(FinancialDBOpenHelper.COLUMN_TRDATE, tr.getDate());
		values.put(FinancialDBOpenHelper.COLUMN_TRAMOUNT, tr.getAmount());
		values.put(FinancialDBOpenHelper.COLUMN_TRSTATUS, tr.getStatus());
		values.put(FinancialDBOpenHelper.COLUMN_TRRECORD, tr.getRecordTime());
		values.put(FinancialDBOpenHelper.COLUMN_TRBKDISNAME, tr.getBkDisName());
		db.insert(FinancialDBOpenHelper.TABLE_TRANSACTIONS, null, values);
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
}
