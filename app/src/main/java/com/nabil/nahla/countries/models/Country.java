package com.nabil.nahla.countries.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Country {

    @SerializedName("area")
    private double area;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("capital")
    private String capital;

    @SerializedName("demonym")
    private String demonym;

    @SerializedName("flag")
    private String flag;

    @SerializedName("alpha2Code")
    private String alpha2Code;

    @SerializedName("languages")
    private ArrayList<LanguagesItem> languages;

    @SerializedName("borders")
    private ArrayList<String> borders;

    @SerializedName("subregion")
    private String subregion;

    @SerializedName("callingCodes")
    private ArrayList<String> callingCodes;

    @SerializedName("regionalBlocs")
    private ArrayList<RegionalBlocsItem> regionalBlocs;

    @SerializedName("gini")
    private double gini;

    @SerializedName("population")
    private int population;

    @SerializedName("numericCode")
    private String numericCode;

    @SerializedName("alpha3Code")
    private String alpha3Code;

    @SerializedName("topLevelDomain")
    private ArrayList<String> topLevelDomain;

    @SerializedName("timezones")
    private ArrayList<String> timezones;

    @SerializedName("cioc")
    private String cioc;

    @SerializedName("translations")
    private Translations translations;

    @SerializedName("name")
    private String name;

    @SerializedName("altSpellings")
    private ArrayList<String> altSpellings;

    @SerializedName("region")
    private String region;

    @SerializedName("latlng")
    private ArrayList<Double> latlng;

    @SerializedName("currencies")
    private ArrayList<CurrenciesItem> currencies;

    public double getArea() {
        return area;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getCapital() {
        return capital;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getFlag() {
        return flag;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public ArrayList<LanguagesItem> getLanguages() {
        return languages;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public String getSubregion() {
        return subregion;
    }

    public ArrayList<String> getCallingCodes() {
        return callingCodes;
    }

    public ArrayList<RegionalBlocsItem> getRegionalBlocs() {
        return regionalBlocs;
    }

    public double getGini() {
        return gini;
    }

    public int getPopulation() {
        return population;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public ArrayList<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public ArrayList<String> getTimezones() {
        return timezones;
    }

    public String getCioc() {
        return cioc;
    }

    public Translations getTranslations() {
        return translations;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAltSpellings() {
        return altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public ArrayList<Double> getLatlng() {
        return latlng;
    }

    public ArrayList<CurrenciesItem> getCurrencies() {
        return currencies;
    }
}