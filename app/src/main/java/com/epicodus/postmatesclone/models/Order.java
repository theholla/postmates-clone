package com.epicodus.postmatesclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 10/29/15.
 */
@Table(name = "Orders", id = "_id")
public class Order extends Model {

    @Column(name = "Product")
    private Product mProduct;

    public Product getProduct() {
        return mProduct;
    }

    public Order() {
        super();
    }

    public Order(Product product) {
        super();
        mProduct = product;
    }

    public static List<Order> getOrder() {
        return new Select()
            .from(Order.class)
            .execute();
    }

    public static void deleteOrder() {
               new Delete()
               .from(Order.class)
                .execute();
    }

    public static List<Product> getProducts(){
        List<Order> allOrders;
        allOrders = new ArrayList<>();
        allOrders = Order.getOrder();

        List<Product> allProducts;
        allProducts = new ArrayList<>();
        for (Order order : allOrders){
            allProducts.add(order.getProduct());
        }
        return allProducts;
    }


 }
