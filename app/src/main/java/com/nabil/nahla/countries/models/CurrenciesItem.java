package com.nabil.nahla.countries.models;

import com.google.gson.annotations.SerializedName;

public class CurrenciesItem{

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	public String getSymbol(){
		return symbol;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}
}