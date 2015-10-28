package com.epicodus.postmatesclone.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.adapters.ProductAdapter;
import com.epicodus.postmatesclone.models.CustomerUser;
import com.epicodus.postmatesclone.models.Product;

import java.util.ArrayList;

public class CustomerActivity extends ListActivity {

    private Button mLogoutButton;
    private SharedPreferences mPreferences;
    private Button mAddProduct;
    private ArrayList<Product> mProducts;
    private ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mLogoutButton = (Button) findViewById(R.id.logoutButton);
        mPreferences = getApplicationContext().getSharedPreferences("postmates", Context.MODE_PRIVATE);
        mAddProduct = (Button) findViewById(R.id.addProduct);

        mProducts = (ArrayList) Product.all();
        mAdapter = new ProductAdapter(this, mProducts);
        setListAdapter(mAdapter);


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(CustomerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, AddProduct.class);
                startActivity(intent);
            }
        });

    }


}
