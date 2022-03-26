package com.example.nolfi.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nolfi.R;

import java.util.ArrayList;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ViewHolder> {
    private ArrayList<MainItem> mainItems;
    @NonNull
    @Override
    public MainItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainpage_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainItemAdapter.ViewHolder holder, int position) {
        holder.onBind(mainItems.get(position));
    }

    public void setFriendList(ArrayList<MainItem> list){
        this.mainItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mainItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView product_name;
        TextView living_area;
        TextView time;
        TextView product_price;
        TextView product_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            living_area = (TextView) itemView.findViewById(R.id.living_area);
            time=(TextView) itemView.findViewById(R.id.time);
            product_price=(TextView) itemView.findViewById(R.id.product_price);
            product_category=(TextView) itemView.findViewById(R.id.product_category);
        }

        void onBind(MainItem item){
            product_image.setImageURI(item.getProduct_image());
            product_name.setText(item.getProduct_name());
            living_area.setText(item.getLiving_area());
            time.setText(item.getTime());
            product_price.setText(item.getProduct_price());
            product_category.setText(item.getProduct_category());
        }
    }
}