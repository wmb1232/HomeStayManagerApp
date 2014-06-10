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
    private int cat = 0;
    private int dog = 0;
    private int smoke = 0;
	
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
            final EditText firstName = (EditText) findViewById(R.id.edittext_firstName);
       	    final String fname = firstName.getText().toString();
       	    
       	    final EditText lastName = (EditText) findViewById(R.id.edittextLastName);
       	    final String lname = lastName.getText().toString();

       	    final EditText emaiL = (EditText) findViewById(R.id.editText_email);
       	    final String email = emaiL.getText().toString();

       	    final EditText phoneN = (EditText) findViewById(R.id.editText_phon);
       	    final String phone = phoneN.getText().toString();
       	    
       	    final EditText mailingAddress = (EditText) findViewById(R.id.editText_mailing);
       	    final String mail = mailingAddress.getText().toString();
       	    
       	    final EditText sDate = (EditText) findViewById(R.id.editText_start);
       	    final String startDate = sDate.getText().toString();
       	    
       	    final EditText eDate = (EditText) findViewById(R.id.editText_end);
       	    final String endDate = eDate.getText().toString();
       	    
       	    final EditText ate = (EditText) findViewById(R.id.editText_gender);
    	    final String gender = ate.getText().toString();
    	    
    	    final EditText zp = (EditText) findViewById(R.id.editText_zip);
       	    final int zip = Integer.parseInt(zp.getText().toString());     	    
 	    
    	    final EditText famsize = (EditText) findViewById(R.id.editText_famsize);
    	    final int familySizee = Integer.parseInt(famsize.getText().toString());
     	  
    	    final String state = "CA";
    	    
    	    final EditText allerg = (EditText) findViewById(R.id.editText_eller);
    	    final String allergies = allerg.getText().toString();
             
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
   	    				Global.stu_id = values.get(i)._id;
   	    				studentExist = true;
   	    			 break;
   	    			}
   	    		}
       
   	    		if(studentExist == false) {

   	    			st = datasource.createStudent(fname, lname, email, gender, 
        		   					phone, startDate, endDate, mail, 
        			   					state, zip, allergies, dog, cat,familySizee,smoke,"password");
   	    			
   	    			studentExist = true;
   	    			Global.stu_id = values.get(values.size()-1)._id;
   	        	
   	    			ArrayList<Student> values = new ArrayList<Student>();

     	        	System.out.println("***********stu**************");
     	        	System.out.println(fname+ "\n"+ lname+ " \n" +email+ "\n" +gender+ "\n"+ 
		   				   phone+ "\n" +startDate+ " \n" +endDate+ " \n" +mail+ " \n" 
		   				   + state+ " \n"+ zip+ " \n" +allergies+ " \n" +dog+ " \n" +cat+ " \n"+familySizee+ " \n"+smoke+ " \n"+"password");
     	        	System.out.println(	Global.stu_id );
     	        	System.out.println("*************endest*************");
     	        	
   	    			if(email != null)
   	    				finish();
    	
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
   			  Global.fam_id =  valuesFam.indexOf(valuesFam.get(i));
   			     familyExist = true;
   			     break;
      	      	 }
                }
      	        if(familyExist == false) {
        	 
               fm = datasource.createFamily(fname, lname, email, gender, 
    					phone, startDate, endDate, mail, 
   	   					state, zip, allergies, dog, cat, familySizee,smoke,"password");     
               familyExist = false;               
               Global.fam_id = valuesFam.get(valuesFam.size()-1)._id;
             
             	System.out.println("***********getfam**************");
            	System.out.println(fname+ "\n"+ lname+ " \n" +email+ "\n" +gender+ "\n"+ 
      				   phone+ "\n" +startDate+ " \n" +endDate+ " \n" +mail+ " \n" 
      				   + state+ " \n"+ zip+ " \n" +allergies+ " \n" +dog+ " \n" +cat+ " \n"+familySizee+ " \n"+smoke+ " \n"+"password");
            	System.out.println(	Global.fam_id);
            	System.out.println("*************endgetfam*************");
        	   
      	        
       	      if(email != null)
       	    	finish();
      	        
      	        }
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


}
