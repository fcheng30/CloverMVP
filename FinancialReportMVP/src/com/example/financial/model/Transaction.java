package com.example.financial.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.financialreportmvp.MainActivity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Transaction implements Parcelable {

	private String name;
	private String type;
	private myDate date;
	private double amount;
	private String status;
	private String recordTime;
	private String bkDisName;
	private String userid;

	public Transaction() {
		this.name = "";
		this.type = "";
		this.date = new myDate();
		this.amount = 0;
		this.status = "pending";
		this.recordTime = getDateTime();
		this.bkDisName = "";
		this.userid = "";
	}

	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

	public Transaction(String name, String type, myDate date, Double amount,
			String bkDisName, String userid) {
		this.name = name;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.status = "pending";
		this.recordTime = getDateTime();
		this.bkDisName = bkDisName;
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public myDate getDate() {
		return date;
	}

	public void setDate(myDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getBkDisName() {
		return bkDisName;
	}

	public void setBkDisName(String bkDisName) {
		this.bkDisName = bkDisName;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return this.bkDisName + " : " + this.name
				+ " : $" + this.amount + " ( " + this.date + ")";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public Transaction(Parcel in) {
		name = in.readString();
		type = in.readString();
		amount = in.readDouble();
		date = (myDate) in.readValue(myDate.class.getClassLoader());
		status = in.readString();
		recordTime = in.readString();
		bkDisName = in.readString();
		userid = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");
		dest.writeString(name);
		dest.writeString(type);
		dest.writeDouble(amount);
		dest.writeValue(date);
		dest.writeString(status);
		dest.writeString(recordTime);
		dest.writeString(bkDisName);
		dest.writeString(userid);

	}


	public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {

		@Override
		public Transaction createFromParcel(Parcel arg0) {
			Log.i(MainActivity.LOGTAG, "createFromParcel");
			return new Transaction(arg0);
		}

		@Override
		public Transaction[] newArray(int arg0) {
			Log.i(MainActivity.LOGTAG, "newArray");
			return new Transaction[arg0];
		}

	};

}
