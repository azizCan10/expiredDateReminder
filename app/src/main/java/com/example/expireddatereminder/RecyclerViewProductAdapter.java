package com.example.expireddatereminder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expireddatereminder.service.ProductService;
import com.example.expireddatereminder.service.impl.ProductServiceImpl;
import com.example.expireddatereminder.repository.impl.ProductRepositoryImpl;
import com.example.expireddatereminder.databinding.ReyclerviewProductRowBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecyclerViewProductAdapter extends RecyclerView.Adapter<RecyclerViewProductAdapter.AdapterHolder>{
    ProductService productService;

    Context context;

    NotificationCompat.Builder builder;
    Date now;
    DateFormat x;

    public RecyclerViewProductAdapter(Context context) {
        this.context = context;
        productService = new ProductServiceImpl(new ProductRepositoryImpl(context));
        builder = new NotificationCompat.Builder(context, "urun hatirlatma");
        now = new Date();
        x = new SimpleDateFormat("yyyy/MM/dd");
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

        try {
            if (x.format(now).equals(x.format(convertToDate(productService.getAll().get(position).getExpireDate())))) {
                holder.binding.txtRowProductName.setTextColor(context.getResources().getColor(R.color.red));
                builder.setContentTitle("Ürün Hatırlatma");
                builder.setContentText(productService.getAll().get(position).getProductName() + " ürününün son kullanma tarihi bugün.");
                builder.setSmallIcon(R.drawable.ic_baseline_add_24);
                builder.setAutoCancel(false);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                managerCompat.notify(position, builder.build());
            }
            else
                if (now.after(convertToDate(productService.getAll().get(position).getExpireDate()))) {
                    holder.binding.txtRowProductName.setTextColor(context.getResources().getColor(R.color.purple_700));
                    holder.binding.txtRowProductName.setText(productService.getAll().get(position).getProductName() + " (SKT geçti.)");
                }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    private Date convertToDate(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);

        return date1;
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
