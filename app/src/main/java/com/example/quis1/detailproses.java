package com.example.quis1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class detailproses extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailproses);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String tipeMember = intent.getStringExtra("tipe_member");
        String kodeBarang = intent.getStringExtra("kode_barang");
        int jumlahBarang = intent.getIntExtra("jumlah_barang", 1);

        String namaBarang = getNamaBarang(kodeBarang);
        double totalHarga = getTotalHarga(namaBarang, jumlahBarang);

        float diskonTambahan = hitungDiskonTambahan(totalHarga);
        double jumlahBayarSebelumDiskonTambahan = totalHarga;

        float diskonHarga = hitungDiskonHarga(tipeMember, totalHarga);
        float jumlahBayar = hitungJumlahBayar(totalHarga, diskonHarga, diskonTambahan);

        tampilkanInformasi(nama, tipeMember, kodeBarang, namaBarang, totalHarga, diskonHarga, jumlahBayar);

        setupShareButton(jumlahBayar);
    }

    private String getNamaBarang(String kodeBarang) {
        String namaBarang = "Barang tidak ditemukan";
        switch (kodeBarang) {
            case "SGS":
                namaBarang = "Samsung Galaxy S";
                break;
            case "AA5":
                namaBarang = "Acer Aspire 5";
                break;
            case "MP3":
                namaBarang = "Macbook Pro M3";
                break;
        }
        return namaBarang;
    }

    private double getTotalHarga(String namaBarang, int jumlahBarang) {
        double hargaBarang = 0;
        switch (namaBarang) {
            case "Samsung Galaxy S":
                hargaBarang = 12999999;
                break;
            case "Acer Aspire 5":
                hargaBarang = 9999999;
                break;
            case "Macbook Pro M3":
                hargaBarang = 28999999;
                break;
        }
        return hargaBarang * jumlahBarang;
    }

    private float hitungDiskonTambahan(double totalHarga) {
        return totalHarga > 10000000 ? 100000 : 0;
    }

    private float hitungDiskonHarga(String tipeMember, double totalHarga) {
        float diskonHarga = 0;
        switch (tipeMember) {
            case "Gold":
                diskonHarga = (float) (0.1 * totalHarga);
                break;
            case "Silver":
                diskonHarga = (float) (0.05 * totalHarga);
                break;
            case "Biasa":
                diskonHarga = (float) (0.02 * totalHarga);
                break;
        }
        return diskonHarga;
    }

    private float hitungJumlahBayar(double totalHarga, float diskonHarga, float diskonTambahan) {
        float jumlahBayar = (float) (totalHarga - diskonHarga);
        return jumlahBayar - diskonTambahan;
    }

    private void tampilkanInformasi(String nama, String tipeMember, String kodeBarang, String namaBarang, double totalHarga, float diskonHarga, float jumlahBayar) {
        TextView welcomeTextView = findViewById(R.id.SelamatDatang);
        welcomeTextView.setText("Selamat datang, " + nama + "!\nTipe Member: " + tipeMember);

        TextView memberTypeTextView = findViewById(R.id.etmember);
        memberTypeTextView.setText("Tipe Member: " + tipeMember);

        TextView itemCodeTextView = findViewById(R.id.etkode);
        itemCodeTextView.setText("Kode Barang: " + kodeBarang);

        TextView itemNameTextView = findViewById(R.id.etnama);
        itemNameTextView.setText("Nama Barang: " + namaBarang);

        TextView itemPriceTextView = findViewById(R.id.itemPriceTextView);
        itemPriceTextView.setText("Harga Barang: Rp " + String.format("%.0f", totalHarga));

        TextView discountPriceTextView = findViewById(R.id.tvdiskon);
        discountPriceTextView.setText("Diskon Harga: Rp " + String.format("%.0f", diskonHarga));

        TextView totalAmountTextView = findViewById(R.id.jlhbayar);
        totalAmountTextView.setText("Jumlah Bayar: Rp " + String.format("%.0f", jumlahBayar));
    }

    private void setupShareButton(float jumlahBayar) {
        Button shareButton = findViewById(R.id.btnbagikan);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Total Pembayaran: Rp " + String.format("%.0f", jumlahBayar);
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(shareIntent, "Bagikan"));
            }
        });
    }
}
