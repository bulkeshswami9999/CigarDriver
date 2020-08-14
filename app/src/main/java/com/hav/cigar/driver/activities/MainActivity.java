package com.hav.cigar.driver.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.hav.cigar.driver.R;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
    Animation animFadeIn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
        animFadeIn.setAnimationListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.layout_linear);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.startAnimation(animFadeIn);



    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //LoginActivity.class
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }


}
