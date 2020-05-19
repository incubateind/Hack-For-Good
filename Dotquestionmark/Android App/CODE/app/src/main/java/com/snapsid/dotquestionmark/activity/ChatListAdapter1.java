package com.snapsid.dotquestionmark.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.snapsid.dotquestionmark.R;

import java.util.ArrayList;

public class ChatListAdapter1 extends BaseAdapter {

    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;
    private ArrayList<DataSnapshot> mSnapshotLists;

    private String cityC;




    private ChildEventListener mListener=new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            mSnapshotLists.add(dataSnapshot);
            notifyDataSetChanged();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public ChatListAdapter1(Activity activity, DatabaseReference ref, String name, String aa){

        mActivity=activity;
        mDisplayName=name;
        cityC=aa;
        mDatabaseReference=ref.child("order");
        mDatabaseReference.addChildEventListener(mListener);


        mSnapshotLists=new ArrayList<>();


        Log.d("citycheck", cityC);
    }

    static  class ViewHolder{
        TextView authorName;
        TextView body;
        TextView city1;
        TextView address1;
        TextView phone;
        TextView date;
        Button locate;
        TextView owner;
        LinearLayout.LayoutParams params;
    }
    @Override
    public int getCount() {
        return mSnapshotLists.size();
    }

    @Override
    public InstandMessage1 getItem(int i) {

        DataSnapshot snapshot=mSnapshotLists.get(i);
        return snapshot.getValue(InstandMessage1.class);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            LayoutInflater inflater=(LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.chat_msg_row, viewGroup, false);

            final ViewHolder holder=new ViewHolder();
            holder.authorName=(TextView) view.findViewById(R.id.author);
            holder.body=(TextView) view.findViewById(R.id.message);
            holder.city1=(TextView) view.findViewById(R.id.citydis);
            holder.address1=(TextView) view.findViewById(R.id.addis);
            holder.phone=(TextView) view.findViewById(R.id.phonedis);
            holder.date=(TextView) view.findViewById(R.id.datedis);

            holder.locate=(Button) view.findViewById(R.id.locateid);
            holder.owner=(TextView) view.findViewById(R.id.ownerNameid);

            holder.params=(LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            view.setTag(holder);
        }


        final InstandMessage1 message=getItem(i);
        final ViewHolder holder=(ViewHolder) view.getTag();

        boolean isMe=message.getAuthor().equals(mDisplayName);
        setChatRowApperance(isMe, holder);

        String city11=message.getCity();

        Log.d("cityCheck1", city11);
        String author=message.getAuthor();
        holder.authorName.setText(author);

        String msg="Shop Type: "+message.getMessage();
        holder.body.setText(msg);

        String ad= "Address: "+message.getAddress();
        holder.address1.setText(ad);

        String ci="City: "+message.getCity();
        holder.city1.setText(ci);

        String ph="Number: "+message.getPhone();
        holder.phone.setText(ph);

        String dt="Short Description: "+message.getDate();
        holder.date.setText(dt);


//        String ow="Owner";
//        holder.owner.setText(ow);

        final String lat1=message.getLat();
        final String lon1=message.getLon();
        Log.d("lat1", lat1);
        Log.d("lat1", lon1);

        if(!cityC.equals(city11))
        {
            holder.authorName.setVisibility(View.GONE);
            holder.body.setVisibility(View.GONE);
            holder.address1.setVisibility(View.GONE);
            holder.city1.setVisibility(View.GONE);
            holder.phone.setVisibility(View.GONE);
            holder.date.setVisibility(View.GONE);
           holder.locate.setVisibility(View.GONE);
          // holder.owner.setVisibility(View.GONE);

        }
        else{
            holder.authorName.setVisibility(View.VISIBLE);
            holder.body.setVisibility(View.VISIBLE);
            holder.address1.setVisibility(View.VISIBLE);
            holder.city1.setVisibility(View.VISIBLE);
            holder.phone.setVisibility(View.VISIBLE);
           holder.locate.setVisibility(View.GONE);
//            holder.owner.setVisibility(View.VISIBLE);

            holder.locate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("maps", "click");

                    String geoUri = "http://maps.google.com/maps?q=loc:" + lat1 + "," + lon1 + " (" + "" + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                    mActivity.startActivity(intent);
                }
            });
        }
        return view;
    }

    private void setChatRowApperance(boolean isItMe, ViewHolder holder){

        if(isItMe){
            holder.params.gravity= Gravity.END;
            holder.authorName.setTextColor(Color.BLUE);
        }
        else{
            holder.params.gravity= Gravity.START;
            holder.authorName.setTextColor(Color.RED);
        }

        holder.authorName.setLayoutParams(holder.params);
        holder.body.setLayoutParams(holder.params);
    }
    void  cleanup() {
        mDatabaseReference.removeEventListener(mListener);
    }
}
