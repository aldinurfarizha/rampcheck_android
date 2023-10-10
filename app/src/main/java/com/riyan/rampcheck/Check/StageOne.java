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
        Intent intent = getIntent();
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    Intent intents = new Intent(StageOne.this, StageTwo.class);
                    intents.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intents.putExtra("id_bus", intent.getExtras().getString("id_bus"));
                    intents.putExtra("id_sopir",intent.getExtras().getString("id_sopir"));
                    intents.putExtra("kartu_uji_stuk",kartuUjiStuk.getSelectedItem().toString());
                    intents.putExtra("kp_reguler",kpReguler.getSelectedItem().toString());
                    intents.putExtra("kp_cadangan",kpCadangan.getSelectedItem().toString());
                    intents.putExtra("sim_pengemudi",simPengemudi.getSelectedItem().toString());
                    startActivity(intents);
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