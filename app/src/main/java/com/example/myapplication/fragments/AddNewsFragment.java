package com.example.myapplication.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;


public class AddNewsFragment extends Fragment {
    public static final int PICK_IMAGE = 1;
    private ImageView image;
    private Bitmap bitmap;
    public AddNewsFragment() {
        super(R.layout.fragment_add_news);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        image = view.findViewById(R.id.addImage);
        TextView header = view.findViewById(R.id.news_header_text);
        TextView mainText = view.findViewById(R.id.news_main_text);
        Button loadImage = view.findViewById(R.id.load_btn);
        Button saveArticle = view.findViewById(R.id.save_btn);
        loadImage.setOnClickListener(v -> {
            imageLoader();
        });
        saveArticle.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putString("header", header.getText().toString());
            result.putString("main", mainText.getText().toString());
            result.putParcelable("image", bitmap);
            getParentFragmentManager().setFragmentResult("requestKey", result);
            getParentFragmentManager().beginTransaction().remove(this).commit();
        });
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE) {
            Uri selectedImage = data.getData();
            if(selectedImage != null) {
                image.setImageURI(selectedImage);
                bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
            }
        }
    }
    private void imageLoader() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
    }
}