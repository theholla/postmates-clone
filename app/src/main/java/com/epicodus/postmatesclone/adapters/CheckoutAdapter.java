package com.epicodus.postmatesclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.Product;

import java.util.ArrayList;

/**
 * Created by Guest on 10/29/15.
 */
public class CheckoutAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Product> mProducts;

    public CheckoutAdapter(Context context, ArrayList<Product> products){
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

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.checkout_list_item, null);
            holder = new ViewHolder();
            holder.productNameLabel = (TextView) convertView.findViewById(R.id.productNameLabel);
            holder.productPriceLabel = (TextView) convertView.findViewById(R.id.productPriceLabel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = mProducts.get(position);

        holder.productNameLabel.setText(product.getProductName());
        holder.productPriceLabel.setText("$" + product.getPrice());

        return convertView;
    }

    private static class ViewHolder {
        TextView productNameLabel;
        TextView productPriceLabel;
    }
}
