package com.riyan.rampcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.riyan.rampcheck.Adapter.RampcheckAdapter;
import com.riyan.rampcheck.Model.ModelRampcheck;
import com.riyan.rampcheck.Util.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Data extends AppCompatActivity {
    private ArrayList<ModelRampcheck> modelRampcheckArrayList;
    RampcheckAdapter adapter;
    RecyclerView gridView;
    SweetAlertDialog dialog;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        modelRampcheckArrayList = new ArrayList<>();
        gridView = (RecyclerView)findViewById(R.id.list_data);
        back = (ImageView)findViewById(R.id.back);
        dialog = new SweetAlertDialog(Data.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#2E7DF3"));
        dialog.setTitleText("Sedang Mengambil Data");
        dialog.setCancelable(false);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(URLs.ALL_DATA_CHECK)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        modelRampcheckArrayList.clear();
                        dialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            JSONArray array = jsonObject.getJSONArray("data");
                            int nomor=1;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                ModelRampcheck modelRampcheck = new ModelRampcheck(ob.getString("id_rampcheck"),ob.getString("tanggal_pemeriksaan"),ob.getString("nomor_plat_kendaraan")+" "+ob.getString("nama_po"),ob.getString("nama_sopir"),ob.getString("nama_penguji")
                                        ,ob.getString("trayek"),ob.getString("status"), ""+nomor);
                                modelRampcheckArrayList.add(modelRampcheck);
                                nomor++;
                            }
                            adapter = new RampcheckAdapter(modelRampcheckArrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Data.this, LinearLayoutManager.VERTICAL, false);
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
                                new SweetAlertDialog(Data.this, SweetAlertDialog.ERROR_TYPE)
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
    public void koneksi_ulang(){
        new SweetAlertDialog(Data.this, SweetAlertDialog.ERROR_TYPE)
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