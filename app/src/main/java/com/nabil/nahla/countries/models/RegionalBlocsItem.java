package com.nabil.nahla.countries.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RegionalBlocsItem{

	@SerializedName("otherNames")
	private ArrayList<String> otherNames;

	@SerializedName("acronym")
	private String acronym;

	@SerializedName("name")
	private String name;

	@SerializedName("otherAcronyms")
	private ArrayList<String> otherAcronyms;

	public ArrayList<String> getOtherNames(){
		return otherNames;
	}

	public String getAcronym(){
		return acronym;
	}

	public String getName(){
		return name;
	}

	public ArrayList<String> getOtherAcronyms(){
		return otherAcronyms;
	}
}