package com.nabil.nahla.countries.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.adapters.CountryAdapter;
import com.nabil.nahla.countries.listeners.OnGetDataListener;
import com.nabil.nahla.countries.models.Country;
import com.nabil.nahla.countries.network.RetrofitManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements OnGetDataListener<ArrayList<Country>, String>, SwipeRefreshLayout.OnRefreshListener {

    private static final String LETTER_KEY = "LETTER";
    @BindView(R.id.msgTV)
    TextView msgTV;
    @BindView(R.id.countriesRV)
    RecyclerView countriesRV;
    @BindView(R.id.swipeRefreshL)
    SwipeRefreshLayout swipeRefreshL;

    CountryAdapter countryAdapter;
    ArrayList<Country> countries;

    String letter;

    public static Intent newInstance(Context context, String letter) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(LETTER_KEY, letter);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        swipeRefreshL.setOnRefreshListener(this);

        letter = getIntent().getStringExtra(LETTER_KEY);

        setUpRV();
        loadCountries();

    }

    private void setUpRV() {
        countries = new ArrayList<>();
        countryAdapter = new CountryAdapter(countries);
        countriesRV.setLayoutManager(new GridLayoutManager(this, 2));
        countriesRV.setAdapter(countryAdapter);
    }

    private void loadCountries() {
        RetrofitManager.getInstance().getAllCountries(this, this, new ProgressDialog(this));
    }

    @OnClick(R.id.logoutB)
    public void onLogoutBClicked() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    @Override
    public void onSuccess(ArrayList<Country> data) {
        msgTV.setVisibility(View.GONE);
        countriesRV.setVisibility(View.VISIBLE);
        swipeRefreshL.setRefreshing(false);

        countries.clear();
        for (Country country : data) {
            if (country.getName().startsWith(letter))
                countries.add(country);
        }
        // countries.addAll(data);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String errorMsg) {
        swipeRefreshL.setRefreshing(false);
        switch (errorMsg) {
            case "No internet connection!!":
                countriesRV.setVisibility(View.GONE);
                msgTV.setText(errorMsg);
                msgTV.setVisibility(View.VISIBLE);
                break;
            default:
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
                msgTV.setVisibility(View.GONE);
                countriesRV.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onRefresh() {
        loadCountries();
    }
}
