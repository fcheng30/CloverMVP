package com.example.financial.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.financialreportmvp.MainActivity;

public class BankAccount implements Parcelable{
	private String name;
	private String disname;
	private double balance;
	private double mir;
	private String userid;
	
	static public final BankAccount NULL_ACCOUNT = new BankAccount("", "", 0,0,"null");
	
	public BankAccount(){
		this.name = "";
		this.disname = "";
		this.balance = 0;
		this.mir = 0;
		this.userid = "";
	}
	
	public BankAccount(String name, String disname, double balance, double mir,
			String userid) {
		super();
		this.name = name;
		this.disname = disname;
		this.balance = balance;
		this.mir = mir;
		this.userid = userid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisname() {
		return disname;
	}
	public void setDisname(String disname) {
		this.disname = disname;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getMir() {
		return mir;
	}
	public void setMir(double mir) {
		this.mir = mir;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		String result;
		result = this.name + " : $" + this.balance;
		return result;
	}
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	public BankAccount(Parcel in) {
		name = in.readString();
		disname = in.readString();
		balance = in.readDouble();
		mir = in.readDouble();
		userid = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");
		dest.writeString(name);
		dest.writeString(disname);
		dest.writeDouble(balance);
		dest.writeDouble(mir);
		dest.writeString(userid);

	}

	public static final Parcelable.Creator<BankAccount> CREATOR =
			new Parcelable.Creator<BankAccount>() {

				@Override
				public BankAccount createFromParcel(Parcel arg0) {
					Log.i(MainActivity.LOGTAG, "createFromParcel");
					return new BankAccount(arg0);
				}

				@Override
				public BankAccount[] newArray(int arg0) {
					Log.i(MainActivity.LOGTAG, "newArray");
					return new BankAccount[arg0];
				}
		
			};
}
