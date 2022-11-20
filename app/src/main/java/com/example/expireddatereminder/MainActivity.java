package com.example.expireddatereminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.expireddatereminder.business.abstracts.ProductService;
import com.example.expireddatereminder.business.concretes.ProductManager;
import com.example.expireddatereminder.dataAccess.concretes.ProductRepositoryImpl;
import com.example.expireddatereminder.databinding.ActivityMainBinding;
import com.example.expireddatereminder.entities.concretes.Product;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private RecyclerViewProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar.toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        productAdapter =new RecyclerViewProductAdapter(this);
        binding.recyclerView.setAdapter(productAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
