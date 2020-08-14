package com.hav.cigar.driver.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.adapter.NotificationAdapter;
import com.hav.cigar.driver.adapter.OrderAdapter;
import com.hav.cigar.driver.model.DeliveryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    List<DeliveryModel> listOfDelivery;
    NotificationAdapter notificationAdapter;

    @BindView(R.id.notificationRecylerView)
    RecyclerView notificationRecylerView;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View notificationView = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this,notificationView);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Notification");
        listOfDelivery = new ArrayList<>();
        LinearLayoutManager elayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        notificationRecylerView.setHasFixedSize(true);
        notificationRecylerView.setLayoutManager(elayoutManager);
        notificationAdapter = new NotificationAdapter(getContext(), listOfDelivery);
        notificationRecylerView.setAdapter(notificationAdapter);
        return notificationView;
    }
}
