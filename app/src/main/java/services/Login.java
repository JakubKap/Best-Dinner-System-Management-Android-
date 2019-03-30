package services;

import android.util.Log;

import com.jakubAndTomek.bestdinner.activity.StartActivity;

import listener.EmptyFieldsListener;
import listener.ResponseListener;

public class Login {
    private static final String logTag = StartActivity.class.getSimpleName();
    private EmptyFieldsListener emptyFieldsListener;
    private ResponseListener responseListener;

    public Login(EmptyFieldsListener emptyFieldsListener, ResponseListener responseListener){
        this.emptyFieldsListener = emptyFieldsListener;
        this.responseListener = responseListener;
    }

    public void loginUser(String login, String password){
        Log.d(logTag, "login = " + login
                + ", hasło + " + password);

        if(login.isEmpty() || password.isEmpty()){
            Log.d(logTag, "Brak loginu lub hasła");
            emptyFieldsListener.callBackOnEmptyField();
        }
        else{
            //TODO docelowo zapytanie do bazy,
            // a tymczasem sprawdzenie, czy (login == "klient" && password =="klient")

            if(login.equals("klient") && password.equals("klient")){
                Log.d(logTag, "Wprowadzono poprawne dane");
                responseListener.onSuccessCallback();
            }
            else
                //niepoprawne dane logowania
                responseListener.onFailureCallback();
        }
    }
}
