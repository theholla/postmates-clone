package com.epicodus.postmatesclone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.Product;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ArrayList<Product> mOrder;
    private Product mProduct;
    private TextView displayProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        String product = getIntent().getStringExtra("product");
        //mProduct = Product.find(product);
        //mOrder.add(mProduct);

        displayProductName = (TextView) findViewById(R.id.displayProduct);
        displayProductName.setText(product);



    }


}
