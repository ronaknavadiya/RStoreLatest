package com.example.rstore.Buyers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.rstore.R;
import com.example.rstore.Sellers.SellerAddNewProductActivity;
import com.example.rstore.Sellers.SellerProductCategoryActivity;

public class BuyersCategoriesActivity extends AppCompatActivity {

    private ImageView tshirts,sportsTshirts,femaleDress,sweaters;
    private ImageView glasses,hatsCaps,walletsBagsPurses,shoes;
    private ImageView headphonesHandsfree,laptops,watches,mobilePhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyers_categories);

        tshirts = findViewById(R.id.buyers_t_shirts);
        sportsTshirts = findViewById(R.id.buyers_sports_t_shirts);
        femaleDress = findViewById(R.id.buyers_female_dresses);
        sweaters = findViewById(R.id.buyers_sweather);
        glasses = findViewById(R.id.buyers_glasses);
        hatsCaps = findViewById(R.id.buyers_hats_caps);
        walletsBagsPurses = findViewById(R.id.buyers_purses_bags_wallets);
        shoes = findViewById(R.id.buyers_shoes);
        headphonesHandsfree = findViewById(R.id.buyers_headphones_handsfree);
        laptops = findViewById(R.id.buyers_laptop_pc);
        watches = findViewById(R.id.buyers_watches);
        mobilePhones = findViewById(R.id.buyers_mobilephones);

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        sportsTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","SportsTShirts");
                startActivity(intent);
            }
        });

        femaleDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","Female Dresses");
                startActivity(intent);
            }
        });

        sweaters.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","Sweaters");
                startActivity(intent);
            }
        }));

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","glasses");
                startActivity(intent);
            }
        });

        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","hats");
                startActivity(intent);
            }
        });

        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","Wallets Bags Purses");
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","shoes");
                startActivity(intent);
            }
        });

        headphonesHandsfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","headphones");
                startActivity(intent);
            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","Laptops");
                startActivity(intent);
            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","Watches");
                startActivity(intent);
            }
        });

        mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyersCategoriesActivity.this, BuyersCategoriesViewActivity.class);
                intent.putExtra("category","Mobile phones");
                startActivity(intent);
            }
        });

    }
}
