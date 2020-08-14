package com.hav.cigar.driver.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hav.cigar.driver.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageUploadActivity extends AppCompatActivity {

    @BindView(R.id.submitBtn)
    AppCompatButton submitBtn;
    @BindView(R.id.uploadPhotos)
    AppCompatImageView uploadPhotos;
    @BindView(R.id.uploadDrivingLicence)
    AppCompatImageView uploadDrivingLicence;
    private static int GALLERY = 1;
    private static int imageLocation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        ButterKnife.bind(this);

    }

    public void Submit(View view) {
        startActivity(new Intent(ImageUploadActivity.this, AdminApprovalActivity.class));
        finish();
    }

    public void uploadDrivingLicence(View view) {
        imageLocation = 2;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    public void uploadPhoto(View view) {
        imageLocation = 1;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY && resultCode == RESULT_OK && null != data) {

            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    Toast.makeText(ImageUploadActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    if (imageLocation == 1)
                        uploadPhotos.setImageBitmap(bitmap);
                    else if (imageLocation == 2)
                        uploadDrivingLicence.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ImageUploadActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}