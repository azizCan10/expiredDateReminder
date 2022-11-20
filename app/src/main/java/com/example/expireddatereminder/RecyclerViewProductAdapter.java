package com.example.expireddatereminder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expireddatereminder.business.abstracts.ProductService;
import com.example.expireddatereminder.business.concretes.ProductManager;
import com.example.expireddatereminder.dataAccess.concretes.ProductRepositoryImpl;
import com.example.expireddatereminder.databinding.ReyclerviewProductRowBinding;

public class RecyclerViewProductAdapter extends RecyclerView.Adapter<RecyclerViewProductAdapter.AdapterHolder>{
    ProductService productService;

    public RecyclerViewProductAdapter(Context context) {
        productService = new ProductManager(new ProductRepositoryImpl(context));
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReyclerviewProductRowBinding binding = ReyclerviewProductRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtRowId.setText(String.valueOf(productService.getAll().get(position).getId()));
        holder.binding.txtRowProductName.setText(productService.getAll().get(position).getProductName());
        holder.binding.txtRowExpireDate.setText(productService.getAll().get(position).getExpireDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductSettingsActivity.class);

                intent.putExtra("id", String.valueOf(productService.getAll().get(position).getId()));
                intent.putExtra("productName", productService.getAll().get(position).getProductName());
                intent.putExtra("expireDate", productService.getAll().get(position).getExpireDate());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productService.getAll().size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        ReyclerviewProductRowBinding binding;

        public AdapterHolder(@NonNull ReyclerviewProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
