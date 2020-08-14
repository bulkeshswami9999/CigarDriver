package com.hav.cigar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.model.DeliveryModel;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context mContext;
    private List<DeliveryModel> listOfDelivery;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        AppCompatTextView status;
        AppCompatImageView statsuImage;
        MyViewHolder(View view) {
            super(view);
            title = (AppCompatTextView) view.findViewById(R.id.title);
            status = (AppCompatTextView)view.findViewById(R.id.status);
            statsuImage = (AppCompatImageView)view.findViewById(R.id.statusImg);


        }
    }

    public OrderAdapter(Context mContext, List<DeliveryModel> listOfDelivery) {
        this.mContext = mContext;
        this.listOfDelivery = listOfDelivery;
    }
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_layout, parent, false);
        return new OrderAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final OrderAdapter.MyViewHolder holder, int position) {
        if(position == 1)
        {
            holder.status.setText("Not Delivered");
            holder.statsuImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_close));

        }else if(position == 2){
            holder.status.setText("Canceled");
            holder.statsuImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_close));
        }



    }


    void removeItem(int position) {
        listOfDelivery.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DeliveryModel item, int position) {
        listOfDelivery.add(position, item);
        notifyItemInserted(position);
    }

    public List<DeliveryModel> getData() {
        return listOfDelivery;
    }



    @Override
    public int getItemCount() {
        return 6;
    }
}

