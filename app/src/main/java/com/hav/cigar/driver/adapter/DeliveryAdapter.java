package com.hav.cigar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.model.DeliveryModel;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder> {

    private Context mContext;
    private List<DeliveryModel> listOfDelivery;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        AppCompatTextView status;
        MyViewHolder(View view) {
            super(view);
            title = (AppCompatTextView) view.findViewById(R.id.title);

        }
    }

    public DeliveryAdapter(Context mContext, List<DeliveryModel> listOfDelivery) {
        this.mContext = mContext;
        this.listOfDelivery = listOfDelivery;
    }
    @Override
    public DeliveryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivery_layout, parent, false);
        return new DeliveryAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final DeliveryAdapter.MyViewHolder holder, int position) {



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
