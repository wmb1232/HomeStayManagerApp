package com.example.homestaymanagerandroidapp;

import java.util.ArrayList;

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
import android.widget.ListView;
import android.os.Build;

public class ViewFam extends ActionBarActivity {
	HomeStayDataSource datasourc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_fam);
		
		 datasourc = new HomeStayDataSource(this);
		 datasourc.open();
		 
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	 	System.out.println("*************endviewFamiliesVIEFAM***************");
    	
		 ListView  listView = (ListView) findViewById(R.id.listView0);
		 ArrayList<Family> fm = new  ArrayList<Family>( );
	     fm = datasourc.getAllFamilies();
		 
		 String [] strs = new String[fm.size()];
		 
		 for(int i = 0; i < strs.length; i++)	 {
		 strs[i] =(fm.get(i)._id +" "+fm.get(i).firstName+" " + fm.get(i).lastName);
		 System.out.println(strs[i]);
		 }
					 
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewFam.this,
	    android.R.layout.simple_list_item_1, android.R.id.text1, strs); 
	     
	     // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
        
        System.out.println("*************endviewFamilies AFTER VIEFAM***************");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_fam, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_view_fam,
					container, false);
			return rootView;
		}
	}

}
