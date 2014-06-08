package com.example.homestaymanagerandroidapp;
import java.util.Date;

public class Student {
	public int _id;
	public String firstName;
	public String lastName;
	public String emailAddress;
	public String gender;
	public String phone;
	public String startDate;
	public String endDate;
	public String address;
	public String state;
	public int zipCode;
	public String allergies;
	public boolean dogPet;
	public boolean catPet;
	public int famSize;
	public boolean smoke;
	
	Student()
	{
		
	}
	
	Student(int id, String fn, String ln, String email, String gender, String ph, String sd, String ed, String add, String st,
			int zip, String alle, int dp, int cp, int famSi, int smoke )
	{
		_id = id;
		firstName = fn;
		lastName = ln;
		emailAddress = email;
		this.gender = gender;
		phone = ph;
		startDate = sd;
		endDate = ed;
		address = add;
		state = st;
		zipCode = zip;
		allergies = alle;
		if(dp == 0)
		{
			dogPet = false;
		}
		else
		{
			dogPet = true;
		}
		if(cp == 0)
		{
			catPet = false;
		}
		else
		{
			catPet = true;
		}
		famSize = famSi;
		if(smoke == 0)
		{
			this.smoke = false;
		}
		else
		{
			this.smoke = true;
		}
	}
}
