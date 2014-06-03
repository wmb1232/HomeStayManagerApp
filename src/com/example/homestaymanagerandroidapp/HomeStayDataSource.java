package com.example.homestaymanagerandroidapp;

import java.util.ArrayList;
import java.util.Date;
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
//		    values.put(MySQLiteHelper.COLUMN_EMAIL, email);
//		    values.put(MySQLiteHelper.COLUMN_GENDER, gender);
		    values.put(MySQLiteHelper.COLUMN_PHONE, phone);
//		    values.put(MySQLiteHelper.COLUMN_START_DATE, sDate);
//		    values.put(MySQLiteHelper.COLUMN_END_DATE, eDate);
//		    values.put(MySQLiteHelper.COLUMN_ADDRESS, address);
//		    values.put(MySQLiteHelper.COLUMN_STATE, state);
//		    values.put(MySQLiteHelper.COLUMN_ZIP, zip);
//		    values.put(MySQLiteHelper.COLUMN_ALLERGIES, allergies);
//		    values.put(MySQLiteHelper.COLUMN_DOG_PET_PREF, dogPet);
//		    values.put(MySQLiteHelper.COLUMN_CAT_PET_PREF, catPet);
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
		  
		  
		  public ArrayList<Student> getAllStudents() {
			     ArrayList<Student> students = new ArrayList<Student>();

			    Cursor cursor = database.query(MySQLiteHelper.TABLE_STUDENTS,
			        allColumns, null, null, null, null, null);

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      Student student = cursorToStudent(cursor);
			      students.add(student);
			      cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return students;
			  }
		  
		private Student cursorToStudent(Cursor cursor) {
			    Student student = new Student();
			    student._id = cursor.getInt(0);
			    student.firstName = cursor.getString(1);
			    //student.lastName = cursor.getString(2);
			    //student.gender = cursor.getString(3);
			    //student.phone = cursor.getString(4);
			    //student.emailAddress = cursor.getString(5);
			    //student.startDate = new Date(cursor.getString(6));
			    //student.endDate = new Date(cursor.getString(7));
			    //student.address = cursor.getString(8);
			    //student.zipCode = cursor.getInt(9);
			    //student.state = cursor.getString(10);
//			    student.allergies = cursor.getString(11);
//			    if(cursor.getInt(12) == 1)
//			    {
//			    	student.dogPet = true;
//			    }
//			    else
//			    {
//			    	student.dogPet = false;
//			    }
//			    if(cursor.getInt(13) == 1)
//			    {
//			    	student.catPet = true;
//			    }
//			    else
//			    {
//			    	student.catPet = false;
//			    }
			    
			    return student;
			  }

}


