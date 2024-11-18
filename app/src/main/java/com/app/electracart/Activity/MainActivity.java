package com.app.electracart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.app.electracart.Adapter.PopularListAdapter;
import com.app.electracart.Domain.PopularDomain;
import com.app.electracart.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerview();
        bottom_navigation();

    }

    private void bottom_navigation() {

        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(view -> {
            // Handle home button click
        });

        cartBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initRecyclerview() {

        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("MacBook Pro 13 M2 chip", "Discover the new Macebook Pro 13 featuring the powerful M2 chip. This is cutting-edge laptop redefines performance and portability. With its sleek design and advanced technology, the MacBook Pro 13 M2 chip is your ultimatecompanion for productivity, creativity and entertainment. Experience seamless multitasking, stunning visuals on the Retina display and enhanced security with Touch ID. Take your computing experience to the next level with the MacBook Pro 13 M2 chip.", "pic1", 15, 4.0, 500));
        items.add(new PopularDomain("PS-5 Digital", "Discover the new Macebook Pro 13 featuring the powerful M2 chip. This is cutting-edge laptop redefines performance and portability. With its sleek design and advanced technology, the MacBook Pro 13 M2 chip is your ultimatecompanion for productivity, creativity and entertainment. Experience seamless multitasking, stunning visuals on the Retina display and enhanced security with Touch ID. Take your computing experience to the next level with the MacBook Pro 13 M2 chip.", "pic2", 10, 4.5, 450));
        items.add(new PopularDomain("IPhone 14", "Discover the new Macebook Pro 13 featuring the powerful M2 chip. This is cutting-edge laptop redefines performance and portability. With its sleek design and advanced technology, the MacBook Pro 13 M2 chip is your ultimatecompanion for productivity, creativity and entertainment. Experience seamless multitasking, stunning visuals on the Retina display and enhanced security with Touch ID. Take your computing experience to the next level with the MacBook Pro 13 M2 chip.", "pic3", 13, 4.2, 800));

        recyclerViewPopular = findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPopular = new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}