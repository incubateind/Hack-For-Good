package com.snapsid.dotquestionmark.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.snapsid.dotquestionmark.MapsActivity;
import com.snapsid.dotquestionmark.R;

public class newui extends AppCompatActivity {

    private ImageButton nearby, test, current, precaution, symptom, faq, helpline, donate, pledge, food1, pass, order;
    private ImageView about, food;
    private Button map11, button11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newui);

        checkPermission();

        map11=(Button) findViewById(R.id.maps11id);
        button11=(Button) findViewById(R.id.button11);


        nearby=(ImageButton) findViewById(R.id.nearbyigid1);
        test=(ImageButton) findViewById(R.id.testigid);
        current=(ImageButton) findViewById(R.id.currentcaseigid);
        precaution=(ImageButton) findViewById(R.id.precautionigid);
        symptom=(ImageButton) findViewById(R.id.symptomsigid);
        faq=(ImageButton) findViewById(R.id.faqigid);
        helpline=(ImageButton) findViewById(R.id.helplineigid);
        donate=(ImageButton) findViewById(R.id.donateigid);
        pledge=(ImageButton) findViewById(R.id.pledgeigid);
        about=(ImageView) findViewById(R.id.aboutimgid);

        food=(ImageView) findViewById(R.id.foodid);

        food1=(ImageButton) findViewById(R.id.foodid1);

        pass=(ImageButton) findViewById(R.id.passid);
        order=(ImageButton) findViewById(R.id.orderid);

        food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, MainChatActivity.class);
                startActivity(intent);
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://policecitizenportal.uk.gov.in/e_pass/Home/SearchePass"));
                startActivity(viewIntent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, MainChatActivity1.class);
                startActivity(intent);
            }
        });


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, NewCurrent.class);
                startActivity(intent);
            }
        });


        map11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, MapsActivity11.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Abouts.class);
                startActivity(intent);
            }
        });


        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, MainActivity.class);
                startActivity(intent);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Hospital.class);
                startActivity(intent);
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, NewCurrent.class);
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, MainChatActivity.class);
                startActivity(intent);
            }
        });

        precaution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Precautions.class);
                startActivity(intent);
            }
        });
        symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Quiz.class);
                startActivity(intent);
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Faq.class);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Donate.class);
                startActivity(intent);
            }
        });
        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(newui.this, Helpline.class);
                startActivity(intent);
            }
        });
        pledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(newui.this, Pledge.class);
                startActivity(intent);
//                Intent viewIntent =
//                        new Intent("android.intent.action.VIEW",
//                                Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSe_mB6NFAXSfE6lCJAXK4qKvFUJ6u1ttZ1BnP3MNgQPJmMaPA/viewform"));
//                startActivity(viewIntent);
            }
        });


    }

    private void checkPermission() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
