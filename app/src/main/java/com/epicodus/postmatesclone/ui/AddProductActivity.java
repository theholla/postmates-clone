package com.epicodus.postmatesclone.ui;

import android.app.AlertDialog;
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
                String company = mCompanyName.getText().toString().trim();
                String productName = mProductName.getText().toString().trim();
                String productPriceString = mProductPrice.getText().toString().trim().replaceAll("[^\\w\\s]", "");

                if (company.isEmpty() || productName.isEmpty() || productPriceString.isEmpty() ) {
                    showErrorDialog();
                }
                else {
                    int productPrice = Integer.parseInt(productPriceString);
                    Product newProduct = new Product(company, productName, productPrice);
                    newProduct.save();
                    Intent intent = new Intent(AddProductActivity.this, StoreActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
        builder.setMessage("You are missing a required field. Please fill all fields.")
                .setTitle("Oops!")
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

}