package com.epicodus.postmatesclone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.postmatesclone.R;

public class SuccessActivity extends AppCompatActivity {

    private Button mNewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        mNewOrder = (Button) findViewById(R.id.newOrder);

        mNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
    }

}
