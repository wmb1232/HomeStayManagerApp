package com.example.homestaymanagerandroidapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.AlertDialog;
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

    private int cat = 0;
    private int dog = 0;
    private int smoke = 0;

	
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

        	
           	
            final EditText firstName = (EditText) findViewById(R.id.edittext_firstName);

       	    final String fname = firstName.getText().toString();
       	    if(fname.equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The first name cannot be blank").setNeutralButton("Close", null).show(); 
    			return;
       	    }
       	           	    
       	    
       	    final EditText lastName = (EditText) findViewById(R.id.editTextPassword);
       	    final String lname = lastName.getText().toString(); 	    
       	    if(lname.equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The last name cannot be blank").setNeutralButton("Close", null).show(); 
    			return;
       	    }

       	    final EditText emaiL = (EditText) findViewById(R.id.editText_email);
       	    final String email = emaiL.getText().toString();
       	    if(email.equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The email cannot be blank").setNeutralButton("Close", null).show(); 
    			return;
       	    }

       	    final EditText phoneN = (EditText) findViewById(R.id.editText_phon);
       	    final String phone = phoneN.getText().toString();
       	    if(phone.equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The phone number cannot be blank").setNeutralButton("Close", null).show(); 
    			return;
       	    }

       	    
       	    final EditText mailingAddress = (EditText) findViewById(R.id.editText_mailing);
       	    final String mail = mailingAddress.getText().toString();
       	    
       	    final String startDate = String.valueOf(start_day) + "/" + String.valueOf(start_month) + "/" + String.valueOf(start_yr);

       	    final String endDate = String.valueOf(end_day) + "/" + String.valueOf(end_month) + "/" + String.valueOf(end_yr);

       	    final EditText ate = (EditText) findViewById(R.id.editText_gender);
    	    final String gender = ate.getText().toString();
       	    if(gender.equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The gender cannot be blank").setNeutralButton("Close", null).show(); 
    			return;
       	    }
    	    
    	    final EditText zp = (EditText) findViewById(R.id.editTexUserName);
    	    int zip;
    	    if(zp.getText().toString().equals("")){
    	    	zip = 0;
    	    }
    	    else{
    	    	zip = Integer.parseInt(zp.getText().toString());     	
    	    	
    	    }
 	    
    	    final EditText famsize = (EditText) findViewById(R.id.editText_famsize);
    	    if(famsize.getText().toString().equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The family size cannot be blank").setNeutralButton("Close", null).show(); 
    			return;
    	    }
    	    
    	    final int familySizee = Integer.parseInt(famsize.getText().toString());
     	  
    	    final String state = "CA";
    	    
    	    final EditText allerg = (EditText) findViewById(R.id.editText_eller); 	    
    	    final String allergies = allerg.getText().toString();
    	    
    	    
    	    final EditText enteredPass = (EditText) findViewById(R.id.editTextPass); 	    
    	    final String passWord = enteredPass.getText().toString();
    	    
    	    if(passWord.equals("")){
    			new AlertDialog.Builder(EmailLogin.this).setTitle("Error").setMessage("The password cannot be blank").setNeutralButton("Close", null).show(); 
    			return;   	
    	    }
    	    
    	               
            values = new ArrayList<Student>();
            values = datasource.getAllStudents();  
            valuesFam = new ArrayList<Family>();	
            valuesFam = datasource.getAllFamilies();
   
   	    	Student st;
   	    	Family fm;
   	    	System.out.println("Es Estuduante "+ Global.EsEstudiante);
   	    	System.out.println("Es familia "+ Global.EsFamilia);
//************b********ADD STUDENT ***************************
   	    	
   	    	if(Global.EsEstudiante == true) {	
   	    		for(int i = 0; i < values.size(); i++)	{
   	    			if(email.equals(values.get(i).emailAddress)) {
   	    				Toast.makeText(getApplicationContext(), 
      					"Account associated with this email already exist", Toast.LENGTH_LONG).show();   	    	
   	    			    //	Global.stu_id = values.get(i)._id;
   	    				studentExist = true;
   	    			 break;
   	    			}
   	    		}
       
   	    		if(studentExist == false) {

   	    			st = datasource.createStudent(fname, lname, email, gender, 
        		   					phone, startDate, endDate, mail, 
        		   					state, zip, allergies, dog, cat,2,1,passWord);        				   					
   	    			
   	    			studentExist = true;
   	    			//Global.stu_id = values.get(values.size())._id;


     	        	System.out.println("***********stu**************");
     	        	System.out.println(fname+ "\n"+ lname+ " \n" +email+ "\n" +gender+ "\n"+ 
		   				   phone+ "\n" +startDate+ " \n" +endDate+ " \n" +mail+ " \n" 
		   				   + state+ " \n"+ zip+ " \n" +allergies+ " \n" +dog+ " \n" +cat+ " \n"+familySizee+
		   				   " \n"+smoke+ " \n"+passWord);
     	        	System.out.println(	Global.stu_id );
     	        	System.out.println("*************endest*************");
     	        	
   	    			if(email != null &&studentExist == true )
   	    				startActivity(new Intent(EmailLogin.this,StudentMainMenu.class)); 	
   	       }
     	  // go to main menu if new account created   	     
     	 	        	
        }
   	//*******************END ADD STUDENT ***************************	
   	    	
    	   if(Global.EsFamilia == true){  	    		
      	        for(int i = 0; i < valuesFam.size(); i++)	{
      	        	//System.out.println(valuesFam.get(i).firstName);
      	      	  if(email.equals(valuesFam.get(i).emailAddress)) {
   			     Toast.makeText(getApplicationContext(), 
                            "Account associated with this email already exist", Toast.LENGTH_LONG).show();
   			  //  Global.fam_id =  valuesFam.indexOf(valuesFam.get(i));
   			     familyExist = true;
   			     break;
      	      	 }
                }
      	        if(familyExist == false) {
        	 
      	        	fm = datasource.createFamily(fname, lname, email, gender, 
    					phone, startDate, endDate, mail, 
   	   					state, zip, allergies, dog, cat, familySizee,smoke,passWord);     
      	        	familyExist = false;               
      	        	// Global.fam_id = valuesFam.get(valuesFam.size())._id;
             
      	        	System.out.println("***********getfam**************");
      	        	System.out.println(fname+ "\n"+ lname+ " \n" +email+ "\n" +gender+ "\n"+ 
      				   phone+ "\n" +startDate+ " \n" +endDate+ " \n" +mail+ " \n" 
      				   + state+ " \n"+ zip+ " \n" +allergies+ " \n" +dog+ " \n" +cat+ " \n"+familySizee+ " \n"+smoke+ " \n"+"password");
      	        	System.out.println(	Global.fam_id);
      	        	System.out.println("*************endgetfam*************");
        	      	  
      	        	if(email != null && familyExist == false)
      	        		startActivity(new Intent(EmailLogin.this,FamilyMainMenu.class)); 	
      	        
      	        }
    	     }
  
    	   }
       });
      
       
	}

    public void onRadioButtonClickedDogCat(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_Dog:
                if (checked)
                dog= 1;
                break;
            case R.id.radioButton_cat:
                if (checked)
                	cat = 1;
                break;
            case R.id.radioButton_smoke:
                if (checked)
                	smoke =1;
                break;
        }
    }
	
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
