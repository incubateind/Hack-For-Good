package com.snapsid.dotquestionmark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.snapsid.dotquestionmark.R;

public class Login extends AppCompatActivity {


    private EditText name, email, city;
    private Button save;
     DatabaseReference mDatabaseReference;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference();

        name=(EditText) findViewById(R.id.name1id);
        email=(EditText) findViewById(R.id.emailid);
        city=(EditText) findViewById(R.id.cityid);

        save=(Button) findViewById(R.id.saveid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a,b,c;

                a=name.getText().toString();
                b=email.getText().toString();
                c=city.getText().toString();
                //b=t2.getText().toString();








                if (TextUtils.isEmpty(a)||TextUtils.isEmpty(b) || TextUtils.isEmpty(c) || !Patterns.EMAIL_ADDRESS.matcher(b).matches())   {
                    if(TextUtils.isEmpty(a)) {
                        name.setError("Field is empty");
                    }
                    if(TextUtils.isEmpty(b))
                    {
                        email.setError("Field is empty");
                    }
                    if(TextUtils.isEmpty(c)) {
                        city.setError("Field is empty");
                    }

                    if(!Patterns.EMAIL_ADDRESS.matcher(b).matches())
                    {
                        email.setError("Invalid Email");
                    }
                }
                else {
                    InstantMessage mes=new InstantMessage(c, a, b);
                    mDatabaseReference.push().setValue(mes);
                    Intent intent=new Intent(getApplicationContext(), newui.class);
                    startActivity(intent);

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("check", "1");
                    editor.putString("email", b);
                    editor.putString("city", c);

                    editor.commit();
                    finish();
                }
            }
        });


    }
}
