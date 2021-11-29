package com.example.kb1_master_akun;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import master_akun.Database;

public class DaftarAkun extends AppCompatActivity {

    Database myDb;
    ArrayList<String> _id, nomor, nama, laporan;
    ListView listAkun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_akun);
        listAkun = findViewById(R.id.listAkun);

        myDb = new Database(DaftarAkun.this);
        _id = new ArrayList<>();
        nomor = new ArrayList<>();
        nama = new ArrayList<>();
        laporan = new ArrayList<>();
        displayData();

        ListAkunBaseAdapter listAkunBaseAdapter = new ListAkunBaseAdapter(DaftarAkun.this, this, _id, nomor, nama, laporan);
        listAkun.setAdapter(listAkunBaseAdapter);
    }

    private void displayData() {
        Cursor cursor = myDb.MasteringAkun();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Data Mahasiswa Kosong!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                _id.add(cursor.getString(0));
                nomor.add(cursor.getString(1));
                nama.add(cursor.getString(2));
                laporan.add(cursor.getString(3));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

}