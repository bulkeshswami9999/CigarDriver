package com.hav.cigar.driver.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hav.cigar.driver.R;
import com.hav.cigar.driver.api.APIClient;
import com.hav.cigar.driver.api.APIInterface;
import com.hav.cigar.driver.model.Data;

import com.hav.cigar.driver.model.Datum;
import com.hav.cigar.driver.model.LoginData;
import com.hav.cigar.driver.utility.Constant;
import com.hav.cigar.driver.utility.MyApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.POST;

public class OtpVerificationActivity extends AppCompatActivity {

    @BindView(R.id.verifyBtn)
    AppCompatButton verifyBtn;
    @BindView(R.id.resendText)
    AppCompatTextView resendText;
    @BindView(R.id.first)
    AppCompatEditText first;
    @BindView(R.id.second)
    AppCompatEditText second;
    @BindView(R.id.third)
    AppCompatEditText third;
    @BindView(R.id.fourth)
    AppCompatEditText fourth;
    MyApplication myApp;
    APIInterface apiInterface;
    Retrofit ret1;
    APIClient apiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        ButterKnife.bind(this);
        myApp=(MyApplication)getApplication();
        apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    public void verify(View view) {
        //@POST("api/driver_api.php?apicall=driver_login")
        //Call<Data2> getLoginInfo(@Field("mob") String mob);
        //242342345235
        Call<LoginData> call= apiInterface.getLoginInfo("242342345235");
        // handle request in backgroun so called enque() method
        call.enqueue(new Callback<LoginData>()
        {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus().equals("200"))
                    {
                        //textViewResult.setText("Code:==== "+ response.code());
                        //Log.e("==responese",""+response.body().getId());

                        Log.e("==responese of Login ", "" + response.body().getStatus());

                        myApp.getPreferences().setString("id","");

                        startActivity(new Intent(OtpVerificationActivity.this, HomeActivity.class));
                        finish();
                    }
                }


            }
            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.e("==Error Login ",""+t.toString());
            }
        });
    }

    public void resend(View view) {
        Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
    }
}
