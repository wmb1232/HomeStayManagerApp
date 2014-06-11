package com.example.homestaymanagerandroidapp;
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
public class View_specific_family extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_specific_family);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//
		
		 String [] strs = new String[12];
		 
		 strs[0] = "Name: "  + Global.currentFam.firstName + " " + Global.currentFam.lastName;
		 strs[1] = "Address: " + Global.currentFam.address;
		 strs[2] = "State: " + Global.currentFam.state;
		 strs[3] = "Zip Code: " + Global.currentFam.zipCode;
		 strs[4] = "Dates: " + Global.currentFam.startDate + " - " + Global.currentFam.endDate;
		 strs[5] = "Family Size: " + Global.currentFam.famSize;
		 strs[6] = "Has Dog: " + Global.currentFam.dogPet;
		 strs[7] = "Has Cat: " + Global.currentFam.catPet;
		 strs[8] = "Email: " + Global.currentFam.emailAddress;
		 strs[9] = "Allergies: " + Global.currentFam.allergies;
		 strs[10] = "Phone: " + Global.currentFam.phone;
		 strs[11] = "Password: " + Global.currentFam.password;
		 final ListView  listView = (ListView) findViewById(R.id.listView1);
		 
		 if(strs != null){
			    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(View_specific_family.this,
			    	    android.R.layout.simple_list_item_1, android.R.id.text1, strs); 
			    	     
			    	     // Assign adapter to ListView
			            listView.setAdapter(adapter2); 			 
		 }

		 
		 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_specific_family, menu);
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
			View rootView = inflater.inflate(
					R.layout.fragment_view_specific_family, container, false);
			return rootView;
		}
	}
}