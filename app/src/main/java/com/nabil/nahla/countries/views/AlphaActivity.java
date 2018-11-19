package com.nabil.nahla.countries.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.adapters.LetterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlphaActivity extends AppCompatActivity {

    LetterAdapter lettersAdapter;
    @BindView(R.id.lettersRV)
    RecyclerView lettersRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        ButterKnife.bind(this);

        setUpRV();

    }

    private void setUpRV() {
        lettersAdapter = new LetterAdapter();
        lettersRV.setLayoutManager(new GridLayoutManager(this, 3));
        lettersRV.setAdapter(lettersAdapter);
    }

}
