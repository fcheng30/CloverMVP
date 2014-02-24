package com.example.financial.model;


import com.example.financialreportmvp.MainActivity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;



public class User implements Parcelable{
	
	private String userId;
	private String password;
	private String name;
	private String email;
	
	static public final User NULL_USER = new User("", "", "null","null@foo.com");
	static public final User ADMIN = new User("admin","pass1234","Administrator","admin@gatech.edu");
	/*******************************
	 * Default Constructor, makes a new User
	 */
	public User() {
		userId = "";
		password = "";
		name = "";
		email = "";
	}
	
	/*******************************
	 * Constructor, makes a new User
	 * @param un the userid
	 * @param p the password
	 * @param n the name
	 * @param e the email
	 */
	public User(String ui, String p, String n, String e ) {
		userId = ui;
		password = p;
		name = n;
		email = e;
	}
	
	/*******************************
	 * 
	 * @return the userid
	 */
	public String getUserid() {
		return userId;
	}
	
	/*******************************
	 * set userid
	 */
	public void setUserid(String userId) {
		this.userId = userId;
	}
	
	/*******************************
	 * 
	 * @return the user's name
	 */
	public String getName() {
		return name;
	}
	
	/*******************************
	 * 
	 * @return the name
	 */
	public void setName(String e) {
		this.name = e;
	}
	
	/*******************************
	 * 
	 * @return the user password
	 */
	public String getPassword() {
		return password;
	}
	
	/*******************************
	 * Reset user's password
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*******************************
	 * 
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}

	/*******************************
	 * 
	 * set user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return name +" : " + userId;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	public User(Parcel in) {
		userId = in.readString();
		password = in.readString();
		name = in.readString();
		email = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");
		dest.writeString(userId);
		dest.writeString(password);
		dest.writeString(name);
		dest.writeString(email);
	}

	public static final Parcelable.Creator<User> CREATOR =
			new Parcelable.Creator<User>() {

				@Override
				public User createFromParcel(Parcel arg0) {
					Log.i(MainActivity.LOGTAG, "createFromParcel");
					return new User(arg0);
				}

				@Override
				public User[] newArray(int arg0) {
					Log.i(MainActivity.LOGTAG, "newArray");
					return new User[arg0];
				}
		
			};
}
