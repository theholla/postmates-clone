package com.epicodus.postmatesclone.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.adapters.CheckoutAdapter;
import com.epicodus.postmatesclone.models.Order;
import com.epicodus.postmatesclone.models.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends ListActivity {

    //private ArrayList<Product> mOrder;
    private Product mProduct;
    private TextView displayProductName;
    private TextView mTotalPrice;
    private Button mCheckoutButton;

    //for the Adapter
    private ArrayList<Product> mAllProducts;
    private CheckoutAdapter mAdapter;


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

        displayProductName = (TextView) findViewById(R.id.displayOrder);
        mCheckoutButton = (Button) findViewById(R.id.checkoutButton);

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

        mAllProducts = (ArrayList) Order.getProducts();
        mAdapter = new CheckoutAdapter(this, mAllProducts);
        setListAdapter(mAdapter);


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
