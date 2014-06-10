package com.example.homestaymanagerandroidapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homestaymanagerandroidapp.R;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class MainFragment extends Fragment {
	
	EmailLogin el;
	//Global gb;

    public   HomeStayDataSource datasource;
	public ArrayList<Student> values;
	public ArrayList<Family> valuesFam;
	public boolean isFamily = false;
	public boolean isStudent = false;
	public boolean studentExist = false;
	ImageView tv1;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    
	    authButton.setReadPermissions(Arrays.asList("user_birthday","public_profile","email","user_about_me",
	    		"user_hometown","user_interests", "user_photos", "user_religion_politics"));
	    return view;
	  
	}
	private static final String TAG = "MainFragment";
	public MainActivity StudentOrFamily = new MainActivity() ;
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        
	        Request.newMeRequest(session, new Request.GraphUserCallback() {    		
				@Override
			public void onCompleted(GraphUser user, Response response) {
///*************[******** ADD FAMILY OR STUDENT TO DATA BASE *********************************
	           //     String facebookId = userProfile.get(user.getId()).toString();
	           //     userProfilePictureView.setProfileId(facebookId);
		
			 if(Global.EsEstudiante == true) {
				  System.out.print("NO MAMEZZZZ");
			  	//  System.out.print( StudentOrFamily.EsFamilia);
				  System.out.print("NO MAMEZZZZ");
				  
		            values = new ArrayList<Student>();	
		            values = datasource.getAllStudents();
		            
		            Student st;
		         //  values.get(0)._id = Global.stu_id; 
		            st = datasource.createStudent(user.getFirstName(), user.getLastName(), 
		            		user.getProperty("email").toString(), "gender", 
	    					"phone", "startDate", "endDate", "mail", 
	   	   					"state", 95442, "allergies", 2, 2,2,1,"password");			  
			     startActivity(new Intent(getActivity(),StudentMainMenu.class));
			     Global.EsEstudiante = false;
			 }
			 else if(Global.EsFamilia == true){
					  	
		            valuesFam = new ArrayList<Family>();	
		            valuesFam = datasource.getAllFamilies();
		            Family fm;
		            fm = datasource.createFamily(user.getFirstName(), user.getLastName(), 
		            		user.getProperty("email").toString(), "gender", 
	    					"phone", "startDate", "endDate", "mail", 
	   	   					"state", 95442, "allergies", 2, 2, 2,1,"password");
		            
		            startActivity(new Intent(getActivity(),FamilyMainMenu.class));
		            Global.EsFamilia = false;
		  }

///********************* ADD FAMILY OR STUDENT TO DATA BASE *********************************
			}
			}).executeAsync();

	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        Global.EsEstudiante = false;
	       Global.EsFamilia = false;
	    }
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	private UiLifecycleHelper uiHelper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);	    
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	    
        datasource = new HomeStayDataSource(getActivity());
        datasource.open();
	  
	}
    // get user profile picture
    public Bitmap getFacebookProfilePicture(String userID) throws IOException{
        URL imgUrl = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        InputStream in = (InputStream) imgUrl.getContent();
        Bitmap  bitmap = BitmapFactory.decodeStream(in);

        return bitmap;
    }

	
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
}