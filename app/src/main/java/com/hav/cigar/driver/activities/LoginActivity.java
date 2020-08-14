package com.hav.cigar.driver.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hav.cigar.driver.CurrentLocationMapsActivity;
import com.hav.cigar.driver.R;
import com.hav.cigar.driver.api.APIClient;
import com.hav.cigar.driver.api.APIInterface;
import com.hav.cigar.driver.model.LoginData;
import com.hav.cigar.driver.model.MobileNoExists;
import com.hav.cigar.driver.utility.Constant;
import com.hav.cigar.driver.utility.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{
    @BindView(R.id.signUpText)
    AppCompatTextView signUptext;
    @BindView(R.id.loginBtn)
    AppCompatButton loginBtn;
    @BindView(R.id.mobileNumber)
    AppCompatEditText mobileNumber;
    MyApplication myApp;
    APIInterface apiInterface;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        myApp= (MyApplication)getApplication();

        apiInterface = APIClient.getClient().create(APIInterface.class);


    }
    public void mylocation(View view)
    {
        startActivity(new Intent(this, CurrentLocationMapsActivity.class));
    }

    public void signUp(View view)
    {
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }

    public void Login(View view)
    {
        if(mobileNumber.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Mobile Number is Empty",Toast.LENGTH_SHORT).show();
        }
        else if(mobileNumber.getText().toString().length() < 10)
        {
            Toast.makeText(getApplicationContext(),"Please Enter Valid Mobile Number",Toast.LENGTH_SHORT).show();
        }
        else
            {
                // Call Check Mobile No Exists API
                //https://piersoncigars.com/api/driver_api.php?apicall=check_mobno_exists
                //Method : post
                //params : mob, token (Edit- means id we have to pass)
                  String token= myApp.getPreferences().getToken(Constant.PREFERENCE_DEVICE_TOKEN,"");
                Call<MobileNoExists> call= apiInterface.mobileNoExists(mobileNumber.getText().toString(),token);
                // handle request in backgroun so called enque() method
                call.enqueue(new Callback<MobileNoExists>()
                {
                    @Override
                    public void onResponse(Call<MobileNoExists> call, Response<MobileNoExists> response)
                    {
                        if(response.isSuccessful())
                        {

                            if(response.body().getStatus().equals("200"))
                            {
                                //textViewResult.setText("Code:==== "+ response.code());
                                //Log.e("==responese",""+response.body().getId());

                                Log.e("==responese of Login ", "" + response.body().getStatus());

                                myApp.getPreferences().setString("userid",response.body().getMobileNoExistsData().getId());
                                Log.e("==responese of id ", "" + response.body().getMobileNoExistsData());
//                                Log.e("==responese  mobnumber", "" + response.body().getMobileNoExistsData().getMob());
//
                                    // Call APi 2 and pass below 2 lines in that
                                startActivity(new Intent(LoginActivity.this, OtpVerificationActivity.class));
                                finish();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<MobileNoExists> call, Throwable t)
                    {
                       Log.e("==Error Login ",""+t.toString());
                    }

                    });
            }



            }
    }

    //--- Funtion to get Current Location of Driver
    /// --- End of btnCurrentLocation() func.

