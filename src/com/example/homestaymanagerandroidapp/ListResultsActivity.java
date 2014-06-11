package com.example.homestaymanagerandroidapp;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class ListResultsActivity extends ActionBarActivity {

	int idArray[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_results);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		ListView  listView = (ListView) findViewById(R.id.listView1);
		
		 String [] strs = new String[Global.familyList.size()];
		 int length = strs.length;
		 idArray= new int[length];
		 
		 
		 for(int i = 0; i < strs.length; i++)	 {
			 strs[i] =(Global.familyList.get(i)._id +" "+Global.familyList.get(i).firstName+" " + Global.familyList.get(i).lastName);
			 idArray[i] = (Global.familyList.get(i)._id);
			 System.out.println(strs[i]);
		 }
		
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListResultsActivity.this,
	    android.R.layout.simple_list_item_1, android.R.id.text1, strs); 
	     
	     // Assign adapter to ListView
       listView.setAdapter(adapter); 
       
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       	
       	  public void onItemClick(AdapterView<?> parentAdapter, View view, int position,long id) {
       		  
       /**		  AlertDialog.Builder adb = new AlertDialog.Builder(
       				  ViewFam.this);
       				  adb.setTitle("Family Information");
       				  adb.setMessage("Selected Item is = "
       				  + listView.getItemAtPosition(position));
       				  adb.setPositiveButton("Ok", null);
       				  adb.show(); 
       	*/	 // TextView clickedView = (TextView) view;
       				  
			  Global.currentFam = Global.familyList.get(idArray[position]);
       		 				   
       		  startActivity(new Intent(ListResultsActivity.this,View_specific_family.class));
       		  
       		  
       	         //Toast.makeText(ViewFam.this, "Item with id ["+id+"] - Position ["+position+"]"
       	         	//	+ " - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
       	  }
       	
		});
       
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_results, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_list_results,
					container, false);
			return rootView;
		}
	}

}
