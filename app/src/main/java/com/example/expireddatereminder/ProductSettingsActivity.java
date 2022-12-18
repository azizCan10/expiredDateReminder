package com.example.expireddatereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.expireddatereminder.service.ProductService;
import com.example.expireddatereminder.service.impl.ProductServiceImpl;
import com.example.expireddatereminder.repository.impl.ProductRepositoryImpl;
import com.example.expireddatereminder.databinding.ActivityProductSettingsBinding;
import com.example.expireddatereminder.entity.Product;

import java.util.Calendar;
import java.util.Objects;

public class ProductSettingsActivity extends AppCompatActivity {

    ActivityProductSettingsBinding binding;

    DatePickerDialog datePickerDialog;

    int productId;

    ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductSettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar.toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.toolbar.setTitle("Ürün Ayarları");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        binding.toolbar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductSettingsActivity.super.onBackPressed();
            }
        });

        productService = new ProductServiceImpl(new ProductRepositoryImpl(this));

        initDatePicker();
        productId = Integer.valueOf(getIntent().getStringExtra("id"));

        binding.edtTxtProductName.setText(getIntent().getStringExtra("productName"));
        binding.btnSelectDate.setText(getIntent().getStringExtra("expireDate"));

        binding.btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productService.update(new Product(productId, binding.edtTxtProductName.getText().toString(), binding.btnSelectDate.getText().toString()));
                ProductSettingsActivity.super.onBackPressed();
            }
        });

        binding.btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productService.delete(productId);
                ProductSettingsActivity.super.onBackPressed();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                binding.btnSelectDate.setText(day + "/" + (month + 1) + "/" + year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);

    }

    public void dialogDatePicker(View view) {
        datePickerDialog.show();
    }
}