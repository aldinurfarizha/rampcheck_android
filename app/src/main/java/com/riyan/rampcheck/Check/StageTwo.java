package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.riyan.rampcheck.R;

public class StageTwo extends AppCompatActivity {
    Spinner lampuUtamaDekat, lampuUtamaJauh, lampuSeinDepan, lampuSeinBelakang, lampuRem, lampuMundur, remUtama, remParkir, kondisiKacaDepan, pintuUtama, kondisiBanDepan, kondisiBanBelakang, sabukKeselamatanPengemudi, pengukurKecepatan, penghapusKaca, pintuDarurat, jendelaDarurat, alatPemukulPemecahKaca;
    Button btn_lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);
        lampuUtamaDekat = findViewById(R.id.lampu_utama_dekat);
        lampuUtamaJauh = findViewById(R.id.lampu_utama_jauh);
        lampuSeinDepan = findViewById(R.id.lampu_sein_depan);
        lampuSeinBelakang = findViewById(R.id.lampu_sein_belakang);
        lampuRem = findViewById(R.id.lampu_rem);
        lampuMundur = findViewById(R.id.lampu_mundur);
        remUtama = findViewById(R.id.rem_utama);
        remParkir = findViewById(R.id.rem_parkir);
        kondisiKacaDepan = findViewById(R.id.kondisi_kaca_depan);
        pintuUtama = findViewById(R.id.pintu_utama);
        kondisiBanDepan = findViewById(R.id.kondisi_ban_depan);
        kondisiBanBelakang = findViewById(R.id.kondisi_ban_belakang);
        sabukKeselamatanPengemudi = findViewById(R.id.sabuk_keselamatan_pengemudi);
        pengukurKecepatan = findViewById(R.id.pengukur_kecepatan);
        penghapusKaca = findViewById(R.id.penghapus_kaca);
        pintuDarurat = findViewById(R.id.pintu_darurat);
        jendelaDarurat = findViewById(R.id.jendela_darurat);
        alatPemukulPemecahKaca = findViewById(R.id.alat_pemukul_pemecah_kaca);
        btn_lanjut=(Button) findViewById(R.id.btn_lanjut);
        Intent intent = getIntent();
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    Intent intents = new Intent(StageTwo.this, StageThree.class);
                    intents.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intents.putExtra("id_bus", intent.getExtras().getString("id_bus"));
                    intents.putExtra("id_sopir",intent.getExtras().getString("id_sopir"));
                    intents.putExtra("kartu_uji_stuk",intent.getExtras().getString("kartu_uji_stuk"));
                    intents.putExtra("kp_reguler",intent.getExtras().getString("kp_reguler"));
                    intents.putExtra("kp_cadangan",intent.getExtras().getString("id_sopir"));
                    intents.putExtra("sim_pengemudi",intent.getExtras().getString("id_sopir"));
                    intents.putExtra("lampu_utama_dekat", lampuUtamaDekat.getSelectedItem().toString());
                    intents.putExtra("lampu_utama_jauh", lampuUtamaJauh.getSelectedItem().toString());
                    intents.putExtra("lampu_sein_depan", lampuSeinDepan.getSelectedItem().toString());
                    intents.putExtra("lampu_sein_belakang", lampuSeinBelakang.getSelectedItem().toString());
                    intents.putExtra("lampu_rem", lampuRem.getSelectedItem().toString());
                    intents.putExtra("lampu_mundur", lampuMundur.getSelectedItem().toString());
                    intents.putExtra("rem_utama", remUtama.getSelectedItem().toString());
                    intents.putExtra("rem_parkir", remParkir.getSelectedItem().toString());
                    intents.putExtra("kondisi_kaca_depan", kondisiKacaDepan.getSelectedItem().toString());
                    intents.putExtra("pintu_utama", pintuUtama.getSelectedItem().toString());
                    intents.putExtra("kondisi_ban_depan", kondisiBanDepan.getSelectedItem().toString());
                    intents.putExtra("kondisi_ban_belakang", kondisiBanBelakang.getSelectedItem().toString());
                    intents.putExtra("sabuk_keselamatan_pengemudi", sabukKeselamatanPengemudi.getSelectedItem().toString());
                    intents.putExtra("pengukur_kecepatan", pengukurKecepatan.getSelectedItem().toString());
                    intents.putExtra("penghapus_kaca", penghapusKaca.getSelectedItem().toString());
                    intents.putExtra("pintu_darurat", pintuDarurat.getSelectedItem().toString());
                    intents.putExtra("jendela_darurat", jendelaDarurat.getSelectedItem().toString());
                    intents.putExtra("alat_pemukul_pemecah_kaca", alatPemukulPemecahKaca.getSelectedItem().toString());
                    startActivity(intents);
                }
            }
        });
    }
    public boolean check(){
        if(lampuUtamaDekat.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)lampuUtamaDekat.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(lampuUtamaJauh.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)lampuUtamaJauh.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(lampuSeinDepan.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)lampuSeinDepan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(lampuSeinBelakang.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)lampuSeinBelakang.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(lampuRem.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)lampuRem.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(lampuMundur.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)lampuMundur.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(remUtama.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)remUtama.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(remParkir.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)remParkir.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(kondisiKacaDepan.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)kondisiKacaDepan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(pintuUtama.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)pintuUtama.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(kondisiBanDepan.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)kondisiBanDepan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(kondisiBanBelakang.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)kondisiBanBelakang.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(sabukKeselamatanPengemudi.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)sabukKeselamatanPengemudi.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(pengukurKecepatan.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)pengukurKecepatan.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(penghapusKaca.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)penghapusKaca.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(pintuDarurat.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)pintuDarurat.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(jendelaDarurat.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)jendelaDarurat.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        if(alatPemukulPemecahKaca.getSelectedItem().toString().equals("-- Pilih --")){
            ((TextView)alatPemukulPemecahKaca.getSelectedView()).setError("Wajib di pilih");
            return false;
        }
        return true;
    }

}