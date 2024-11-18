package com.app.electracart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.electracart.Domain.PopularDomain;
import com.app.electracart.Helper.ManagementCart;
import com.app.electracart.R;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private Button addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt;
    private ImageView itemPic, backBtn;
    private PopularDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
    }

    private void getBundle() {

        object = (PopularDomain) getIntent().getSerializableExtra("object");

        if (object == null) {
            Toast.makeText(this, "Error loading item details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(itemPic);
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview() + "");
        scoreTxt.setText(object.getScore() + "");

        addToCartBtn.setOnClickListener(view -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);
            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
        });

        backBtn.setOnClickListener(view -> finish());
    }

    private void initView() {

        addToCartBtn = findViewById(R.id.addToCartBtn);
        feeTxt = findViewById(R.id.feeTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        itemPic = findViewById(R.id.itemPic);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        backBtn = findViewById(R.id.backBtn);
    }
}