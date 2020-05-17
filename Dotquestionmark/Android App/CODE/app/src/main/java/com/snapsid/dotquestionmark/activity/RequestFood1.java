package com.snapsid.dotquestionmark.activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.snapsid.dotquestionmark.R;

import androidx.appcompat.app.AppCompatActivity;


public class RequestFood1 extends AppCompatActivity {

    private EditText name, city, address, request, phone, date, venture;
    private Button save;
    private Spinner district, type;
    private String disS, typeS;



    private LocationManager locationManager;
    private LocationListener locationListener;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;
    private String lat="", lon="";

    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_food1);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference();


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    // latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    lat=String.valueOf(location.getLatitude());
                    lon=String.valueOf(location.getLongitude());

                    // mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));

                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                   // String phoneNumber = "9675070702";
                    String myLatidude = String.valueOf(location.getAltitude());
                    String myLongitude = String.valueOf(location.getLongitude());

//                    String locString="https://www.google.com/maps/search/?api=1&query="+myLatidude+","+myLongitude;
//
//                    String message = "Latitude = " + myLatidude + " Longitude = " + myLongitude;
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNumber, null, locString, null, null);

                    Log.d("location1", lat+"  "+lon);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        name=(EditText) findViewById(R.id.nameid);
        city=(EditText) findViewById(R.id.cityid);
        address=(EditText) findViewById(R.id.addressid);
        request=(EditText) findViewById(R.id.foodid);
        phone=(EditText) findViewById(R.id.phoneid);

        date=(EditText) findViewById(R.id.dateid);

        save=(Button) findViewById(R.id.saveid);

        district= findViewById(R.id.disid);

        type=findViewById(R.id.disid3);
        venture=findViewById(R.id.ventureid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {

        Log.d("chat", "I sent something..");
        // TODO: Grab the text the user typed in and push the message to Firebase

        String input=name.getText().toString();
        String ph=phone.getText().toString();
        String ad=address.getText().toString();
        String ci=city.getText().toString();
        String food=request.getText().toString();

        String da=date.getText().toString();

        String ven=venture.getText().toString();




        disS=district.getSelectedItem().toString();
        typeS=type.getSelectedItem().toString();



        Log.d("dis", ven +typeS);

        if(input.equals("")|| ph.equals("")|| ad.equals("") ||da.equals("")||disS.equals("Select your District")||typeS.equals("Select Type") ||ven.equals("")|| lat.equals("") ||lon.equals("")){

            if(input.equals(""))
            {
                name.setError("Field is empty");
            }
            if(ph.equals(""))
            {
                phone.setError("Field is empty");
            }
            if(ad.equals(""))
            {
                address.setError("Field is empty");
            }
//            if(food.equals(""))
//            {
//                request.setError("Field is empty");
//            }
            if(da.equals(""))
            {
                date.setError("Field is empty");
            }

            if(ven.equals(""))
            {
                venture.setError("Field is empty");
            }

            if(disS.equals("Select your District"))
            {
                Toast.makeText(getApplicationContext(), "Please select the district", Toast.LENGTH_SHORT).show();
            }

            if(typeS.equals("Select Type"))
            {
                Toast.makeText(getApplicationContext(), "Please select type", Toast.LENGTH_SHORT).show();
            }

            if(lat.equals("")||lon.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Turn on your location", Toast.LENGTH_SHORT).show();
            }


        }
        else{
            InstandMessage1 chat=new InstandMessage1(ven,input,ph,ad,disS,typeS, da,  lat, lon);
            mDatabaseReference.child("order").push().setValue(chat);

            Intent intent=new Intent(getApplicationContext(), newui.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Request Successful", Toast.LENGTH_SHORT).show();

        }
    }
}

