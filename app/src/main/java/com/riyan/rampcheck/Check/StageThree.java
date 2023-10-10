package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.riyan.rampcheck.R;

public class StageThree extends AppCompatActivity {
    Button btn_lanjut;
    Spinner lampuPosisiDepan, lampuPosisiBelakang, kacaSpion, klakson, lantaiDanTangga,
            jlnTempatDudukPenumpang, banCadangan, segitigaPengaman, dongkrak, pembukaRoda, lampuSenter;
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
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

}