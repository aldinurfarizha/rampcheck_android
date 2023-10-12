package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.riyan.rampcheck.Dashboard;
import com.riyan.rampcheck.Login;
import com.riyan.rampcheck.Model.Credential;
import com.riyan.rampcheck.R;
import com.riyan.rampcheck.Util.SharedPrefManager;
import com.riyan.rampcheck.Util.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

public class StageThree extends AppCompatActivity {
    Button btn_lanjut;
    Spinner lampuPosisiDepan, lampuPosisiBelakang, kacaSpion, klakson, lantaiDanTangga,
            jlnTempatDudukPenumpang, banCadangan, segitigaPengaman, dongkrak, pembukaRoda, lampuSenter;
    SweetAlertDialog dialog;
    Credential credential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_three);
        lampuPosisiDepan = findViewById(R.id.lampu_posisi_depan);
        lampuPosisiBelakang = findViewById(R.id.lampu_posisi_belakang);
        kacaSpion = findViewById(R.id.kaca_spion);
        klakson = findViewById(R.id.klakson);
        lantaiDanTangga = findViewById(R.id.lantai_dan_tangga);
        jlnTempatDudukPenumpang = findViewById(R.id.jln_tempat_duduk_penumpang);
        banCadangan = findViewById(R.id.ban_cadangan);
        segitigaPengaman = findViewById(R.id.segitiga_pengaman);
        dongkrak = findViewById(R.id.dongkrak);
        pembukaRoda = findViewById(R.id.pembuka_roda);
        lampuSenter = findViewById(R.id.lampu_senter);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        dialog = new SweetAlertDialog(StageThree.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#2E7DF3"));
        dialog.setTitleText("Sedang Mengirim...");
        dialog.setCancelable(false);
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    sendData();
                }
            }
        });
    }
    public void sendData(){
        Intent intent = getIntent();
        final String id_bus = intent.getExtras().getString("id_bus");
        final String id_sopir = intent.getExtras().getString("id_sopir");
        final String kartu_uji_stuk = intent.getExtras().getString("kartu_uji_stuk");
        final String kp_reguler = intent.getExtras().getString("kp_reguler");
        final String kp_cadangan = intent.getExtras().getString("kp_cadangan");
        final String sim_pengemudi = intent.getExtras().getString("sim_pengemudi");
        final String lampu_utama_dekat = intent.getExtras().getString("lampu_utama_dekat");
        final String lampu_utama_jauh = intent.getExtras().getString("lampu_utama_jauh");
        final String lampu_sein_depan = intent.getExtras().getString("lampu_sein_depan");
        final String lampu_sein_belakang = intent.getExtras().getString("lampu_sein_belakang");
        final String lampu_rem = intent.getExtras().getString("lampu_rem");
        final String lampu_mundur = intent.getExtras().getString("lampu_mundur");
        final String rem_utama = intent.getExtras().getString("rem_utama");
        final String rem_parkir = intent.getExtras().getString("rem_parkir");
        final String kondisi_kaca_depan = intent.getExtras().getString("kondisi_kaca_depan");
        final String pintu_utama = intent.getExtras().getString("pintu_utama");
        final String kondisi_ban_depan = intent.getExtras().getString("kondisi_ban_depan");
        final String kondisi_ban_belakang = intent.getExtras().getString("kondisi_ban_belakang");
        final String sabuk_keselamatan_pengemudi = intent.getExtras().getString("sabuk_keselamatan_pengemudi");
        final String pengukur_kecepatan = intent.getExtras().getString("pengukur_kecepatan");
        final String penghapus_kaca = intent.getExtras().getString("penghapus_kaca");
        final String pintu_darurat = intent.getExtras().getString("pintu_darurat");
        final String jendela_darurat = intent.getExtras().getString("jendela_darurat");
        final String alat_pemukul_pemecah_kaca = intent.getExtras().getString("alat_pemukul_pemecah_kaca");
        final String lampu_posisi_depan = lampuPosisiDepan.getSelectedItem().toString();
        final String lampu_posisi_belakang = lampuPosisiBelakang.getSelectedItem().toString();
        final String kaca_spion = kacaSpion.getSelectedItem().toString();
        final String klaksons = klakson.getSelectedItem().toString();
        final String lantai_dan_tangga = lantaiDanTangga.getSelectedItem().toString();
        final String jln_tempat_duduk_penumpang = jlnTempatDudukPenumpang.getSelectedItem().toString();
        final String ban_cadangan = banCadangan.getSelectedItem().toString();
        final String segitiga_pengaman = segitigaPengaman.getSelectedItem().toString();
        final String dongkraks = dongkrak.getSelectedItem().toString();
        final String pembuka_roda = pembukaRoda.getSelectedItem().toString();
        final String lampu_senter = lampuSenter.getSelectedItem().toString();
        credential = SharedPrefManager.getInstance(this).get();
        final String id_user= credential.getId_user();
        dialog.show();
        AndroidNetworking.post(URLs.INSERT_RAMPCHECK)
                .addBodyParameter("id_user", id_user)
                .addBodyParameter("id_bus", id_bus)
                .addBodyParameter("id_sopir", id_sopir)
                .addBodyParameter("kartu_uji_stuk", kartu_uji_stuk)
                .addBodyParameter("kp_reguler", kp_reguler)
                .addBodyParameter("kp_cadangan", kp_cadangan)
                .addBodyParameter("sim_pengemudi", sim_pengemudi)
                .addBodyParameter("lampu_utama_dekat", lampu_utama_dekat)
                .addBodyParameter("lampu_utama_jauh", lampu_utama_jauh)
                .addBodyParameter("lampu_sein_depan", lampu_sein_depan)
                .addBodyParameter("lampu_sein_belakang", lampu_sein_belakang)
                .addBodyParameter("lampu_rem", lampu_rem)
                .addBodyParameter("lampu_mundur", lampu_mundur)
                .addBodyParameter("rem_utama", rem_utama)
                .addBodyParameter("rem_parkir", rem_parkir)
                .addBodyParameter("kondisi_kaca_depan", kondisi_kaca_depan)
                .addBodyParameter("pintu_utama", pintu_utama)
                .addBodyParameter("kondisi_ban_depan", kondisi_ban_depan)
                .addBodyParameter("kondisi_ban_belakang", kondisi_ban_belakang)
                .addBodyParameter("sabuk_keselamatan_pengemudi", sabuk_keselamatan_pengemudi)
                .addBodyParameter("pengukur_kecepatan", pengukur_kecepatan)
                .addBodyParameter("penghapus_kaca", penghapus_kaca)
                .addBodyParameter("pintu_darurat", pintu_darurat)
                .addBodyParameter("jendela_darurat", jendela_darurat)
                .addBodyParameter("alat_pemukul_pemecah_kaca", alat_pemukul_pemecah_kaca)
                .addBodyParameter("lampu_posisi_depan", lampu_posisi_depan)
                .addBodyParameter("lampu_posisi_belakang", lampu_posisi_belakang)
                .addBodyParameter("kaca_spion", kaca_spion)
                .addBodyParameter("klakson", klaksons)
                .addBodyParameter("lantai_dan_tangga", lantai_dan_tangga)
                .addBodyParameter("jln_tempat_duduk_penumpang", jln_tempat_duduk_penumpang)
                .addBodyParameter("ban_cadangan", ban_cadangan)
                .addBodyParameter("segitiga_pengaman", segitiga_pengaman)
                .addBodyParameter("dongkrak", dongkraks)
                .addBodyParameter("pembuka_roda", pembuka_roda)
                .addBodyParameter("lampu_senter", lampu_senter)
                .setTag("Login Prosses")
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        try {
                            JSONObject arr = response.getJSONObject("data");
                            berhasil(arr.getString("id_rampcheck"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        try {
                            JSONObject response = new JSONObject(error.getErrorBody());
                            Toasty.error(getApplicationContext(), response.getString("messages"), Toast.LENGTH_SHORT, true).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("error insert", error.toString());

                    }
                });
    }
    public boolean check() {
        if (lampuPosisiDepan.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) lampuPosisiDepan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (lampuPosisiBelakang.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) lampuPosisiBelakang.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (kacaSpion.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) kacaSpion.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (klakson.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) klakson.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (lantaiDanTangga.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) lantaiDanTangga.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (jlnTempatDudukPenumpang.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) jlnTempatDudukPenumpang.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (banCadangan.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) banCadangan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (segitigaPengaman.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) segitigaPengaman.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (dongkrak.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) dongkrak.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (pembukaRoda.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) pembukaRoda.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        if (lampuSenter.getSelectedItem().toString().equals("-- Pilih --")) {
            ((TextView) lampuSenter.getSelectedView()).setError("Wajib di pilih");
            return false;
        }

        return true;
    }
    public void koneksi_ulang(){
        new SweetAlertDialog(StageThree.this, SweetAlertDialog.ERROR_TYPE)
                .setContentText("Koneksi Bermasalah !")
                .setConfirmText("Ulangi")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        AndroidNetworking.cancelAll();
                        sendData();
                        sweetAlertDialog.cancel();
                    }
                })
                .show();
    }
    public void berhasil(String id_rampcheck){
        new SweetAlertDialog(StageThree.this, SweetAlertDialog.SUCCESS_TYPE)
                .setContentText("Rampcheck Berhasil")
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intents = new Intent(StageThree.this, Detail.class);
                        intents.setFlags(intents.FLAG_ACTIVITY_NEW_TASK);
                        intents.putExtra("id_rampcheck",  id_rampcheck);
                        startActivity(intents);
                    }
                })
                .show();
    }
}