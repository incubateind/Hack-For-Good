package com.snapsid.dotquestionmark.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.snapsid.dotquestionmark.R;

public class Fa extends AppCompatActivity {

    private TextView tt;
    String aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa);

        tt=(TextView)findViewById(R.id.ans);
        Intent intent=getIntent();
        aa=intent.getStringExtra("1");
        tt.setText(aa);
    }
}
