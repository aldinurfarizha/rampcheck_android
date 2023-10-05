package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.riyan.rampcheck.R;

public class StageOne extends AppCompatActivity {
    Spinner kartuUjiStuk, kpReguler, kpCadangan, simPengemudi;
    Button btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_one);
        kartuUjiStuk = findViewById(R.id.kartu_uji_stuk);
        kpReguler = findViewById(R.id.kp_reguler);
        kpCadangan = findViewById(R.id.kp_cadangan);
        simPengemudi = findViewById(R.id.sim_pengemudi);
        btnLanjut = findViewById(R.id.buttonLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    Intent intent = new Intent(StageOne.this, StageTwo.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id_bus", intent.getExtras().getString("id_bus"));
                    intent.putExtra("id_sopir",intent.getExtras().getString("id_sopir"));
                    intent.putExtra("kartu_uji_stuk",kartuUjiStuk.getSelectedItem().toString());
                    intent.putExtra("kp_reguler",kpReguler.getSelectedItem().toString());
                    intent.putExtra("kp_cadangan",kpCadangan.getSelectedItem().toString());
                    intent.putExtra("sim_pengemudi",simPengemudi.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });
    }
    public boolean check(){
        if(kartuUjiStuk.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)kartuUjiStuk.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(kpReguler.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)kpReguler.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(kpCadangan.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)kpCadangan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(simPengemudi.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)simPengemudi.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        return true;
    }
}