package com.example.expireddatereminder.repository.impl;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.expireddatereminder.repository.ProductRepository;
import com.example.expireddatereminder.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    SQLiteDatabase database;

    public ProductRepositoryImpl(Context context) {
        database = context.openOrCreateDatabase("Products", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS products (id INT PRIMARY KEY, productName VARCHAR, expireDate VARCHAR)");
    }

    @Override
    public void add(Product product) {
        String sqlString = "INSERT INTO products (productName, expireDate) VALUES (?, ?)";

        SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
        sqLiteStatement.bindString(1, product.getProductName());
        sqLiteStatement.bindString(2, product.getExpireDate());

        sqLiteStatement.execute();
    }

    @Override
    public void update(Product product) {
        String sqlString = "UPDATE products SET productName = '" + product.getProductName() + "', expireDate = '" + product.getExpireDate() + "' WHERE id = " + product.getId() + "";
        SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
        sqLiteStatement.execute();
    }

    @Override
    public void delete(int id) {
        String sqlString = "DELETE FROM products WHERE id = " + id + "";
        SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
        sqLiteStatement.execute();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM products", null);

        int id = cursor.getColumnIndex("id");
        int productName = cursor.getColumnIndex("productName");
        int expireDate = cursor.getColumnIndex("expireDate");

        while (cursor.moveToNext()) {
            products.add(new Product(cursor.getInt(id), cursor.getString(productName), cursor.getString(expireDate)));
        }

        return products;
    }

    @Override
    public Product getById(int id) {
        return null;
    }


}
