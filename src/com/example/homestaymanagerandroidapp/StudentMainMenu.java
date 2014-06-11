package com.example.homestaymanagerandroidapp;

import java.util.ArrayList;

import com.facebook.model.GraphUser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.Toast;

public class StudentMainMenu extends ActionBarActivity {
	HomeStayDataSource datasourc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_main_menu);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Button btn1 = (Button) findViewById(R.id.showFamilies);
		
		btn1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startActivity(new Intent(StudentMainMenu.this,ViewFam.class));
		    }
		     });
		
		Button btn2 = (Button) findViewById(R.id.editProfile);
		btn2.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startActivity(new Intent(StudentMainMenu.this,EmailLogin.class));
		    }
		     });
		
		/**
		System.out.println("*************endviewFamilies***************");
		 datasourc = new HomeStayDataSource(this);
		 datasourc.open();

		 // Get ListView object from xml
      
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Button btn1 = (Button) findViewById(R.id.favorites);
		
		btn1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startActivity(new Intent(MainMenu.this,ViewFam.class));
		    }
		     });
		    
		
		Button btn = (Button) findViewById(R.id.showFamilies);
		btn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	System.out.println("*************endviewFamilies2***************");
		    	
		 ListView  listView = (ListView) findViewById(R.id.listView1);
		 ArrayList<Student> as = new  ArrayList<Student>( );
	     as = datasourc.getAllStudents();
		 
		 String [] strs = new String[as.size()];
		 
		 for(int i = 0; i < strs.length; i++)	 
		 strs[i] =(as.get(i)._id +" "+as.get(i).firstName+" " + as.get(i).lastName);
					 
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainMenu.this,
	    android.R.layout.simple_list_item_1, android.R.id.text1, strs); 
	     
	     // Assign adapter to ListView
         listView.setAdapter(adapter); 
			
		    }
		});
		*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
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
	

	
	public void viewFamilies() {
		
	//	System.out.println("************viewFamilies****************");
		
	 ArrayList<Family> as = new  ArrayList<Family>( );
	 as = datasourc.getAllFamilies();
		
		//System.out.println(el.getValues().get(0).firstName);
		//System.out.println("*************endviewFamilies***************");
		
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
			View rootView = inflater.inflate(R.layout.fragment_main_menu,
					container, false);
			return rootView;
		}
	}
	
	public void gotoManual(View v){
    	startActivity(new Intent(StudentMainMenu.this,ManualSearchActivity.class));
	
	}

}
