package com.hav.cigar.driver.ui.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hav.cigar.driver.activities.GoogleMapActivity;
import com.hav.cigar.driver.activities.HomeActivity;
import com.hav.cigar.driver.activities.MapActivity;
import com.hav.cigar.driver.fragments.GoogleMapFragment;
import com.hav.cigar.driver.R;
import com.hav.cigar.driver.adapter.DeliveryAdapter;
import com.hav.cigar.driver.adapter.SliderAdapter;
import com.hav.cigar.driver.fragments.MapFragment;
//import com.hav.cigar.driver.fragments.MapFrgment;
import com.hav.cigar.driver.model.DeliveryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeFragment extends Fragment {

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    TabLayout indicator;
    List<String> title;
    List<Integer> count;
    List<DeliveryModel> listOfDelivery;
    DeliveryAdapter deliveryAdapter;

    @BindView(R.id.homeRecylerView)
    RecyclerView homeRecylerView;
    @BindView(R.id.accept)
    AppCompatButton acceptBtn;
    private String[] runtimePermissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Pierson Geoffreys");
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        title = new ArrayList<>();
        title.add("Total Count");
        title.add("Upcoming Delivery");
        title.add("Cancel Delivery");
        title.add("Completed Delivery");
        count = new ArrayList<>();
        count.add(20);
        count.add(10);
        count.add(5);
        count.add(5);
        viewPager.setAdapter(new SliderAdapter(getContext(), title,count));
        indicator.setupWithViewPager(viewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        listOfDelivery = new ArrayList<>();
        LinearLayoutManager elayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        homeRecylerView.setHasFixedSize(true);
        homeRecylerView.setLayoutManager(elayoutManager);
        deliveryAdapter = new DeliveryAdapter(getContext(), listOfDelivery);
        homeRecylerView.setAdapter(deliveryAdapter);
        if (EasyPermissions.hasPermissions(getActivity(), runtimePermissions)) {
        } else {
            EasyPermissions.requestPermissions(this, "Access for Location",
                    101, runtimePermissions);
        }
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent =new Intent(getActivity(),GoogleMapActivity.class);
                getActivity().startActivity(myintent);
             // startActivity(new Intent(getActivity(), GoogleMapActivity.class));
                //FragmentTransaction fmt = getActivity().getSupportFragmentManager().beginTransaction();
                //fmt.replace(R.id.nav_host_fragment,new GoogleMapFragment());
                //fmt.addToBackStack(null);
                //fmt.commit();
            }
        });
        return root;
    }
    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if(getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < title.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
