package com.epicodus.postmatesclone.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.Product;
import com.epicodus.postmatesclone.ui.CheckoutActivity;
import com.epicodus.postmatesclone.ui.StoreActivity;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Guest on 10/28/15.
 */
public class ProductAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Product> mProducts;


    public ProductAdapter(Context context, ArrayList<Product> products){
        mProducts = products;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        ParseUser currentUser = ParseUser.getCurrentUser();
        String role = currentUser.getString("role");

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_list_item, null);
            holder = new ViewHolder();
            holder.companyNameLabel = (TextView) convertView.findViewById(R.id.companyName);
            holder.productNameLabel = (TextView) convertView.findViewById(R.id.productNameLabel);
            holder.priceNameLabel = (TextView) convertView.findViewById(R.id.productPriceLabel);
            holder.addItemButton = (Button) convertView.findViewById(R.id.addItemButton);
            holder.editItemButton = (Button) convertView.findViewById(R.id.btnEditButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (role.equals("admin")) {
            holder.editItemButton.setVisibility(View.VISIBLE);
        }
        else if (role.equals("customer")) {
            holder.addItemButton.setVisibility(View.VISIBLE);
        }

        final Product product = mProducts.get(position);

        holder.companyNameLabel.setText(product.getCompany());
        holder.productNameLabel.setText(product.getProductName());
        holder.priceNameLabel.setText(product.getPrice() + "");
        holder.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CheckoutActivity.class);
                intent.putExtra("product", product.getProductName());
                mContext.startActivity(intent);
                //TODO: Add functionality for edit button
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView companyNameLabel;
        TextView productNameLabel;
        TextView priceNameLabel;
        Button addItemButton;
        Button editItemButton;
    }

}
