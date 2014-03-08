package com.example.financial.db;

import java.util.ArrayList;
import java.util.List;

import com.example.financial.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancialUserSource {
	public static final String LOGTAG="CLOVER";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase db;
	
	protected static final String[] userColumns = {
		FinancialDBOpenHelper.COLUMN_USERID,
		FinancialDBOpenHelper.COLUMN_PASSWORD,
		FinancialDBOpenHelper.COLUMN_NAME,
		FinancialDBOpenHelper.COLUMN_EMAIL,
		FinancialDBOpenHelper.COLUMN_TYPE
	};
	
	public FinancialUserSource(Context context) {
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
	
	
	public void update(FinancialUserSource fs){
		Log.i(LOGTAG, "Databases updated");
		dbhelper.onUpgrade(fs.db, 1, 1);
	}
	
	public User findUser(String uid){
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_USERS, userColumns,
				null, null, null, null, null);
		if(cursor.getCount() >0){
			while(cursor.moveToNext()){
				if(cursor.getString(cursor.getColumnIndex(
						FinancialDBOpenHelper.COLUMN_USERID)).equals(uid)){
					User user = new User();
					user.setUserid(uid);
					user.setPassword(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_PASSWORD)));
					user.setName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_NAME)));
					Log.i(LOGTAG, "find user " + uid);
					return user;
				}
			}
		}
		Log.i(LOGTAG, "find NULL_USER");
		return User.NULL_USER;
	}
	
	public void addUser(User user){
		ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_USERID, user.getUserid());
		values.put(FinancialDBOpenHelper.COLUMN_PASSWORD, user.getPassword());
		values.put(FinancialDBOpenHelper.COLUMN_NAME, user.getName());
		values.put(FinancialDBOpenHelper.COLUMN_EMAIL, user.getEmail());
		values.put(FinancialDBOpenHelper.COLUMN_TYPE, "user");
		db.insert(FinancialDBOpenHelper.TABLE_USERS, null, values);
		Log.i(LOGTAG, "Add a new user " + user.getUserid());
	}
	
	public List<User> getUserList(){
		List<User> users = new ArrayList<User>();
		Cursor cursor = db.query(FinancialDBOpenHelper.TABLE_USERS, userColumns,
				"type_of_user = 'user'", null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		if(cursor.getCount() >0){
			while(cursor.moveToNext()){
					User user = new User();
					user.setUserid(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_USERID)));
					user.setPassword(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_PASSWORD)));
					user.setName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_NAME)));
					user.setEmail(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_EMAIL)));
					users.add(user);
			}
		}
		return users;
	}

	public void removeUser(String userid) {
		String[] value = new String[]{userid};
		db.delete(FinancialDBOpenHelper.TABLE_USERS, FinancialDBOpenHelper.COLUMN_USERID + "=?", value);
		Log.i(LOGTAG, "Delete user : " + userid);
	}
	
	public void resetPW(String uid){
		ContentValues values = new ContentValues();
		String[] userid = new String[]{uid};
		values.put(FinancialDBOpenHelper.COLUMN_PASSWORD, "123456");
		db.update(FinancialDBOpenHelper.TABLE_USERS, values, FinancialDBOpenHelper.COLUMN_USERID + "=?", userid);
		Log.i(LOGTAG, "Password reset : " + uid);
	}
	
}
