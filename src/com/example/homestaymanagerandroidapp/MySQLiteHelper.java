package com.example.homestaymanagerandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_STUDENTS = "Students";
	public static final String TABLE_FAMILIES = "Families";
	public static final String TABLE_WIZARD_MATCH = "WizMatch";
	public static final String TABLE_FAVORITES = "Favorites";
	
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_FNAME = "firstName";
	public static final String COLUMN_LNAME = "lastName";
	public static final String COLUMN_GENDER = "gender";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_START_DATE = "startDate";
	public static final String COLUMN_END_DATE = "finalDate";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_STATE = "state";
	public static final String COLUMN_ZIP = "zipCode";
	public static final String COLUMN_ALLERGIES = "allergies";
	public static final String COLUMN_DOG_PET_PREF = "dogPetPref";
	public static final String COLUMN_CAT_PET_PREF = "catPetPref";
	public static final String COLUMN_FAMSIZE = "familySize";
	public static final String COLUMN_SMOKE = "smoke";
	public static final String COLUMN_PASSWORD = "password";
	
	public static final String COLUMN_STUDENTID = "StudentID";
	public static final String COLUMN_FAMILYID = "FamilyID";
	
	
	private static final String DATABASE_NAME = "HomeStay1.db";
	private static final int DATABASE_VERSION = 2;
	
	//Database creation sql statement
	private static final String DATABASE_CREATE_STUDENT = "create table "
		      + TABLE_STUDENTS + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_FNAME
		      + " text not null, " + COLUMN_LNAME + " text not null, " + 
		      COLUMN_GENDER + " text, "  + COLUMN_PHONE + " text not null, " +  
		      COLUMN_EMAIL + " text, " + COLUMN_START_DATE + " text, "
		      + COLUMN_END_DATE + " text, " + COLUMN_ADDRESS + " text, " + COLUMN_STATE 
		      + " text, " + COLUMN_ZIP + " text, "  + COLUMN_ALLERGIES + 
		      " integer, " + COLUMN_DOG_PET_PREF + " integer, " + COLUMN_CAT_PET_PREF + 
		      " integer, " + COLUMN_FAMSIZE + " integer, " + COLUMN_SMOKE + " integer, " + COLUMN_PASSWORD + " text " +");" ;
	
	private static final String DATABASE_CREATE_FAMILIES = "create table "
		      + TABLE_FAMILIES + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_FNAME
		      + " integer not null, " + COLUMN_LNAME + " text not null, " + 
		      COLUMN_GENDER + " text, "  + COLUMN_PHONE + " text not null, " +  
		      COLUMN_EMAIL + " text, " + COLUMN_START_DATE + " text, "
		      + COLUMN_END_DATE + " text, " + COLUMN_ADDRESS + " text, " + 
		      COLUMN_STATE + " text, " + COLUMN_ZIP + " text, " + COLUMN_ALLERGIES + 
		      " integer, " + COLUMN_DOG_PET_PREF + " integer, " + COLUMN_CAT_PET_PREF + 
		      " integer, " + COLUMN_FAMSIZE + " integer, " + COLUMN_SMOKE + " integer, " + COLUMN_PASSWORD + " text " + ");" ;
	
	private static final String DATABASE_CREATE_WIZMATCH = "create table " + TABLE_WIZARD_MATCH +
			"(" + COLUMN_STUDENTID + " integer primary key , " + COLUMN_FAMILYID + " integer not null); ";
	
	private static final String DATABASE_CREATE_FAVORITES = "create table " + TABLE_FAVORITES +
			"(" + COLUMN_STUDENTID + " integer primary key , " + COLUMN_FAMILYID + " integer not null); ";
	
	public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }
	
	@Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE_STUDENT);
	    database.execSQL(DATABASE_CREATE_FAMILIES);
	    database.execSQL(DATABASE_CREATE_WIZMATCH);
	    database.execSQL(DATABASE_CREATE_FAVORITES);
	    
	  }
	
	 @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILIES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_WIZARD_MATCH);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
	    onCreate(db);
	  }

}
