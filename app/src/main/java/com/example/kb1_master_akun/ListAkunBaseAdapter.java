package com.example.kb1_master_akun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ListAkunBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList _id,nomor,nama,laporan;
    LayoutInflater inflter;

    ConstraintLayout mainLayout;

    Activity activity;

    public ListAkunBaseAdapter(Activity activity,Context context, ArrayList _id, ArrayList nomor, ArrayList nama, ArrayList laporan) {
        this.context = context;
        this._id = _id;
        this.nomor = nomor;
        this.nama = nama;
        this.laporan = laporan;
        this.activity = activity;
        inflter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return _id.size(); }

    @Override
    public Object getItem(int i) { return null; }

    @Override
    public long getItemId(int i) { return 0; }


    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView _id_txt,nomor_mhs_txt,nama_mhs_txt,laporan_mhs_txt;
        view = inflter.inflate(R.layout.list_akun,null);

        _id_txt = view.findViewById(R.id._id);
        nomor_mhs_txt = view.findViewById(R.id.nmr_akun);
        nama_mhs_txt = view.findViewById(R.id.nama_akun);
        laporan_mhs_txt = view.findViewById(R.id.laporan_akun);

        mainLayout = view.findViewById(R.id.mainLayout);

        _id_txt.setText(String.valueOf(_id.get(i)));
        nomor_mhs_txt.setText(String.valueOf(nomor.get(i)));
        nama_mhs_txt.setText(String.valueOf(nama.get(i)));
        laporan_mhs_txt.setText(String.valueOf(laporan.get(i)));

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,_id.get(i).toString(),Toast.LENGTH_SHORT).show();
                Intent int_updateakun = new Intent(context, UpdateAkun.class);
                int_updateakun.putExtra("idakun",String.valueOf(_id.get(i)));
                int_updateakun.putExtra("nomorakun",String.valueOf(nomor.get(i)));
                int_updateakun.putExtra("namaakun",String.valueOf(nama.get(i)));
                int_updateakun.putExtra("laporanakun",String.valueOf(laporan.get(i)));
                activity.startActivityForResult(int_updateakun, 1);
            }
        });

        return view;


    }
}
