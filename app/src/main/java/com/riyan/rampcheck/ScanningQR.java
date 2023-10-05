package com.riyan.rampcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.riyan.rampcheck.Check.StagePrepare;

import es.dmoral.toasty.Toasty;

public class ScanningQR extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_qr);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toasty.success(getApplicationContext(), "Berhasil mendapatkan Barcode", Toast.LENGTH_SHORT, true).show();
                        Intent intent = new Intent(ScanningQR.this, StagePrepare.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.putExtra("id_bus", result.getText());
                        intent.putExtra("id_bus", "1234W18");
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
        mCodeScanner.startPreview();
    }
}