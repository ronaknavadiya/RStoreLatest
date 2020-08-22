package com.example.rstore.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rstore.Admin.AdminMaintainProductsActivity;
import com.example.rstore.Admin.AdminNewOrdersActivity;
import com.example.rstore.Model.Products;
import com.example.rstore.R;
import com.example.rstore.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BuyersCategoriesViewActivity extends AppCompatActivity {

    private String productType= "";
    private RecyclerView productCategoriesList;
    private DatabaseReference productRef;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyers_categories_view);

        productType = getIntent().getStringExtra("category");
        productRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productCategoriesList = findViewById(R.id.buyers_categories_view_product_list);
        productCategoriesList.setLayoutManager(new LinearLayoutManager(this));
        title = findViewById(R.id.buyers_categories_view_title_text);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        title.setText("Category: " +productType);

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(productRef.orderByChild("category").equalTo(productType),Products.class)
                .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Products model)
            {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText("Price = "+ model.getPrice()+ "$");
                Picasso.get().load(model.getImage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                            Intent intent = new Intent(BuyersCategoriesViewActivity.this,ProductDetailsActivity.class);
                            intent.putExtra("pid",model.getPid());
                            startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
                return new ProductViewHolder(view);
            }
        };
        productCategoriesList.setAdapter(adapter);
        adapter.startListening();
    }
}
