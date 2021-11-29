package com.example.kb1_master_akun.alret;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kb1_master_akun.R;

public class AlretModal {
    Context context;

    public AlretModal(Context context) { this.context = context;}

    public void validasi(String string_error){
        Dialog errorDialog = new Dialog(context);
        errorDialog.setContentView(R.layout.alret_dialog);
        errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView error = errorDialog.findViewById(R.id.txt_error);
        error.setText(string_error);
        errorDialog.show();

        Button close_dialog_format_email = errorDialog.findViewById(R.id.close_dialog_format_email);
        close_dialog_format_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss(); }
        });
    }

}
