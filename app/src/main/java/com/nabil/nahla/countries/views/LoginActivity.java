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
import com.google.firebase.auth.FirebaseUser;
import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.utils.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.passwordTIL)
    TextInputLayout passwordTIL;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        Validation emailValidation = new Validation(this, emailET);
        emailET = emailValidation.validateEmail();

    }

    @OnClick(R.id.loginB)
    public void onLoginBClicked() {
        if (isValid()) return;

        if (emailET.getError() == null && passwordTIL.getError() == null)
            signIn(emailET.getText().toString(), passwordET.getText().toString());
        else if (emailET.getError() != null)
            Toast.makeText(this, emailET.getError().toString(), Toast.LENGTH_SHORT).show();
        else if (passwordTIL.getError() != null)
            Toast.makeText(this, passwordTIL.getError().toString(), Toast.LENGTH_SHORT).show();
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
        return false;
    }

    @OnClick(R.id.registerTV)
    public void onRegisterTVClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

}
