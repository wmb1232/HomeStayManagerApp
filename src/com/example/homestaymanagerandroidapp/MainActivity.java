package com.example.homestaymanagerandroidapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Facebook;

public class MainActivity extends FragmentActivity {

	private RadioGroup rgOpinion;
	Global status;

	private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        
        if (savedInstanceState == null) {
        	mainFragment = new MainFragment();
            getSupportFragmentManager()
            .beginTransaction()
            .add(android.R.id.content,mainFragment)
            .commit();
        }
        else{
            mainFragment = (MainFragment) getSupportFragmentManager()
            .findFragmentById(android.R.id.content);
        }
      // start Facebook Login
       Session.openActiveSession(this, true, new Session.StatusCallback() {
        // callback when session changes state
              @Override
              public void call(Session session, SessionState state, Exception exception) {
              }
        });
       
       
       /*
       Session session = Session.getActiveSession();
       if (session != null && session.isOpened()) {
			startActivity(new Intent(MainActivity.this,StudentMainMenu.class));
       } else {
	
       }*/
        
       
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_student:
                if (checked)
                	Global.EsEstudiante = true;
                break;
            case R.id.radio_family:
                if (checked)
                	Global.EsFamilia = true;
                break;
        }
    }
    
    
    public void goToRegister(View v){
		  startActivity(new Intent(MainActivity.this,EmailLogin.class));

    }
    
	public void goMainMenu(View v)
	{
				
		HomeStayDataSource datasourc;
		
		EditText text = (EditText)findViewById(R.id.editTexUserName);
		String userEntered = text.getText().toString();
		
		EditText text2 = (EditText)findViewById(R.id.editTextPassword);
		String passEntered = text2.getText().toString();
		
		datasourc = new HomeStayDataSource(this);
	 	datasourc.open();	
	 	
	    boolean exists = false;
	    
	    if(userEntered.equals("")){
			new AlertDialog.Builder(this).setTitle("Error").setMessage("The email cannot be blank").setNeutralButton("Close", null).show();
			return;
	    }
	    if(passEntered.equals("")){
			new AlertDialog.Builder(this).setTitle("Error").setMessage("The password cannot be blank").setNeutralButton("Close", null).show();
			return;
	    }
	    
	    ArrayList<Student> list1;
	    List<Family> list2;
	    if(Global.EsEstudiante || true){
	    	list1 = datasourc.getAllStudents();	
	    	
	    	for(Student s : list1){
	    		if(s.emailAddress.equals(userEntered) && s.password.equals(passEntered)){
	    			exists = true;
	    			startActivity(new Intent(MainActivity.this,StudentMainMenu.class));
	    		}
	    	}	
	    }
	    if(Global.EsFamilia || true){
	    	list2 = datasourc.getAllFamilies();
	    	for(Family s : list2){
	    		if(s.emailAddress.equals(userEntered) && s.password.equals(passEntered)){
	    			exists = true;
	    			startActivity(new Intent(MainActivity.this,StudentMainMenu.class));
	    		}
	    	}	       	
	    }
	    
	    if(!exists){
			AlertDialog.Builder builder2=new AlertDialog.Builder(MainActivity.this);
			  builder2.setMessage("Error in Login");
			  builder2.setPositiveButton("Register",new DialogInterface.OnClickListener() {

			  @Override
			  public void onClick(DialogInterface dialog, int which) {
				  startActivity(new Intent(MainActivity.this,EmailLogin.class));
			  }

			  });

			  builder2.setNegativeButton("Retry", new DialogInterface.OnClickListener() {

			@Override
			  
			public void onClick(DialogInterface dialog, int which) {

			  // TODO Auto-generated method stub
			  //Toast.makeText(getApplicationContext(), "U Clicked Cancel ", Toast.LENGTH_LONG).show();

			  }

			  });

			  builder2.show();
	    	
	    }
	    else{
	    	startActivity(new Intent(MainActivity.this,StudentMainMenu.class));
	    }

		  
		//startActivity(new Intent(MainActivity.this,StudentMainMenu.class));
		
	}
    
    public void setupLoginButton(View v)
    { 
    	startActivity(new Intent(MainActivity.this,EmailLogin.class));

    }
    public void LoginButton(View v) {
    	startActivity(new Intent(MainActivity.this,Login.class));

    }
    



}



