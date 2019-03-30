package com.jakubAndTomek.bestdinner.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jakubAndTomek.bestdinner.R;

import listener.EmptyFieldsListener;
import listener.ResponseListener;
import services.Login;

public class LoginActivity extends AppCompatActivity implements EmptyFieldsListener, ResponseListener {

    private static final String logTag = StartActivity.class.getSimpleName();
    EditText loginEditText;
    EditText passwordEditText;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = new Login(this, this);

        Button loginButton = findViewById(R.id.loginButton);
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(logTag, "Kliknięto przycisk Zaloguj się");

                //TODO w zależności od uprawnień zalogowanego użytkownika, należy przenieść
                // go do stosownego ekranu (inny ekran dla klienta, a inny dla dostawcy)


                String loginText = loginEditText.getText().toString();
                String passwordText = passwordEditText.getText().toString().trim();
                login.loginUser(loginText, passwordText);
            }
        });
    }

    @Override
    public void callBackOnEmptyField() {
        Log.d(logTag, "Nie uzupełniono min. 1 pola");

        showMessage(getString(R.string.emptyLoginFieldsTitle), getString(R.string.emptyLoginFieldsMsg),
                getString(R.string.ok));

        cleanFields();
    }

    @Override
    public void onSuccessCallback() {
        Log.d(logTag, "Udane zalogowanie.");

        Intent intent = new Intent(LoginActivity.this, ClientHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailureCallback() {
        Log.d(logTag, "Niepoprawne dane logowania");

        showMessage(getString(R.string.wrongCredentialsTitle), getString(R.string.wrongCredentialsMsg),
                getString(R.string.ok));

        cleanFields();
    }

    public void showMessage(String title, String message, String positiveButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButton,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }

    public void cleanFields(){
        Log.d(logTag, "Czyszczenie pól");

        loginEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}
