package com.example.financial.db;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.Transaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancialReportGenerator {
	public static final String LOGTAG = "CLOVER";

	SQLiteOpenHelper dbhelper;
	SQLiteDatabase db;
	
	public FinancialReportGenerator(Context context) {
		dbhelper = new FinancialDBOpenHelper(context);
	}
	
	public void open() {
		Log.i(LOGTAG, "Account Databases opened");
		db = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Account Databases closed");
		db.close();
	}
		
	public List<Transaction> getSpendingList(String date){
		List<Transaction> trs = new ArrayList<Transaction>();
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_TRANSACTIONS, FinancialTransactionSource.transactionColumns,
				FinancialDBOpenHelper.COLUMN_TRTYPE + " = " + "'Withdrawl' AND "
				 + "strftime('%Y%m'," + FinancialDBOpenHelper.COLUMN_TRDATE + ") = " + "'" + date + "'",
				 null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		return FinancialTransactionSource.cursorTransaction(cursor, trs);
	}
	
}
