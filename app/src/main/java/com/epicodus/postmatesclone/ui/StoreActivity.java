package com.epicodus.postmatesclone.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.adapters.ProductAdapter;
import com.epicodus.postmatesclone.models.Product;
import com.parse.ParseUser;

import java.util.ArrayList;

public class StoreActivity extends ListActivity {

    private Button mLogoutButton;
    private Button mAddProduct;
    private ArrayList<Product> mProducts;
    private ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mLogoutButton = (Button) findViewById(R.id.logoutButton);
        mAddProduct = (Button) findViewById(R.id.addProduct);

        mProducts = (ArrayList) Product.all();
        mAdapter = new ProductAdapter(this, mProducts);
        setListAdapter(mAdapter);


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();

                Intent intent = new Intent(StoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

}
