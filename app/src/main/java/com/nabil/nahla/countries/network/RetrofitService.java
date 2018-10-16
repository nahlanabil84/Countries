package com.nabil.nahla.countries.network;

import com.nabil.nahla.countries.models.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET(Endpoints.ALL_COUNTRIES)
    Call<ArrayList<Country>> getAllCountries();
}
