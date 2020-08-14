package com.hav.cigar.driver.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hav.cigar.driver.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUpActivity extends AppCompatActivity {

    //@BindView(R.id.logintext)
    //AppCompatTextView loginTxt;
    @BindView(R.id.registerBtn)
    AppCompatButton registerBtn;
    @BindView(R.id.fullname)
    AppCompatEditText fullName;
   // @BindView(R.id.lastName)
    //AppCompatEditText lastName;
    @BindView(R.id.mobileNumber)
    AppCompatEditText mobileNumber;
    @BindView(R.id.email)
    AppCompatEditText email;
    @BindView(R.id.dob)
    AppCompatEditText dob;
    @BindView(R.id.license)
    AppCompatEditText license;
    @BindView(R.id.address)
    AppCompatEditText address;
    @BindView(R.id.city)
    AppCompatEditText city;
    @BindView(R.id.state)
    AppCompatEditText state;
    @BindView(R.id.pincode)
    AppCompatEditText pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    public void Login(View view) {
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();
    }

    public void register(View view) {
        if (license.getText() != null && license.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Driving license is Empty", Toast.LENGTH_SHORT).show();
        } else if (fullName.getText() != null && fullName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Full Name is Empty", Toast.LENGTH_SHORT).show();
        } else if (pincode.getText() != null && pincode.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter a Pincode", Toast.LENGTH_SHORT).show();
        } else if (state.getText() != null && state.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "State is Empty", Toast.LENGTH_SHORT).show();
        } else if (city.getText() != null && city.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "City is Empty", Toast.LENGTH_SHORT).show();
        } else if (mobileNumber.getText() != null && mobileNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Mobile Number is Empty", Toast.LENGTH_SHORT).show();
        } else if (email.getText() != null && email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter a Email-ID", Toast.LENGTH_SHORT).show();
        }  else if(address.getText()!=null && address.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Address is Empty",Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(SignUpActivity.this, ImageUploadActivity.class));
            finish();
        }
    }
}
