package com.nabil.nahla.countries.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.utils.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.confirmPasswordET)
    EditText confirmPasswordET;
    @BindView(R.id.passwordTIL)
    TextInputLayout passwordTIL;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        Validation emailValidation = new Validation(this, emailET);
        emailET = emailValidation.validateEmail();

        Validation passwordValidation = new Validation(this, confirmPasswordET);
        confirmPasswordET = passwordValidation.validatePassword(passwordET);

    }

    @OnClick(R.id.registerB)
    public void onRegisterBClicked() {
        if (isValid()) return;

        if (emailET.getError() == null && passwordTIL.getError() == null && confirmPasswordET.getError() == null)
            createAccount(emailET.getText().toString(), passwordET.getText().toString());
        else if (emailET.getError() != null)
            Toast.makeText(this, emailET.getError().toString(), Toast.LENGTH_SHORT).show();
        else if (passwordTIL.getError() != null)
            Toast.makeText(this, passwordTIL.getError().toString(), Toast.LENGTH_SHORT).show();
        else if (confirmPasswordET.getError() != null)
            Toast.makeText(this, confirmPasswordET.getError().toString(), Toast.LENGTH_SHORT).show();

    }

    private boolean isValid() {
        if (emailET.getText().toString().isEmpty()) {
            emailET.setError(getString(R.string.empty_));
            return true;
        }

        if (emailET.getError() != null) {
            emailET.setError(emailET.getError().toString());
            return true;
        }

        if (passwordET.getText().toString().isEmpty()) {
            passwordTIL.setError(getString(R.string.empty_));
            return true;
        } else passwordTIL.setError(null);

        if (passwordET.getText().toString().length() < 6) {
            passwordTIL.setError(getString(R.string.short_pw));
            return true;
        } else passwordTIL.setError(null);

        if (confirmPasswordET.getText().toString().isEmpty()) {
            confirmPasswordET.setError(getString(R.string.empty_));
            return true;
        } else confirmPasswordET.setError(null);

        if (!passwordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
            confirmPasswordET.setError(getString(R.string.error_unmatch_pw));
            return true;
        } else confirmPasswordET.setError(null);
        return false;
    }

    @OnClick(R.id.loginTV)
    public void onLoginTVClicked() {
        startActivity(new Intent(this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    private void createAccount(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        }
                    }
                });
    }

}
