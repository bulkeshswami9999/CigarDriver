package com.hav.cigar.driver.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hav.cigar.driver.R;

public class AdminApprovalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approval);
    }

    public void Login(View view) {
        startActivity(new Intent(AdminApprovalActivity.this, LoginActivity.class));
        finish();
    }
}
