package com.snapsid.dotquestionmark.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.snapsid.dotquestionmark.R;



public class RequestFood extends AppCompatActivity {

    private EditText name, city, address, request, phone, date;
    private Button save;
    private Spinner district;
    private String disS;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;
    private String lat="", lon="";

    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_food);

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

        disS=district.getSelectedItem().toString();

        Log.d("dis", disS);

        if(input.equals("")|| ph.equals("")|| ad.equals("") ||food.equals("")||da.equals("")||disS.equals("Select your District")|| lat.equals("") ||lon.equals("")){

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
            if(food.equals(""))
            {
                request.setError("Field is empty");
            }
            if(da.equals(""))
            {
                date.setError("Field is empty");
            }

            if(disS.equals("Select your District"))
            {
                Toast.makeText(getApplicationContext(), "Please select the district", Toast.LENGTH_SHORT).show();
            }

            if(lat.equals("")||lon.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Turn on your location", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            InstandMessage chat=new InstandMessage(input,ph,ad,disS,food, da, lat, lon);
            mDatabaseReference.child("messages").push().setValue(chat);

            Intent intent=new Intent(getApplicationContext(), newui.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Request Successful", Toast.LENGTH_SHORT).show();

        }
    }
}

