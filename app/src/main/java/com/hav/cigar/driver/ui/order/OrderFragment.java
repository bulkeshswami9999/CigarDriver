package com.hav.cigar.driver.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.adapter.DeliveryAdapter;
import com.hav.cigar.driver.adapter.OrderAdapter;
import com.hav.cigar.driver.model.DeliveryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends Fragment {

    List<DeliveryModel> listOfDelivery;
    OrderAdapter orderAdapter;

    @BindView(R.id.orderRecylerView)
    RecyclerView orderRecylerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this,root);
        listOfDelivery = new ArrayList<>();
        LinearLayoutManager elayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orderRecylerView.setHasFixedSize(true);
        orderRecylerView.setLayoutManager(elayoutManager);
        orderAdapter = new OrderAdapter(getContext(), listOfDelivery);
        orderRecylerView.setAdapter(orderAdapter);
        return root;
    }
}
