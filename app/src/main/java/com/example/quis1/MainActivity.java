package com.example.quis1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etNama, etKodeBarang, etJlhBarang;
    RadioGroup radioGroup;
    RadioButton btngold, btnsilver, btnreguler;
    Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etKodeBarang = findViewById(R.id.etkodebarang);
        etJlhBarang = findViewById(R.id.etjlhbarang);
        radioGroup = findViewById(R.id.radioGroup);
        btngold = findViewById(R.id.btngold);
        btnsilver = findViewById(R.id.btnsilver);
        btnreguler = findViewById(R.id.btnreguler);
        btnProses = findViewById(R.id.btnproses);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String kodeBarang = etKodeBarang.getText().toString();
                int jumlahBarang = 0;
                try {
                    jumlahBarang = Integer.parseInt(etJlhBarang.getText().toString());
                } catch (NumberFormatException e) {
                    etJlhBarang.setError("Jumlah barang harus angka");
                    return;
                }
                String tipeMember = getTipeMember();

                Intent intent = new Intent(MainActivity.this, detailproses.class);
                intent.putExtra("nama", nama);
                intent.putExtra("kode_barang", kodeBarang);
                intent.putExtra("tipe_member", tipeMember);
                intent.putExtra("jumlah_barang", jumlahBarang);
                startActivity(intent);
            }
        });
    }

    private String getTipeMember() {
        String tipeMember = "";
        int checkedId = radioGroup.getCheckedRadioButtonId();

        if (checkedId == R.id.btngold) {
            tipeMember = "Gold";
        } else if (checkedId == R.id.btnsilver) {
            tipeMember = "Silver";
        } else if (checkedId == R.id.btnreguler) {
            tipeMember = "Biasa";
        }
        return tipeMember;
    }
}