package com.epicodus.postmatesclone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        displayProductName.setText(product);

        mTotalPrice = (TextView) findViewById(R.id.totalPrice);
        mTotalPrice.setText(getTotal() + "");

    }

    public int getTotal(){
        List<Product> productList = new ArrayList<>();
        productList = Order.getProducts();
        int firstPrice = productList.get(0).getPrice();
        Integer totalPrice = 0;

        for(Product product : productList){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }


}
