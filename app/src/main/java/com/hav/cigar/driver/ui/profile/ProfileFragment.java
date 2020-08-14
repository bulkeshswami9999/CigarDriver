package com.hav.cigar.driver.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.activities.HomeActivity;
import com.hav.cigar.driver.activities.OtpVerificationActivity;
import com.hav.cigar.driver.api.APIClient;
import com.hav.cigar.driver.api.APIInterface;
import com.hav.cigar.driver.model.Data;
import com.hav.cigar.driver.model.Example;
import com.hav.cigar.driver.model.LoginData;
import com.hav.cigar.driver.utility.MyApplication;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private EditText profile_firstname, profile_lastname, profile_id, profile_contact_number, profile_email, profile_address, profile_city, profile_state, profile_pincode;
    //private ImageView profile_image;
    View root;
    MyApplication myApplication;
    APIClient apiClient;
    APIInterface apiInterface;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //profile_image= root.findViewById(R.id.profile_image);
        profile_firstname = (EditText) root.findViewById(R.id.profile_firstname);
        profile_lastname = (EditText) root.findViewById(R.id.profile_lastname);
        profile_id = (EditText) root.findViewById(R.id.profile_id);
        profile_contact_number = (EditText) root.findViewById(R.id.profile_contact_number);
        profile_email =(EditText) root.findViewById(R.id.profile_email);
        profile_address = (EditText) root.findViewById(R.id.profile_address);
        profile_city = (EditText) root.findViewById(R.id.profile_city);
        profile_state = (EditText) root.findViewById(R.id.profile_state);
        profile_pincode = (EditText) root.findViewById(R.id.profile_pincode);


        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Example> call= apiInterface.getDriverInfo(myApplication.getPreferences().getString("id", "" ));
        // handle request in backgroun so called enque() method
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus().equals("200"))
                    {
                        //textViewResult.setText("Code:==== "+ response.code());
                        //Log.e("==responese",""+response.body().getId());
                        Log.e("==Driver Info ", "" + response.body().getStatus());


                        profile_firstname.setText(response.body().getData().getFullName());
                        profile_lastname.setText(response.body().getData().getFullName());
                        profile_id.setText(response.body().getData().getId());
                        profile_contact_number.setText(response.body().getData().getMob());

                        profile_email.setText(response.body().getData().getEmailId());
                        profile_address.setText(response.body().getData().getAddress1());
                        profile_city.setText(response.body().getData().getCity());
                        profile_state.setText(response.body().getData().getState());

                        profile_pincode.setText(response.body().getData().getPinCode());
                        Toast.makeText(getContext(), "Got Driver Profile Details", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("==Error Login ",""+t.toString());
            }


        });
    }


    }

