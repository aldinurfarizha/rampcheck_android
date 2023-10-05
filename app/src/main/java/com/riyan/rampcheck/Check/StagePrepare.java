package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.riyan.rampcheck.Model.Credential;
import com.riyan.rampcheck.R;
import com.riyan.rampcheck.ScanningQR;
import com.riyan.rampcheck.Util.SharedPrefManager;
import com.riyan.rampcheck.Util.URLs;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class StagePrepare extends AppCompatActivity {
String id_bus;
SweetAlertDialog dialog;
TextView nomor_plat,nama_po,jenis_angkutan,trayek;
Button lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_prepare);
        nomor_plat=(TextView)findViewById(R.id.nomor_plat);
        nama_po=(TextView)findViewById(R.id.nama_po);
        jenis_angkutan=(TextView)findViewById(R.id.jenis_angkutan);
        trayek=(TextView)findViewById(R.id.trayek);
        lanjut=(Button)findViewById(R.id.button_lanjut);
        Intent intent = getIntent();
        id_bus= intent.getExtras().getString("id_bus");
        dialog = new SweetAlertDialog(StagePrepare.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#2E7DF3"));
        dialog.setTitleText("Mohon Tunggu...");
        dialog.setCancelable(false);
        getData();
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StagePrepare.this, SelectDriver.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_bus", id_bus);
                startActivity(intent);
            }
        });
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(URLs.SCAN_BARCODE)
                .addBodyParameter("id_bus", id_bus)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            JSONObject arr =response.getJSONObject("data");
                            nomor_plat.setText(arr.getString("nomor_plat_kendaraan"));
                            nama_po.setText(arr.getString("nama_po"));
                            jenis_angkutan.setText(arr.getString("jenis_angkutan"));
                            trayek.setText(arr.getString("trayek"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.dismiss();
                        if (error.getErrorCode() != 0) {
                            try {
                                JSONObject response = new JSONObject(error.getErrorBody());
                                SweetAlertDialog errorSwal=new SweetAlertDialog(StagePrepare.this, SweetAlertDialog.ERROR_TYPE);
                                errorSwal.setTitleText(response.getString("messages"));
                                errorSwal.setConfirmText("Kembali");
                                errorSwal.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        startActivity(new Intent(getApplicationContext(), ScanningQR.class));
                                    }
                                });
                                errorSwal.setCancelable(false);
                                errorSwal.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            SweetAlertDialog errorSwal=new SweetAlertDialog(StagePrepare.this, SweetAlertDialog.ERROR_TYPE);
                            errorSwal.setTitleText("Tidak dapat terhubung ke server");
                            errorSwal.setConfirmText("Kembali");
                            errorSwal.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    finish();
                                }
                            });
                            errorSwal.setCancelable(false);
                            errorSwal.show();
                        }
                    }
                });

    }
}