package com.supersupply.supersupply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button submit = (Button) findViewById(R.id.submit);
        mDatabase = FirebaseDatabase.getInstance().getReference().push();
        submit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(AddActivity.this, vendor_listing.class);
                        final EditText name_input = (EditText) findViewById(R.id.name);
                        final EditText location_input = (EditText) findViewById(R.id.location);
                        final EditText weight_input = (EditText) findViewById(R.id.weight);
                        final EditText expiration_input = (EditText) findViewById(R.id.expiration);
                        final EditText food_input = (EditText) findViewById(R.id.food);
                        final EditText lat = (EditText) findViewById(R.id.latitude);
                        final EditText lon = (EditText) findViewById(R.id.longitude);
                        final TextView thanks = (TextView) findViewById(R.id.thanks);

                        mDatabase.child("food").setValue(food_input.getText().toString());
                        mDatabase.child("weight").setValue(weight_input.getText().toString());
                        mDatabase.child("expiration").setValue(expiration_input.getText().toString());
                        mDatabase.child("location").setValue(location_input.getText().toString());
                        mDatabase.child("name").setValue(name_input.getText().toString());
                        mDatabase.child("latitude").setValue(lat.getText().toString());
                        mDatabase.child("longitude").setValue(lon.getText().toString());
                        thanks.setText("Thanks for your donation!");
                    }
                }
        );
    }
}
