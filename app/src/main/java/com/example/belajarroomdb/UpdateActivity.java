package com.example.belajarroomdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import model.Produk;

public class UpdateActivity extends AppCompatActivity {

    EditText etNamaProduk, etDeskripsiProduk;
    Button btnUpdate, btnDelete;
    AppDatabase app;
    int produkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        // Inisialisasi EditText dan Button
        etNamaProduk = findViewById(R.id.et_nama);
        etDeskripsiProduk = findViewById(R.id.et_deskripsi);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        // Inisialisasi database Room
        app = Room.databaseBuilder(this, AppDatabase.class, "inventory.db")
                .allowMainThreadQueries()
                .build();

        // Ambil data dari intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("produk_id")) {
            produkId = intent.getIntExtra("produk_id", -1);
            if (produkId != -1) {
                // Load data untuk update
                Produk produk = app.produkDao().getAll().get(produkId);
                if (produk != null) {
                    etNamaProduk.setText(produk.nama);
                    etDeskripsiProduk.setText(produk.deskripsi);
                }
            }
        }

        // Aksi ketika tombol update diklik
        btnUpdate.setOnClickListener(v -> {
            String namaProduk = etNamaProduk.getText().toString();
            String deskripsiProduk = etDeskripsiProduk.getText().toString();

            if (namaProduk.isEmpty() || deskripsiProduk.isEmpty()) {
                Toast.makeText(this, "Nama dan Deskripsi harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Memperbarui data
            Produk produk = new Produk();
            produk.id = produkId;
            produk.nama = namaProduk;
            produk.deskripsi = deskripsiProduk;

            app.produkDao().update(produk);

            Toast.makeText(this, "Data Berhasil Diperbarui", Toast.LENGTH_SHORT).show();

            // Kirim hasil ke MainActivity
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Aksi ketika tombol delete diklik
        btnDelete.setOnClickListener(v -> {
            // Menghapus data
            Produk produk = new Produk();
            produk.id = produkId;

            app.produkDao().delete(produk);

            Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();

            // Kirim hasil ke MainActivity
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
