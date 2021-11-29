package com.example.kb1_master_akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kb1_master_akun.alret.AlretModal;

import master_akun.Database;

public class UpdateAkun extends AppCompatActivity {


    Button update, hapus;
    EditText input_nmr_akun,input_nama_akun,input_laporan_akun;
    String idakun, nomorakun, namaakun, laporanakun;
    Dialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun);
        update = findViewById(R.id.update);
        hapus = findViewById(R.id.hapus);
        input_nmr_akun = findViewById(R.id.input_nomor);
        input_nama_akun = findViewById(R.id.input_nama);
        input_laporan_akun = findViewById(R.id.input_laporan);
        getIntentData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlretModal am = new AlretModal(UpdateAkun.this);

                if (input_nmr_akun.getText().toString().isEmpty()) {
                    am.validasi("nomor");
                } else if (input_nama_akun.getText().toString().isEmpty()) {
                    am.validasi("nama");
                } else if (input_laporan_akun.getText().toString().isEmpty()) {
                    am.validasi("laporan");
                } else {
                    nomorakun = input_nmr_akun.getText().toString().trim();
                    namaakun = input_nama_akun.getText().toString().trim();
                    laporanakun = input_laporan_akun.getText().toString().trim();
                    Database mydb = new Database(UpdateAkun.this);
                    mydb.updataAkun(idakun, nomorakun, namaakun, laporanakun);
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database mydb = new Database(UpdateAkun.this);
                mydb.DeleteAkun(idakun);
            }
        });

    }

    private void getIntentData() {
        if(getIntent().hasExtra("idakun") && getIntent().hasExtra("nomorakun") && getIntent().hasExtra("namaakun") && getIntent().hasExtra("laporanakun")){
            idakun   = getIntent().getStringExtra("idakun");
            nomorakun = getIntent().getStringExtra("nomorakun");
            namaakun  = getIntent().getStringExtra("namaakun");
            laporanakun = getIntent().getStringExtra("laporanakun");

            input_nmr_akun.setText(nomorakun);
            input_nama_akun.setText(namaakun);
            input_laporan_akun.setText(laporanakun);

        }else{
            Toast.makeText(UpdateAkun.this, "Data Tidak Ditemukan",Toast.LENGTH_SHORT).show();
        }
    }
}