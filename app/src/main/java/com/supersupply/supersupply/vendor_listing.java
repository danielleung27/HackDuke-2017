package com.supersupply.supersupply;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.supersupply.supersupply.R;

import java.util.ArrayList;

public class vendor_listing extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView entries;
    private ArrayList<String> food = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        entries = (ListView) findViewById(R.id.entries);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, food);
        entries.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String list = "";
                list += "Name: " + dataSnapshot.child("name").getValue(String.class) + "\n";
                list += "Location: " + dataSnapshot.child("location").getValue(String.class) + "\n";
                list += "Expiration: " + dataSnapshot.child("expiration").getValue(String.class) + "\n";
                list += "Amount: " + dataSnapshot.child("weight").getValue(String.class) + "\n";
                list += "Description: " + dataSnapshot.child("food").getValue(String.class);
                food.add(list);
                arrayAdapter.notifyDataSetChanged();
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
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(vendor_listing.this, user_activity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      /*  Bundle addData = getIntent().getExtras();
        if(addData == null) return;
        String entry_message = addData.getString("entry_message");
        LinearLayout group = (LinearLayout)findViewById(R.id.entries);
        TextView newEntry = new TextView(this);
        newEntry.setText(entry_message);
        group.addView(newEntry);*/
    }
}
