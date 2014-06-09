package com.example.homestaymanagerandroidapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class EmailLogin extends Activity {
	public HomeStayDataSource datasource;
	public ArrayList<Student> values;
	public ArrayList<Family> valuesFam;
	public boolean isFamily = false;
	public boolean isStudent = false;
	public boolean studentExist = false;
	public boolean familyExist = false;
	int dialog_id =1;
	int start_yr, start_day, start_month,end_yr,end_day,end_month;
	String weekDay;
	SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_login);
		Calendar today = Calendar.getInstance();
		weekDay = dayFormat.format(today.getTime());
		
		start_yr= today.get(Calendar.YEAR);
		start_day = today.get(Calendar.DAY_OF_MONTH);
		start_month = today.get(Calendar.MONTH);
		end_yr= start_yr;
		end_day=start_day;
		end_month=start_month;
		
		
		setContentView(R.layout.activity_email_login);
		TextView textView = (TextView) findViewById(R.id.startDate);
		TextView textView2 = (TextView) findViewById(R.id.endDate);
		
		textView.setText( weekDay + ", " + start_month + "/" + start_day + "/" + start_yr);
		textView2.setText( weekDay + ", " + end_month + "/" + end_day + "/" + end_yr);

	    datasource = new HomeStayDataSource(this);
	    datasource.open();
	   
       final Button button = (Button) findViewById(R.id.btnSubmit);
       button.setOnClickListener(new View.OnClickListener() {
           
    	   public void onClick(View v) {
    		     
        	   System.out.println("ENTER BUTTON");
            final EditText firstName = (EditText) findViewById(R.id.firstName);
       	    final String fname = firstName.getText().toString();
       	    
       	    final EditText lastName = (EditText) findViewById(R.id.editText2);
       	    final String lname = lastName.getText().toString();

       	    final EditText emaiL = (EditText) findViewById(R.id.editText3);
       	    final String email = emaiL.getText().toString();

       	    final EditText phoneN = (EditText) findViewById(R.id.editText4);
       	    final String phone = phoneN.getText().toString();
       	    
       	    final EditText mailingAddress = (EditText) findViewById(R.id.editText5);
       	    final String mail = mailingAddress.getText().toString();
       	    
       	    final String startDate = String.valueOf(start_day) + "/" + String.valueOf(start_month) + "/" + String.valueOf(start_yr);
       	    
       	    final String endDate = String.valueOf(end_day) + "/" + String.valueOf(end_month) + "/" + String.valueOf(end_yr);
       	    
     	    final String gender = "Male";
    	    final String state = "CA";
    	    final int zip = 5121;
    	    final int dogPet = 0;
    	    final int catPet = 1;
    	    final String allergies = "NONE";
    	              
            values = new ArrayList<Student>();
            values = datasource.getAllStudents();  
            valuesFam = new ArrayList<Family>();	
            valuesFam = datasource.getAllFamilies();
   
   	    	Student st;
   	    	Family fm;
   	    	datasource.createStudent("fname", "lname", "email", "gender", 
   					"phone", "startDate", "endDate", "mail", 
	   					"state", 3423, "allergies", 4, 2,2,1,"password");
   	    	datasource.createFamily("fname", "lname", "email", "gender", 
   					"phone", "startDate", "endDate", "mail", 
	   					"state", 3423, "allergies", 4, 2,2,1,"password");
   	    	
   	    	if(isStudent == true) {
   	    		
   	        for(int i = 0; i < values.size(); i++)	{
   	      	  if(fname.equals(values.get(i).firstName)) {
			     Toast.makeText(getApplicationContext(), 
                         "Account associated with this email already exist", Toast.LENGTH_LONG).show();
			     studentExist = true;
   	      	 }
            }
   	        if(studentExist == false) {

          	st = datasource.createStudent(fname, lname, email, gender, 
        		   					phone, startDate, endDate, mail, 
        			   					state, zip, allergies, dogPet, catPet,2,1,"password");
          	
        	 
     	    ArrayList<Student> values = new ArrayList<Student>();

     	    values = datasource.getAllStudents();
     	    
 		
     	        	System.out.println("***********get**************");
     	        	System.out.println(values.get(0).firstName);
     	        	System.out.println("*************endget*************");
     	        	
   	        }
     	  // go to main menu if new account created  
   	     if(fname != null && studentExist == false)
   	    	startActivity(new Intent(EmailLogin.this,StudentMainMenu.class));
   	     
   	        studentExist = false;
     	 	        	
           }
    	   else if(isFamily == true){ 
  	    		
      	        for(int i = 0; i < valuesFam.size(); i++)	{
      	        	System.out.println(valuesFam.get(i).firstName);
      	      	  if(fname.equals(valuesFam.get(i).firstName)) {
   			     Toast.makeText(getApplicationContext(), 
                            "Account associated with this email already exist", Toast.LENGTH_LONG).show();
   			  familyExist = true;
      	      	 }
               }
      	        if(familyExist == false) {
    
        	 
               fm = datasource.createFamily(fname, lname, email, gender, 
    					phone, startDate, endDate, mail, 
   	   					state, zip, allergies, dogPet, catPet, 2,1,"password");
      	        }
        	    
      	     if(fname != null && familyExist == false)
      	    	startActivity(new Intent(EmailLogin.this,StudentMainMenu.class));
      	     
      	      familyExist = false;
        	 	        	  		   
    		   
    	   }
    	   
    	   }
       });
      
       
	}
	/**
	public void studentOrFamily(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	 
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio_student:
	            if (checked){
	               isFamily = true;
	               System.out.print("FAMILY");
	            }
	            break;
	        case R.id.radio2:
	            if (checked){
	               isStudent = true;
	               System.out.print("Student");
	            }
	            break;
	    }
	}
	*/
	
	
	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_email_login,
					container, false);
			return rootView;
		}
	}
	
	public void goMainMenu(View v)
	{
		startActivity(new Intent(EmailLogin.this,StudentMainMenu.class));
		
	}

	public ArrayList<Student> getValues() {
		// TODO Auto-generated method stub
		return values;
	}
	///////////////////////////////////CREATE DATE SET DIALOG////////////////////////////
	public void startDateDialog(View v)
	{
		dialog_id=1;
		showDialog(dialog_id);
		
	}
	
	public void endDateDialog(View v)
	{
		dialog_id=2;
		showDialog(dialog_id);
	}
	
	protected Dialog onCreateDialog(int id)
	{
		switch (id)
		{
		case 1:
			return new DatePickerDialog(this, mDateSetListener, start_yr, start_month, start_day);
		case 2:
			return new DatePickerDialog(this, nDateSetListener, end_yr, end_month, end_day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			start_yr = year;
			start_month= monthOfYear;
			start_day=dayOfMonth;
			setContentView(R.layout.activity_email_login);
			TextView textView = (TextView) findViewById(R.id.startDate);
			textView.setText( weekDay + ", " + start_month + "/" + start_day + "/" + start_yr);
			
			TextView textView2 = (TextView) findViewById(R.id.endDate);
			textView2.setText( weekDay + ", " + end_month + "/" + end_day + "/" + end_yr);
			
		}
	};
	private DatePickerDialog.OnDateSetListener nDateSetListener = new DatePickerDialog.OnDateSetListener() {
			
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
				// TODO Auto-generated method stub
			end_yr = year;
			end_month= monthOfYear;
			end_day=dayOfMonth;
			setContentView(R.layout.activity_email_login);
			TextView textView = (TextView) findViewById(R.id.startDate);
			textView.setText( weekDay + ", " + start_month + "/" + start_day + "/" + start_yr);
			
			TextView textView2 = (TextView) findViewById(R.id.endDate);
			textView2.setText( weekDay + ", " + end_month + "/" + end_day + "/" + end_yr);
			}
		///////////END SET DATE DIALOG/////////////////
	};
			

}
