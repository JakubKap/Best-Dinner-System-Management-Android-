package com.jakubAndTomek.bestdinner.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.jakubAndTomek.bestdinner.R;

import utils.ConnectionDetector;

public class StartActivity extends AppCompatActivity {

    private static final String logTag = StartActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ConnectionDetector cd = new ConnectionDetector(this);
        if(!cd.connected()){
            Log.d(logTag, "Brak połączenia z Internetem");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle(getString(R.string.noConnectionTitle));
            builder.setMessage(getString(R.string.noConnectionMsg));
            builder.setPositiveButton(getString(R.string.ok),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           finishAffinity();
                        }
                    });

            builder.show();
        }

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(logTag, "Obsługa kliknięcia przeycisku logowania");
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                //TODO zdecydować, czy po przejściu do ekranu logowania można się cofnąć
                //finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(logTag, "Obsługa kliknięcia przeycisku rejestracji");
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
                //TODO zdecydować, czy po przejściu do ekranu rejestracji można się cofnąć
                //finish();
            }
        });
    }

}



