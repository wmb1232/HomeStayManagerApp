package com.example.homestaymanagerandroidapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_login);

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
       	    
       	    final EditText sDate = (EditText) findViewById(R.id.editText6);
       	    final String startDate = sDate.getText().toString();
       	    
       	    final EditText eDate = (EditText) findViewById(R.id.editText7);
       	    final String endDate = eDate.getText().toString();
       	    
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


}
