package com.example.financial.model;

import com.example.financialreportmvp.MainActivity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Transaction implements Parcelable{
	
	private String name;
	private String type;
	private String date;
	private double amount;
	private String status;
	private String recordTime;
	private String bkDisName;
	


	public Transaction() {
		this.name = "";
		this.type = "";
		this.date = "";
		this.amount = 0;
		this.status = "";
		this.recordTime = "";
		this.bkDisName = "";
	}

	public Transaction(String name, String type, String date, Double amount,
			String status, String recordTime, String bkDisName) {
		this.name = name;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.status = status;
		this.recordTime = recordTime;
		this.bkDisName = bkDisName;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
	
	@Override
	public String toString() {
		return this.name + " : " + this.amount + " ( "
				+ this.date + ")";
	}
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	public Transaction(Parcel in) {
		name = in.readString();
		type = in.readString();
		amount = in.readDouble();
		date = in.readString();
		status = in.readString();
		recordTime = in.readString();
		bkDisName = in.readString();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");
		dest.writeString(name);
		dest.writeString(type);
		dest.writeDouble(amount);
		dest.writeString(date);
		dest.writeString(status);
		dest.writeString(recordTime);
		dest.writeString(bkDisName);

	}

	public static final Parcelable.Creator<Transaction> CREATOR =
			new Parcelable.Creator<Transaction>() {

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
