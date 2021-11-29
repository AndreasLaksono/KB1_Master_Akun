package com.example.kb1_master_akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kb1_master_akun.alret.AlretModal;

import master_akun.Database;

public class MainActivity extends AppCompatActivity {

    Button simpan,listakun;
    EditText input_nmr_akun,input_nama_akun,input_laporan_akun;
    Dialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlretModal am = new AlretModal(MainActivity.this);

        simpan = findViewById(R.id.simpan);
        input_nmr_akun = findViewById(R.id.nmr_akun);
        input_nama_akun = findViewById(R.id.nama_akun);
        input_laporan_akun = findViewById(R.id.laporan_akun);
        listakun = findViewById(R.id.viewakun);

        listakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DaftarAkun.class);
                startActivity(i);
            }
        });


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_nmr_akun.getText().toString().isEmpty()){
                    am.validasi("nomor");
                }else if(input_nama_akun.getText().toString().isEmpty()){
                    am.validasi("nama");
                }else if(input_laporan_akun.getText().toString().isEmpty()){
                    am.validasi("laporan");
                }else{
                    int nomor =  Integer.parseInt(input_nmr_akun.getText().toString());
                    String nama = input_nama_akun.getText().toString();
                    String laporan = input_laporan_akun.getText().toString();
                    Database db = new Database(MainActivity.this);
                    db.insertData(nomor,nama,laporan);
                }
            }
        });

    }
}