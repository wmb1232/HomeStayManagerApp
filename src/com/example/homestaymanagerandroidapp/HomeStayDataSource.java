package com.example.homestaymanagerandroidapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HomeStayDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_EMAIL};
	  
	  public HomeStayDataSource(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

		  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }

		  public Student createStudent(String fname, String lname, String email, String gender, 
				  String phone, String sDate, String eDate, String address, String state, 
				  int zip, String allergies, int dogPet, int catPet) {
		    ContentValues values = new ContentValues();
		    values.put(MySQLiteHelper.COLUMN_FNAME, fname);
		    values.put(MySQLiteHelper.COLUMN_LNAME, lname);
		    values.put(MySQLiteHelper.COLUMN_EMAIL, email);
		    values.put(MySQLiteHelper.COLUMN_GENDER, gender);
		    values.put(MySQLiteHelper.COLUMN_PHONE, phone);
		    values.put(MySQLiteHelper.COLUMN_START_DATE, sDate);
		    values.put(MySQLiteHelper.COLUMN_END_DATE, eDate);
		    values.put(MySQLiteHelper.COLUMN_ADDRESS, address);
		    values.put(MySQLiteHelper.COLUMN_STATE, state);
		    values.put(MySQLiteHelper.COLUMN_ZIP, zip);
		    values.put(MySQLiteHelper.COLUMN_ALLERGIES, allergies);
		    values.put(MySQLiteHelper.COLUMN_DOG_PET_PREF, dogPet);
		    values.put(MySQLiteHelper.COLUMN_CAT_PET_PREF, catPet);
		    long insertId = database.insert(MySQLiteHelper.TABLE_STUDENTS, null,
		        values);
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_STUDENTS,
		        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Student newStudent = cursorToStudent(cursor);
		    cursor.close();
		    return newStudent;
		  }
		  
		  
		  
		  
		  private Student cursorToStudent(Cursor cursor) {
			    Student student = new Student();
			    student._id = cursor.getInt(0);
			    student.firstName = cursor.getString(1);
			    return student;
			  }

}


