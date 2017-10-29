package com.supersupply.supersupply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.LinearLayout;


public class UserType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        Button user_button = (Button)findViewById(R.id.user_button);
        Button vendor_button = (Button)findViewById(R.id.vendor_button);

        vendor_button.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(UserType.this, vendor_listing.class));
                };
            }
        );
        user_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(UserType.this, user_activity.class));
                    };
                }
        );
        Bundle vendorData = getIntent().getExtras();
        if(vendorData == null) return;
    }
}
