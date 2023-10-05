package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.riyan.rampcheck.Adapter.RampcheckAdapter;
import com.riyan.rampcheck.Adapter.SopirAdapter;
import com.riyan.rampcheck.Data;
import com.riyan.rampcheck.Model.ModelRampcheck;
import com.riyan.rampcheck.Model.ModelSopir;
import com.riyan.rampcheck.R;
import com.riyan.rampcheck.Util.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SelectDriver extends AppCompatActivity {
    private ArrayList<ModelSopir> modelSopirArrayList;
    SopirAdapter adapter;
    RecyclerView gridView;
    SweetAlertDialog dialog;
    String id_bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_driver);
        Intent intent = getIntent();
        id_bus= intent.getExtras().getString("id_bus");
        modelSopirArrayList = new ArrayList<>();
        gridView = (RecyclerView) findViewById(R.id.list_data);
        dialog = new SweetAlertDialog(SelectDriver.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#2E7DF3"));
        dialog.setTitleText("Sedang Mengambil Data");
        dialog.setCancelable(false);
        getData();
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(URLs.ALL_SOPIR)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        modelSopirArrayList.clear();
                        dialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            JSONArray array = jsonObject.getJSONArray("data");
                            int nomor=1;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                ModelSopir modelSopir = new ModelSopir(ob.getString("id_sopir"),ob.getString("nama_sopir"),ob.getString("tgl_lahir"),ob.getString("telepon"),ob.getString("alamat"),hitungUmur(ob.getString("tgl_lahir"))
                                        ,nomor+"", id_bus);
                                modelSopirArrayList.add(modelSopir);
                                nomor++;
                            }
                            adapter = new SopirAdapter(modelSopirArrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SelectDriver.this, LinearLayoutManager.VERTICAL, false);
                            gridView.setLayoutManager(layoutManager);
                            gridView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.hide();
                        if (error.getErrorCode() != 0) {
                            try {
                                JSONObject response = new JSONObject(error.getErrorBody());
                                new SweetAlertDialog(SelectDriver.this, SweetAlertDialog.ERROR_TYPE)
                                        .setContentText(response.getString("messages"))
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.hide();
                                            }
                                        })
                                        .show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            koneksi_ulang();
                        }
                    }
                });
    }
    public static String hitungUmur(String tanggalLahir) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse(tanggalLahir);
            Date hariIni = new Date();

            int umur = hariIni.getYear() - dob.getYear();
            if (hariIni.getMonth() < dob.getMonth() || (hariIni.getMonth() == dob.getMonth() && hariIni.getDay() < dob.getDay())) {
                umur--;
            }

            return String.valueOf(umur);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
    public void koneksi_ulang(){
        new SweetAlertDialog(SelectDriver.this, SweetAlertDialog.ERROR_TYPE)
                .setContentText("Koneksi Internet Bermasalah !")
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