package com.supersupply.supersupply;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supersupply.supersupply.R;

public class vendor_listing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(vendor_listing.this, AddActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle addData = getIntent().getExtras();
        if(addData == null) return;
        String entry_message = addData.getString("entry_message");
        LinearLayout group = (LinearLayout)findViewById(R.id.entries);
        TextView newEntry = new TextView(this);
        newEntry.setText(entry_message);
        group.addView(newEntry);
    }
}
