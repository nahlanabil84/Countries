package com.nabil.nahla.countries.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.nabil.nahla.countries.R;

public class Validation {
    EditText view;
    Context context;

    public Validation(Context context, EditText view) {
        this.view = view;
        this.context = context;
    }

    public EditText validateEmail() {
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        view.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (view.getText().toString().matches(emailPattern) && s.length() > 0) {
                    view.setError(null);
                } else {
                    view.setError(context.getString(R.string.error_invalid_email));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        return view;
    }

    public EditText validatePassword(final EditText password) {

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!view.getText().toString().isEmpty() && !view.getText().toString().equals(password.getText().toString())) {
                    view.setError(context.getString(R.string.error_unmatch_pw));
                }
                if (view.getText().toString().equals(password.getText().toString())) {
                    view.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!view.getText().toString().equals(password.getText().toString())) {
                    view.setError(context.getString(R.string.error_unmatch_pw));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }

}
