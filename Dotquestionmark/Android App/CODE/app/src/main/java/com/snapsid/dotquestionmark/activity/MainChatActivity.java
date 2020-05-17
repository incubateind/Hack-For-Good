package com.snapsid.dotquestionmark.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.snapsid.dotquestionmark.R;

import androidx.appcompat.app.AppCompatActivity;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mDatabaseReference;
    private ChatListAdapter mAdapter;
    private Spinner district;

    private Button re;
    String cityCheck1="";
    SharedPreferences sharedpreferences;
    String disS="";

    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        cityCheck1=sharedpreferences.getString("city", "0");

        // TODO: Set up the display name and get the Firebase reference
        setDisplayName();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();

        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        re=(Button) findViewById(R.id.requestid);

        district= findViewById(R.id.disid2);
        go=(Button) findViewById(R.id.goid);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disS=district.getSelectedItem().toString();
                mAdapter =new ChatListAdapter(MainChatActivity.this, mDatabaseReference, mDisplayName, disS);
                mChatListView.setAdapter(mAdapter);

            }
        });


        // TODO: Send the message when the "enter" button is pressed

        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                sendMessage();
                return true;
            }
        });
        // TODO: Add an OnClickListener to the sendButton to send a message
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent=new Intent(getApplicationContext(), RequestFood.class);
            startActivity(intent);
                //sendMessage();
            }
        });
    }

    // TODO: Retrieve the display name from the Shared Preferences

    private void setDisplayName(){

//        SharedPreferences prefs=getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
//        mDisplayName=prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, null);

        if(mDisplayName==null) mDisplayName="xyz";
    }

    private void sendMessage() {

        Log.d("chat", "I sent something..");
        // TODO: Grab the text the user typed in and push the message to Firebase

//        String input=mInputText.getText().toString();
//
//        if(!input.equals("")){
//            InstandMessage chat=new InstandMessage(input, mDisplayName);
//            mDatabaseReference.child("messages").push().setValue(chat);
//            mInputText.setText("");
//        }
    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.


    @Override
    public void onStart(){
        super.onStart();
        mAdapter =new ChatListAdapter(this, mDatabaseReference, mDisplayName, disS);
        mChatListView.setAdapter(mAdapter);
    }
    @Override
    public void onStop() {
        super.onStop();

        // TODO: Remove the Firebase event listener on the adapter.
        mAdapter.cleanup();

    }

}
