package com.example.homestaymanagerandroidapp;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.os.Build;

public class ManualSearchActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_search);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manual_search, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_manual_search,
					container, false);
			return rootView;
		}
	}
	
	public void launchManSearch(View v){
		
		Boolean preferences[] = {false,false,false,false,false};
		
		preferences[0] = ((CheckBox) findViewById(R.id.checkBox1)).isChecked();
		preferences[1] = ((CheckBox) findViewById(R.id.checkBox2)).isChecked();
		preferences[2] = ((CheckBox) findViewById(R.id.checkBox3)).isChecked();
		preferences[3] = ((CheckBox) findViewById(R.id.checkBox4)).isChecked();
		preferences[4] = ((CheckBox) findViewById(R.id.checkBox5)).isChecked();
				
		HomeStayDataSource datasourc;
		
		datasourc = new HomeStayDataSource(this);
		datasourc.open();
		 		
		ArrayList<Student> s = datasourc.getAllStudents();
		Student stud = null;
		for(Student stu : s){
			if(stu._id == Global.stu_id){
				stud = stu;
				break;
			}
		}
		 
		Global.familyList = datasourc.getSearchFamilies(preferences, stud);
		
		System.out.println("SFUCK! + " +  preferences[0] + preferences[1] + preferences[2] + preferences[3] + preferences[4]);
		
		
	    if(Global.familyList != null){
			startActivity(new Intent(this,ListResultsActivity.class));	    	
	    }

		
			
	}

}
