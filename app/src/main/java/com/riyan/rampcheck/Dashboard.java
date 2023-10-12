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
import com.riyan.rampcheck.Check.StagePrepare;
import com.riyan.rampcheck.Model.Credential;
import com.riyan.rampcheck.Util.SharedPrefManager;
import com.riyan.rampcheck.Util.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Dashboard extends AppCompatActivity {
    CardView scanqr, data_scan;
    TextView total_rampcheck,total_laik_jalan,total_peringatan,total_dilarang_operasional,total_dilarang_operasional_dan_tilang, nama;
    ImageView logout;
    Credential credential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        scanqr=(CardView) findViewById(R.id.btn_qrcode);
        nama=(TextView)findViewById(R.id.nama);
        data_scan=(CardView)findViewById(R.id.btn_data);
        total_rampcheck=(TextView)findViewById(R.id.total_rampcheck);
        total_laik_jalan=(TextView)findViewById(R.id.total_laik_jalan);
        total_peringatan=(TextView)findViewById(R.id.total_peringatan);
        total_dilarang_operasional=(TextView)findViewById(R.id.total_dilarang_operasional);
        total_dilarang_operasional_dan_tilang=(TextView)findViewById(R.id.total_tilang_dan_dilarang_operasional);
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
                //Intent intent = new Intent(Dashboard.this, StagePrepare.class);
                //intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtra("id_bus", "1234W18");
                //startActivity(intent);
                startActivity(new Intent(getApplicationContext(), ScanningQR.class));
            }
        });
        data_scan.setOnClickListener(new View.OnClickListener() {
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
                            total_rampcheck.setText(arr.getString("res"));
                            total_laik_jalan.setText(arr.getString("satu"));
                            total_peringatan.setText(arr.getString("dua"));
                            total_dilarang_operasional.setText(arr.getString("tiga"));
                            total_dilarang_operasional_dan_tilang.setText(arr.getString("empat"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        koneksi_ulang();
                    }
                });

    }
    public void koneksi_ulang(){
        new SweetAlertDialog(Dashboard.this, SweetAlertDialog.ERROR_TYPE)
                .setContentText("Koneksi Bermasalah !")
                .setConfirmText("Ulangi")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        AndroidNetworking.cancelAll();
                        getData();
                        sweetAlertDialog.cancel();
                    }
                })
                .show();
    }
}