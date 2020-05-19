package com.snapsid.dotquestionmark.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String a=sharedpreferences.getString("check", "0");
        Log.d("abcd", a);
        if(a.equals("1"))
        {
            Intent intent = new Intent(this, newui.class);
            startActivity(intent);

        }
        else{
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        finish();

    }
}
