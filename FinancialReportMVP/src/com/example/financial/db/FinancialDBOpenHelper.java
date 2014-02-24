package com.example.financial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancialDBOpenHelper extends SQLiteOpenHelper{
	private static final String LOGTAG = "CLOVER";
	
	private static final String DATABASE_NAME = "clover.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERID = "userID";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_TYPE = "type_of_user";
	
	private static final String USER_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_USERS + "( " +
			COLUMN_USERID + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT, "
		    + COLUMN_NAME + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_TYPE + " TEXT )";
	
	public static final String TABLE_ACCOUNTS = "accounts";
	public static final String COLUMN_ACNAME = "acName";
	public static final String COLUMN_DISNAME = "disName";
	public static final String COLUMN_BALANCE = "balance";
	public static final String COLUMN_MIR = "mirate";
	public static final String COLUMN_ACUSERID = "acUserID";
	
	private static final String ACCOUNT_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_ACCOUNTS + "( " +
			COLUMN_ACNAME + " TEXT PRIMARY KEY, " + COLUMN_DISNAME + " TEXT, "
		    + COLUMN_BALANCE + " DOUBLE, " + COLUMN_MIR + " DOUBLE, "
			+ COLUMN_ACUSERID + " TEXT NOT NULL,"
		    + "FOREIGN KEY(" + COLUMN_ACUSERID + ") REFERENCES " + TABLE_USERS
		    + "(" + COLUMN_USERID+  ")" +")";
	
	public FinancialDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_USERID,"admin");
		values.put(FinancialDBOpenHelper.COLUMN_PASSWORD, "pass123");
		values.put(FinancialDBOpenHelper.COLUMN_NAME, "administrator");
		values.put(FinancialDBOpenHelper.COLUMN_EMAIL, "admin@gatech.edu");
		values.put(FinancialDBOpenHelper.COLUMN_TYPE, "admin");
		db.execSQL(USER_TABLE_CREATE);
		Log.i(LOGTAG, "Users Table has been created");
		db.insert(FinancialDBOpenHelper.TABLE_USERS, null, values);
		Log.i(LOGTAG, "Admin has been created");
		db.execSQL(ACCOUNT_TABLE_CREATE);
		Log.i(LOGTAG, "Accounts Table has been created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACCOUNTS);
		onCreate(db);
	}

}
