package com.snapsid.dotquestionmark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.snapsid.dotquestionmark.R;

public class Pledge extends AppCompatActivity {

    private Button ipledge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge);

        ipledge=(Button) findViewById(R.id.ipledgeid);
        ipledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSe_mB6NFAXSfE6lCJAXK4qKvFUJ6u1ttZ1BnP3MNgQPJmMaPA/viewform"));
                startActivity(viewIntent);
            }
        });
    }
}
