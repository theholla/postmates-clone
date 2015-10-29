package com.epicodus.postmatesclone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.Order;
import com.epicodus.postmatesclone.models.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    //private ArrayList<Product> mOrder;
    private Product mProduct;
    private TextView displayProductName;
    private TextView mTotalPrice;
    private Button mCheckoutButton;
    //private ArrayList<Product> mAllProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //ArrayList<Product> mOrder = new ArrayList<>();

        String product = getIntent().getStringExtra("product");
        mProduct = Product.find(product);
        //mOrder.add(mProduct);

        Order order = new Order(mProduct);
        order.save();

        displayProductName = (TextView) findViewById(R.id.displayProduct);
        mCheckoutButton = (Button) findViewById(R.id.checkoutButton);
        displayProductName.setText(getListProducts().get(0));

        mTotalPrice = (TextView) findViewById(R.id.totalPrice);
        mTotalPrice.setText("Total: " + getTotal());

        mCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.deleteOrder();
                Intent intent = new Intent(CheckoutActivity.this, SuccessActivity.class);
                startActivity(intent);
            }
        });

    }

    public int getTotal(){
        List<Product> productList = new ArrayList<>();
        productList = Order.getProducts();
        Integer totalPrice = 0;

        for(Product product : productList){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public List<String> getListProducts(){
        List<Product> productList = new ArrayList<>();
        productList = Order.getProducts();
        List<String> productNames;
        productNames = new ArrayList<>();
        for(Product product : productList){
            productNames.add(product.getProductName());
        }

        return productNames;
    }


}
