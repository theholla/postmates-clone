package com.epicodus.postmatesclone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.Product;

public class AddProductActivity extends AppCompatActivity {

    private EditText mCompanyName;
    private EditText mProductName;
    private EditText mProductPrice;
    private Button mNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mCompanyName = (EditText) findViewById(R.id.companyName);
        mProductName = (EditText) findViewById(R.id.productNameLabel);
        mProductPrice = (EditText) findViewById(R.id.productPriceLabel);
        mNewProduct = (Button) findViewById(R.id.newProduct);

        mNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add logic for error handling if fields are blank
                String company = mCompanyName.getText().toString();
                String productName = mProductName.getText().toString();
                int productPrice = Integer.parseInt(mProductPrice.getText().toString().replaceAll("[^\\w\\s]",""));

                Product newProduct = new Product(company, productName, productPrice);
                newProduct.save();
                Intent intent = new Intent(AddProductActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });
    }

}