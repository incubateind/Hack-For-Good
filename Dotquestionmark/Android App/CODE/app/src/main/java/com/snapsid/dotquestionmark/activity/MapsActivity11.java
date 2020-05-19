package com.snapsid.dotquestionmark.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.snapsid.dotquestionmark.MapsActivity;
import com.snapsid.dotquestionmark.R;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class MapsActivity11 extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 400;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private Button atm, bank, grocery,doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps11);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng sydney = new LatLng(28.5672, 77.2100);
        mMap.addMarker(new MarkerOptions().position(sydney).title("All India Institute Medical Sciences (AIIMS)"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        LatLng sydney1 = new LatLng(28.679455, 77.223605);
        mMap.addMarker(new MarkerOptions().position(sydney1).title("National Centre for Disease Control (NCDC)"));

        LatLng sydney2 = new LatLng(21.1534, 79.0940);
        mMap.addMarker(new MarkerOptions().position(sydney2).title("Indira Gandhi Government Medical College, Nagpur"));

        LatLng sydney3 = new LatLng(18.984028, 72.829840);
        mMap.addMarker(new MarkerOptions().position(sydney3).title("Kasturba Hospital for Infectious Diseases, Mumbai"));

        LatLng sydney4 = new LatLng(26.9054, 75.8155);
        mMap.addMarker(new MarkerOptions().position(sydney4).title("Sawai Man Singh, Jaipur"));


        LatLng sydney5 = new LatLng(26.269439, 73.007399);
        mMap.addMarker(new MarkerOptions().position(sydney5).title("Dr. S.N Medical College, Jodhpur"));

        LatLng sydney6 = new LatLng(24.597351, 76.160994);
        mMap.addMarker(new MarkerOptions().position(sydney6).title("Jhalawar Medical College, Jhalawar"));

        LatLng sydney7 = new LatLng(28.006392, 73.330494);
        mMap.addMarker(new MarkerOptions().position(sydney7).title("SP Med. College, Bikaner"));

        LatLng sydney8 = new LatLng(24.5882, 73.6950);
        mMap.addMarker(new MarkerOptions().position(sydney8).title("RNT Medical College, Udaipur"));

        LatLng sydney9 = new LatLng(13.012440, 80.217608);
        mMap.addMarker(new MarkerOptions().position(sydney9).title("King's Institute of Preventive Medicine & Research, Chennai"));

        LatLng sydney10 = new LatLng(10.0062, 77.5537);
        mMap.addMarker(new MarkerOptions().position(sydney10).title("Government Medical College, Theni"));

        LatLng sydney11 = new LatLng(8.7118, 77.7508);
        mMap.addMarker(new MarkerOptions().position(sydney11).title("Tirunelveli Medical College, Tirunelveli"));

        LatLng sydney12 = new LatLng(10.776455, 79.606019);
        mMap.addMarker(new MarkerOptions().position(sydney12).title("Govt. Medical college, Thiruvaru"));

        LatLng sydney13 = new LatLng(26.869255, 80.916204);
        mMap.addMarker(new MarkerOptions().position(sydney13).title("King's George Medical University, Lucknow"));

        LatLng sydney14 = new LatLng(25.2745, 82.9995);
        mMap.addMarker(new MarkerOptions().position(sydney14).title("Institute of Medical Sciences, Banaras Hindu University, Varanasi"));

        LatLng sydney15 = new LatLng(27.918607, 78.085726);
        mMap.addMarker(new MarkerOptions().position(sydney15).title("Jawaharlal Nehru Medical College, Aligarh"));

        LatLng sydney16 = new LatLng(29.206409, 79.521358);
        mMap.addMarker(new MarkerOptions().position(sydney16).title("Government Medical College, Haldwani"));

        LatLng sydney17 = new LatLng(22.563900, 88.398547);
        mMap.addMarker(new MarkerOptions().position(sydney17).title("National Institute of Cholera and Enteric Diseases, Kolkata"));

        LatLng sydney18 = new LatLng(22.5395, 88.3418);
        mMap.addMarker(new MarkerOptions().position(sydney18).title("IPGMER, Kolkata"));

        LatLng sydney19 = new LatLng(23.206761, 77.460146);
        mMap.addMarker(new MarkerOptions().position(sydney19).title("All India Institute Medical Sciences, Bhopal"));

        LatLng sydney20 = new LatLng(23.147227, 79.878570);
        mMap.addMarker(new MarkerOptions().position(sydney20).title("National Institute of Research in Tribal Health (NIRTH), Jabalpur"));

        LatLng sydney21 = new LatLng(9.418544, 76.343197);
        mMap.addMarker(new MarkerOptions().position(sydney21).title("National Institute of Virology Field Unit"));

        LatLng sydney22 = new LatLng(8.5234, 76.9283);
        mMap.addMarker(new MarkerOptions().position(sydney22).title("Govt. Medical College, Thriuvananthapuram"));

        LatLng sydney23 = new LatLng(11.2722, 75.8372);
        mMap.addMarker(new MarkerOptions().position(sydney23).title("Govt. Medical College, Kozhikhode"));

        LatLng sydney24 = new LatLng(10.6145, 76.1961);
        mMap.addMarker(new MarkerOptions().position(sydney24).title("Govt. Medical College, Thrissur"));

        LatLng sydney25 = new LatLng(12.9594, 77.5747);
        mMap.addMarker(new MarkerOptions().position(sydney25).title("Bangalore Medical College & Research Institute, Bangalore"));

        LatLng sydney26 = new LatLng(12.937325, 77.590833);
        mMap.addMarker(new MarkerOptions().position(sydney26).title("National Institute of Virology Field Unit Bangalore"));

        LatLng sydney27 = new LatLng(12.3150, 76.6504);
        mMap.addMarker(new MarkerOptions().position(sydney27).title("Mysore Medical College & Research Institute, Mysore"));

        LatLng sydney28 = new LatLng(13.004862, 76.102678);
        mMap.addMarker(new MarkerOptions().position(sydney28).title("Hassan Inst. of Med. Sciences, Hassan"));


        LatLng sydney29 = new LatLng(13.932622, 75.566819);
        mMap.addMarker(new MarkerOptions().position(sydney29).title("Shimoga Inst. of Med. Sciences, Shivamogga"));


        LatLng sydney30 = new LatLng(23.0525, 72.6029);
        mMap.addMarker(new MarkerOptions().position(sydney30).title("BJ Medical College, Ahmedabad"));

        LatLng sydney31 = new LatLng(22.4968, 70.0823);
        mMap.addMarker(new MarkerOptions().position(sydney31).title("M.P.Shah Government Medical College, Jamnagar"));

        LatLng sydney32 = new LatLng(28.9931, 77.0151);
        mMap.addMarker(new MarkerOptions().position(sydney32).title("BPS Govt Medical College, Sonipat"));

        LatLng sydney33 = new LatLng(28.880106, 76.605529);
        mMap.addMarker(new MarkerOptions().position(sydney33).title("Pt. B.D. Sharma Post Graduate Inst. of Med. Sciences, Rohtak"));

        LatLng sydney34 = new LatLng(31.1065, 77.1823);
        mMap.addMarker(new MarkerOptions().position(sydney34).title("Indira Gandhi Medical College, Shimla, Himachal Pradesh"));

        LatLng sydney35 = new LatLng(32.0983,76.3008);
        mMap.addMarker(new MarkerOptions().position(sydney35).title("Dr. Rajendra Prasad Govt. Med. College, Kangra, Tanda"));

        LatLng sydney36 = new LatLng(34.136149, 74.800034);
        mMap.addMarker(new MarkerOptions().position(sydney36).title("Sher-e- Kashmir Institute of Medical Sciences, Srinagar"));

        LatLng sydney37 = new LatLng(32.736112, 74.853972);
        mMap.addMarker(new MarkerOptions().position(sydney37).title("Government Medical College, Jammu"));

        LatLng sydney38 = new LatLng(34.0860, 74.7988);
        mMap.addMarker(new MarkerOptions().position(sydney38).title("Government Medical College, Srinagar"));

        LatLng sydney39 = new LatLng(22.8437, 86.2324);
        mMap.addMarker(new MarkerOptions().position(sydney39).title("MGM Medical College, Jamshedpur"));

        LatLng sydney40 = new LatLng(25.5907, 91.9398);
        mMap.addMarker(new MarkerOptions().position(sydney40).title("NEIGRI of Health and Medical Sciences, Shillong"));

        LatLng sydney41 = new LatLng(24.810070, 93.961137);
        mMap.addMarker(new MarkerOptions().position(sydney41).title("J N Inst. of Med. Sciences Hospital, Imphal-East, Manipur"));

        LatLng sydney42 = new LatLng(24.8159, 93.9228);
        mMap.addMarker(new MarkerOptions().position(sydney42).title("Regional Institute of Medical Sciences, Imphal"));

        LatLng sydney43 = new LatLng(20.3166, 85.8195);
        mMap.addMarker(new MarkerOptions().position(sydney43).title("Regional Medical Research Center, Bhubaneswar"));

        LatLng sydney44 = new LatLng(21.17695 , 72.84195 );
        mMap.addMarker(new MarkerOptions().position(sydney44).title("Government Medical College, Patiala"));

        LatLng sydney45 = new LatLng(31.6539, 74.8852);
        mMap.addMarker(new MarkerOptions().position(sydney45).title("Government Medical College, Amritsar"));

        LatLng sydney46 = new LatLng(11.950296, 79.799691);
        mMap.addMarker(new MarkerOptions().position(sydney46).title("Jawaharlal Institute of Postgraduate Medical Education & Research, Puducherry"));

        LatLng sydney47 = new LatLng(17.4247, 78.5044);
        mMap.addMarker(new MarkerOptions().position(sydney47).title("Gandhi Medical College, Secunderabad"));

        LatLng sydney48 = new LatLng(17.3822, 78.4829);
        mMap.addMarker(new MarkerOptions().position(sydney48).title("Osmania Medical College, Hyderabad"));

        LatLng sydney49 = new LatLng(23.860435, 91.292748);
        mMap.addMarker(new MarkerOptions().position(sydney49).title("Government Medical College, Agartala"));

        LatLng sydney50 = new LatLng(25.5997, 85.1977);
        mMap.addMarker(new MarkerOptions().position(sydney50).title("Rajendra Memorial Research Institute of Medical Sciences, Patna"));

        LatLng sydney51 = new LatLng(30.7650, 76.7750);
        mMap.addMarker(new MarkerOptions().position(sydney51).title("Post Graduate Institute of Medical Education & Research, Chandigarh"));

        LatLng sydney52 = new LatLng(21.2568, 81.5792);
        mMap.addMarker(new MarkerOptions().position(sydney52).title("All India Institute  Medical Sciences, Raipur"));

        LatLng sydney53 = new LatLng(11.6347, 92.7146);
        mMap.addMarker(new MarkerOptions().position(sydney53).title("Regional Medical Research Centre, Port Blair, Andaman and Nicobar"));

        LatLng sydney54 = new LatLng(13.637936, 79.403908);
        mMap.addMarker(new MarkerOptions().position(sydney54).title("Sri Venkateswara Institute of Medical Sciences, Tirupati"));

        LatLng sydney55 = new LatLng(17.7065, 83.3049);
        mMap.addMarker(new MarkerOptions().position(sydney55).title("Andhra Medical College, Visakhapatnam"));

        LatLng sydney57 = new LatLng(14.6712, 77.5929);
        mMap.addMarker(new MarkerOptions().position(sydney57).title("GMC, Anantapur"));

        LatLng sydney58 = new LatLng(16.5174, 80.6713);
        mMap.addMarker(new MarkerOptions().position(sydney58).title("Sidhartha Medical College, Vijayawada"));

        LatLng sydney59 = new LatLng(16.9792, 82.2373);
        mMap.addMarker(new MarkerOptions().position(sydney59).title("Rangaraya Medical College, Kakinada"));

        LatLng sydney60 = new LatLng(26.1620, 91.7697);
        mMap.addMarker(new MarkerOptions().position(sydney60).title("Gauhati Medical College, Guwahati"));

        LatLng sydney61 = new LatLng(27.472701, 94.982787);
        mMap.addMarker(new MarkerOptions().position(sydney61).title("Regional Medical Research Center, Dibrugarh"));

        LatLng sydney62 = new LatLng(24.7758, 92.7949);
        mMap.addMarker(new MarkerOptions().position(sydney62).title("Silchar Medical College, Silchar"));

        LatLng sydney63 = new LatLng(26.7426, 94.1951);
        mMap.addMarker(new MarkerOptions().position(sydney63).title("Jorhat Medical College, Jorhat"));




        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }



    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyDxRfxqFdgUlccCFu65mDq2C9Ao33G9q4A");
        Log.d("getUrl", googlePlacesUrl.toString());
        Log.d("test", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
//11.758  79.76
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(6));
        Toast.makeText(MapsActivity11.this,"Your Current Location", Toast.LENGTH_LONG).show();

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}
