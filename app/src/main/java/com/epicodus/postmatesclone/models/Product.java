package com.epicodus.postmatesclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Guest on 10/28/15.
 */

@Table(name = "Product", id = "_id")
public class Product extends Model {

    @Column(name = "Company")
    private String mCompany;

    @Column(name = "ProductName")
    private String mProductName;

    @Column(name = "Price")
    private int mPrice;

    public Product() {
        super();
    }

    public Product(String company, String productName, int price) {
        super();
        mCompany = company;
        mProductName = productName;
        mPrice = price;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public static List<Product> findCompanyProducts(String company){
        return new Select()
                .from(Product.class)
                .where("Company = ?", company)
                .execute();
    }

    public static List<Product> all() {
        return new Select()
                .from(Product.class)
                .execute();
    }

    public static Product find(String product) {
        return new Select()
                .from(Product.class)
                .where("ProductName = ?", product)
                .executeSingle();
    }
}
