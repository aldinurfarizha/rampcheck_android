package com.riyan.rampcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.riyan.rampcheck.Model.Credential;
import com.riyan.rampcheck.Util.SharedPrefManager;
import com.riyan.rampcheck.Util.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Dashboard extends AppCompatActivity {
    CardView scanqr, inventaris;
    TextView total_inventaris,total_perolehan,total_penghapusan, nama;
    ImageView logout;
    Credential credential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        scanqr=(CardView) findViewById(R.id.btn_qrcode);
        nama=(TextView)findViewById(R.id.nama);
        inventaris=(CardView)findViewById(R.id.btn_data);
        total_inventaris=(TextView)findViewById(R.id.total_inventaris);
        total_penghapusan=(TextView)findViewById(R.id.total_penghapusan_aset);
        total_perolehan=(TextView)findViewById(R.id.total_inventaris_baru);
        logout=(ImageView)findViewById(R.id.logout);
        credential = SharedPrefManager.getInstance(this).get();
        nama.setText(credential.getNama());
        getData();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(Dashboard.this, SweetAlertDialog.ERROR_TYPE)
                        .setContentText("Anda Ingin Logout ?")
                        .setConfirmText("Ya")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                finish();
                                SharedPrefManager.getInstance(getApplicationContext()).logout();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            }
                        })
                        .show();
            }
        });
        scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanningQR.class));
                //startActivity(new Intent(getApplicationContext(), DetailInventaris.class));
            }
        });
        inventaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Data.class));
            }
        });
    }
    public void getData(){

        AndroidNetworking.post(URLs.DASHBOARD)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject arr =response.getJSONObject("data");
                            total_inventaris.setText(arr.getString("res"));
                            total_perolehan.setText(arr.getString("satu"));
                            total_penghapusan.setText(arr.getString("dua"));
                            total_penghapusan.setText(arr.getString("tiga"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {

                    }
                });

    }
}