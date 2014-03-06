package com.example.financial.db;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.BankAccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancialAccountSource {
	public static final String LOGTAG="CLOVER";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase db;
	
	public static final String[] accountColumns = {
		FinancialDBOpenHelper.COLUMN_ACNAME,
		FinancialDBOpenHelper.COLUMN_DISNAME,
		FinancialDBOpenHelper.COLUMN_BALANCE,
		FinancialDBOpenHelper.COLUMN_MIR,
		FinancialDBOpenHelper.COLUMN_ACUSERID
	};
	
	public FinancialAccountSource(Context context) {
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
	
	public void update(FinancialAccountSource fs){
		Log.i(LOGTAG, "Databases updated");
		dbhelper.onUpgrade(fs.db, 1, 1);
	}
	
	public boolean checkAccount(String uid, String acname){
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_ACCOUNTS, accountColumns,
				FinancialDBOpenHelper.COLUMN_ACUSERID + " = " + "'"+ uid + "'", null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		if(cursor.getCount() >0){
			while(cursor.moveToNext()){
					String name= cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_ACNAME));
					if(name.equals(acname)){
						Log.i(LOGTAG, "find exsit account in " + uid);
						return true;
					}
			}
		}
		Log.i(LOGTAG, "did not find account in " + uid);
		return false;
	}
	
	public void addAccount(BankAccount ba){
		ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_ACNAME, ba.getName());
		values.put(FinancialDBOpenHelper.COLUMN_DISNAME, ba.getDisname());
		values.put(FinancialDBOpenHelper.COLUMN_BALANCE, ba.getBalance());
		values.put(FinancialDBOpenHelper.COLUMN_MIR, ba.getMir());
		values.put(FinancialDBOpenHelper.COLUMN_ACUSERID, ba.getUserid());
		db.insert(FinancialDBOpenHelper.TABLE_ACCOUNTS, null, values);
		Log.i(LOGTAG, "Add a new account " + ba.getName()+ "in " + ba.getUserid());
	}
	
	public List<BankAccount> getAccountList(String uid){
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_ACCOUNTS, accountColumns,
				FinancialDBOpenHelper.COLUMN_ACUSERID + " = " + "'"+ uid + "'", null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		if(cursor.getCount() >0){
			while(cursor.moveToNext()){
					BankAccount ba = new BankAccount();
					ba.setName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_ACNAME)));
					ba.setDisname(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_DISNAME)));
					ba.setBalance(cursor.getDouble(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_BALANCE)));
					ba.setMir(cursor.getDouble(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_MIR)));
					ba.setUserid(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_ACUSERID)));
					accounts.add(ba);
			}
		}
		return accounts;
	}
	public void removeAccount(String userID, String displayName){
		String[] values = new String[]{userID, displayName};
		db.delete(FinancialDBOpenHelper.TABLE_ACCOUNTS, FinancialDBOpenHelper.COLUMN_ACUSERID + "=? AND "
		+ FinancialDBOpenHelper.COLUMN_DISNAME + "=?" , values);
		Log.i(LOGTAG, "account deleted");
	}
}
