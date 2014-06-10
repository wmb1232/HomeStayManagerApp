package com.example.homestaymanagerandroidapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;

public class HomeStayDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = 
		  { 	MySQLiteHelper.COLUMN_ID,
				MySQLiteHelper.COLUMN_FNAME,
				MySQLiteHelper.COLUMN_LNAME,
				MySQLiteHelper.COLUMN_GENDER,
				MySQLiteHelper.COLUMN_PHONE,
				MySQLiteHelper.COLUMN_EMAIL,
				MySQLiteHelper.COLUMN_START_DATE,
				MySQLiteHelper.COLUMN_END_DATE,
				MySQLiteHelper.COLUMN_ADDRESS,
				MySQLiteHelper.COLUMN_STATE,
				MySQLiteHelper.COLUMN_ZIP,
				MySQLiteHelper.COLUMN_ALLERGIES,
				MySQLiteHelper.COLUMN_DOG_PET_PREF,
				MySQLiteHelper.COLUMN_CAT_PET_PREF,
				MySQLiteHelper.COLUMN_FAMSIZE,
				MySQLiteHelper.COLUMN_SMOKE,
				MySQLiteHelper.COLUMN_PASSWORD
		  };
	  
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
				  int zip, String allergies, int dogPet, int catPet, int famSize, int smoke, String password) 
		{
		    
			
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
		    values.put(MySQLiteHelper.COLUMN_FAMSIZE, famSize);
		    values.put(MySQLiteHelper.COLUMN_SMOKE, smoke);
		    values.put(MySQLiteHelper.COLUMN_PASSWORD, password);
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
		
		
		public int updateStudent(Student student) 
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_FNAME, student.firstName);
		    values.put(MySQLiteHelper.COLUMN_LNAME, student.lastName);
		    values.put(MySQLiteHelper.COLUMN_EMAIL, student.emailAddress);
		    values.put(MySQLiteHelper.COLUMN_GENDER, student.gender);
		    values.put(MySQLiteHelper.COLUMN_PHONE, student.phone);
		    String sDate_to_string = formatter.format(student.startDate);
		    values.put(MySQLiteHelper.COLUMN_START_DATE, sDate_to_string);
		    String eDate_to_string = formatter.format(student.endDate);
		    values.put(MySQLiteHelper.COLUMN_END_DATE, eDate_to_string);
		    values.put(MySQLiteHelper.COLUMN_ADDRESS, student.address);
		    values.put(MySQLiteHelper.COLUMN_STATE, student.state);
		    values.put(MySQLiteHelper.COLUMN_ZIP, student.zipCode);
		    values.put(MySQLiteHelper.COLUMN_ALLERGIES, student.allergies);
		    int dog = 0;
		    if(student.dogPet)
		    {
		    	dog = 1;
		    }
		    values.put(MySQLiteHelper.COLUMN_DOG_PET_PREF, dog);
		    int cat = 0;
		    if(student.dogPet)
		    {
		    	cat = 1;
		    }
		    values.put(MySQLiteHelper.COLUMN_CAT_PET_PREF, cat);
		    values.put(MySQLiteHelper.COLUMN_FAMSIZE, student.famSize);
		    int smoke = 0;
		    if(student.smoke)
		    {
		    	smoke = 1;
		    }
		    values.put(MySQLiteHelper.COLUMN_SMOKE, smoke);
		    values.put(MySQLiteHelper.COLUMN_PASSWORD, student.password);
		 
		    // updating row
		    return database.update(MySQLiteHelper.TABLE_STUDENTS, values, MySQLiteHelper.COLUMN_ID + " = " + student._id,null);
		}
		  
		public void deleteStudent(String email)
		{
		    System.out.println("Student deleted with email: " + email);
		    database.delete(MySQLiteHelper.TABLE_STUDENTS, MySQLiteHelper.COLUMN_EMAIL
		        + " = " + email , null);
		}
		
		public Student getStudent(String email)
		{
			System.out.println("Getting Student with email: " + email);
			
			Cursor cursor = database.query(MySQLiteHelper.TABLE_STUDENTS, allColumns , MySQLiteHelper.COLUMN_EMAIL + " = " 
			+ email, null, null, null, null);
			
			Date tempStartDate = new Date();
			Date tempEndDate = new Date();
			if (cursor != null)
		        cursor.moveToFirst();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    try{
		    	tempStartDate = formatter.parse(cursor.getString(6));
		    	tempEndDate = formatter.parse(cursor.getString(7));
		    }catch (ParseException e ) {
		        e.printStackTrace();
		    }
		 
		    Student student = new Student(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), 
		    		cursor.getString(4), cursor.getString(5), tempStartDate, tempEndDate, cursor.getString(8), 
		    		cursor.getString(9), cursor.getInt(10), cursor.getString(11), cursor.getInt(12), cursor.getInt(13),
		    		cursor.getInt(14),cursor.getInt(15),cursor.getString(16));
		    
		    cursor.close();
		    // return contact
		    return student;
			
		}
		    
		  
		public ArrayList<Student> getAllStudents() 
		{
			ArrayList<Student> students = new ArrayList<Student>();
			
			Cursor cursor = database.query(MySQLiteHelper.TABLE_STUDENTS,
			    allColumns, null, null, null, null, null);
			
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) 
			{
			  Student student = cursorToStudent(cursor);
			  students.add(student);
			  cursor.moveToNext();
			}
			// make sure to close the cursor
		    cursor.close();
		    return students;
		}
	  
		public Family createFamily(String fname, String lname, String email, String gender, 
				  String phone, String sDate, String eDate, String address, String state, 
				  int zip, String allergies, int dogPet, int catPet, int famSize, int smoke, String password) 
		{
		    
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
		    values.put(MySQLiteHelper.COLUMN_FAMSIZE, famSize);
		    values.put(MySQLiteHelper.COLUMN_SMOKE, smoke);
		    values.put(MySQLiteHelper.COLUMN_PASSWORD, password);
		    
		    long insertId = database.insert(MySQLiteHelper.TABLE_FAMILIES, null,
		        values);
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_FAMILIES,
		        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Family newFamily = cursorToFamily(cursor);
		    cursor.close();
		    return newFamily;
	    }
		
		public int updateFamily(Family family) 
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_FNAME, family.firstName);
		    values.put(MySQLiteHelper.COLUMN_LNAME, family.lastName);
		    values.put(MySQLiteHelper.COLUMN_EMAIL, family.emailAddress);
		    values.put(MySQLiteHelper.COLUMN_GENDER, family.gender);
		    values.put(MySQLiteHelper.COLUMN_PHONE, family.phone);
		    String sDate_to_string = formatter.format(family.startDate);
		    values.put(MySQLiteHelper.COLUMN_START_DATE, sDate_to_string);
		    String eDate_to_string = formatter.format(family.endDate);
		    values.put(MySQLiteHelper.COLUMN_END_DATE, eDate_to_string);
		    values.put(MySQLiteHelper.COLUMN_ADDRESS, family.address);
		    values.put(MySQLiteHelper.COLUMN_STATE, family.state);
		    values.put(MySQLiteHelper.COLUMN_ZIP, family.zipCode);
		    values.put(MySQLiteHelper.COLUMN_ALLERGIES, family.allergies);
		    int dog = 0;
		    if(family.dogPet)
		    {
		    	dog = 1;
		    }
		    values.put(MySQLiteHelper.COLUMN_DOG_PET_PREF, dog);
		    int cat = 0;
		    if(family.dogPet)
		    {
		    	cat = 1;
		    }
		    values.put(MySQLiteHelper.COLUMN_CAT_PET_PREF, cat);
		    values.put(MySQLiteHelper.COLUMN_FAMSIZE, family.famSize);
		    int smoke = 0;
		    if(family.smoke)
		    {
		    	smoke = 1;
		    }
		    values.put(MySQLiteHelper.COLUMN_SMOKE, smoke);
		    values.put(MySQLiteHelper.COLUMN_PASSWORD, family.password);
		 
		    // updating row
		    return database.update(MySQLiteHelper.TABLE_STUDENTS, values, MySQLiteHelper.COLUMN_ID + " = " + family._id,null);
		}
		
		
		public ArrayList<Family> getAllFamilies() 
		{
			ArrayList<Family> families = new ArrayList<Family>();
			
			Cursor cursor = database.query(MySQLiteHelper.TABLE_FAMILIES,
			    allColumns, null, null, null, null, null);
			
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
			  Family family = cursorToFamily(cursor);
			  families.add(family);
			  cursor.moveToNext();
			}
			// make sure to close the cursor
		    cursor.close();
		    return families;
		}
		
		public ArrayList<Family> getSearchFamilies(Boolean[] preferences, Student student)
		{
			ArrayList<Family> families = new ArrayList<Family>();
			
			Cursor cursor = database.query(MySQLiteHelper.TABLE_FAMILIES,
				    allColumns, null, null, null, null, null);
			
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) 
			{
			  Family family = cursorToFamily(cursor);
			  families.add(family);
			  cursor.moveToNext();
			}
			
			ArrayList<Family> genderFamilies = new ArrayList<Family>();
			
			if(preferences[0])
			{	
				for(Family fam: families)
				{
					if(fam.gender.equals(student.gender))
					{
						genderFamilies.add(fam);
					}
	
				}
			}
			else
			{
				genderFamilies = families;
			}
			
			ArrayList<Family> dogPrefFamilies = new ArrayList<Family>();
			
			if(preferences[1])
			{
				for(Family fam: dogPrefFamilies)
				{
					if(fam.dogPet == student.dogPet)
					{
						dogPrefFamilies.add(fam);
					}
	
				}
			}
			else
			{
				dogPrefFamilies = genderFamilies;
			}
			
			ArrayList<Family> catPrefFamilies = new ArrayList<Family>();
			
			if(preferences[2])
			{
				for(Family fam: dogPrefFamilies)
				{
					if(fam.catPet == student.catPet)
					{
						catPrefFamilies.add(fam);
					}
	
				}
			}
			else
			{
				catPrefFamilies = dogPrefFamilies;
			}
			
			ArrayList<Family> famSizeFamilies = new ArrayList<Family>();

			if(preferences[3])
			{
				for(Family fam: catPrefFamilies)
				{
					if(fam.famSize <= student.famSize)
					{
						famSizeFamilies.add(fam);
					}
	
				}
			}
			else
			{
				famSizeFamilies = catPrefFamilies;
			}
			
			ArrayList<Family> smokeFamilies = new ArrayList<Family>();

			if(preferences[4])
			{
				for(Family fam: famSizeFamilies)
				{
					if(fam.smoke == student.smoke)
					{
						smokeFamilies.add(fam);
					}
	
				}
			}
			else
			{
				smokeFamilies = famSizeFamilies;
			}
			
			return smokeFamilies;
		}
		
		
		public ArrayList<Family> getWizzardFamilies(Student student)
		{
			ArrayList<Family> families = new ArrayList<Family>();
			
			Cursor cursor = database.query(MySQLiteHelper.TABLE_FAMILIES,
				    allColumns, null, null, null, null, null);
			
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) 
			{
			  Family family = cursorToFamily(cursor);
			  families.add(family);
			  cursor.moveToNext();
			}
			
			ArrayList<Family> wizzardFamilies = new ArrayList<Family>();
			
			for(Family fam: families)
			{
				if(fam.gender.equals(student.gender))
				{
					fam.wizzardScore += 1;
				}
				if(fam.dogPet == student.dogPet)
				{
					fam.wizzardScore += 1;
				}
				if(fam.catPet == student.catPet)
				{
					fam.wizzardScore += 1;
				}
				if(fam.famSize <= student.famSize)
				{
					int diff = student.famSize - fam.famSize;
					if(diff == 0)
					{
						fam.wizzardScore += 3;
					}
					if(diff == 1)
					{
						fam.wizzardScore += 2;
					}
					if(diff >= 2)
					{
						fam.wizzardScore += 1;
					}
				}
				if(fam.smoke == student.smoke)
				{
					fam.wizzardScore += 1;
				}
			}
			
			
			Collections.sort(families, COMPARATOR);
			
			for(int i = 0; i < 5; i++)
			{
				if(families.get(i) != null)
				{
					wizzardFamilies.add(families.get(i));
				}
				else
				{
					break;
				}
				
			}
						
			return wizzardFamilies;
		}
		  
		private Student cursorToStudent(Cursor cursor) {
			    
				Student student = new Student();
			    student._id = cursor.getInt(0);
			    student.firstName = cursor.getString(1);
			    student.lastName = cursor.getString(2);
			    student.gender = cursor.getString(3);
			    student.phone = cursor.getString(4);
			    student.emailAddress = cursor.getString(5);
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    try{
			    student.startDate = formatter.parse(cursor.getString(6));
			    student.endDate = formatter.parse(cursor.getString(7));
			    }catch (ParseException e ) {
			        e.printStackTrace();
			    }
			    student.address = cursor.getString(8);
			    student.state = cursor.getString(9);
			    student.zipCode = cursor.getInt(10);
			    student.allergies = cursor.getString(11);
			    if(cursor.getInt(12) == 1)
			    {
			    	student.dogPet = true;
			    }
			    else
			    {
			    	student.dogPet = false;
			    }
			    if(cursor.getInt(13) == 1)
			    {
			    	student.catPet = true;
			    }
			    else
			    {
			    	student.catPet = false;
			    }
			    student.famSize = cursor.getInt(14);
			    if(cursor.getInt(15) == 0)
			    {
			    	student.smoke = false;
			    }
			    else
			    {
			    	student.smoke = true;
			    }
			    student.password = cursor.getString(16);
			    /**
			    System.out.println("ID:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID));
			    System.out.println("fname:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_FNAME));
			    System.out.println("lname:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_LNAME));
			    System.out.println("gender:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_GENDER));
			    System.out.println("phone:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_PHONE));
			    System.out.println("email:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_EMAIL));
			    System.out.println("sDate:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_START_DATE));
			    System.out.println("eDate:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_END_DATE));
			    System.out.println("address:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ADDRESS));
			    System.out.println("state:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_STATE));
			    System.out.println("zip:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ZIP));
			    System.out.println("allegies:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ALLERGIES));
			    System.out.println("dodPref:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_DOG_PET_PREF));
			    System.out.println("catPref:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_CAT_PET_PREF));
			    System.out.println("famSize:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_FAMSIZE));
			   */
			    return student;
			  }

		private Family cursorToFamily(Cursor cursor) 
		{
		    
			Family family = new Family();
			family._id = cursor.getInt(0);
			family.firstName = cursor.getString(1);
			family.lastName = cursor.getString(2);
			family.gender = cursor.getString(3);
			family.phone = cursor.getString(4);
			family.emailAddress = cursor.getString(5);
			family.startDate = cursor.getString(6);
			family.endDate = cursor.getString(7);
			family.address = cursor.getString(8);
		    family.state = cursor.getString(9);
		    family.zipCode = cursor.getInt(10);
		    family.allergies = cursor.getString(11);
		    if(cursor.getInt(12) == 1)
		    {
		    	family.dogPet = true;
		    }
		    else
		    {
		    	family.dogPet = false;
		    }
		    if(cursor.getInt(13) == 1)
		    {
		    	family.catPet = true;
		    }
		    else
		    {
		    	family.catPet = false;
		    }
		    family.famSize = cursor.getInt(14);
		    if(cursor.getInt(15) == 0)
		    {
		    	family.smoke = false;
		    }
		    else
		    {
		    	family.smoke = true;
		    }
		    family.password = cursor.getString(16);
		    /**
		    System.out.println("ID:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID));
		    System.out.println("fname:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_FNAME));
		    System.out.println("lname:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_LNAME));
		    System.out.println("gender:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_GENDER));
		    System.out.println("phone:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_PHONE));
		    System.out.println("email:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_EMAIL));
		    System.out.println("sDate:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_START_DATE));
		    System.out.println("eDate:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_END_DATE));
		    System.out.println("address:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ADDRESS));
		    System.out.println("state:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_STATE));
		    System.out.println("zip:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ZIP));
		    System.out.println("allegies:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_ALLERGIES));
		    System.out.println("dodPref:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_DOG_PET_PREF));
		    System.out.println("catPref:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_CAT_PET_PREF));
		    System.out.println("famSize:" + cursor.getColumnIndex(MySQLiteHelper.COLUMN_FAMSIZE));
		   */
		    return family;
		  }
		
		private static Comparator<Family> COMPARATOR = new Comparator<Family>()
			    {
			    // This is where the sorting happens.
			        public int compare(Family f1, Family f2)
			        {
			            return f1.wizzardScore - f2.wizzardScore;
			        }
			    };
		
}


