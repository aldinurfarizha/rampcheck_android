package com.riyan.rampcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.riyan.rampcheck.Model.Credential;
import com.riyan.rampcheck.Util.SharedPrefManager;
import com.riyan.rampcheck.Util.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {
Button login;
EditText username, password;
SweetAlertDialog dialog;
String message;
public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button) findViewById(R.id.buttonLogin);
        username=(EditText) findViewById(R.id.editTextUsername);
        password=(EditText)findViewById(R.id.editTextPassword);
        checkPermisson();
        sessionCheck();
        dialog = new SweetAlertDialog(Login.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#2E7DF3"));
        dialog.setTitleText("Loading...");
        dialog.setCancelable(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });
    }
    public boolean checkPermisson(){

        int cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    public void sessionCheck(){
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            return;
        }
    }
    public void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final String usernames = username.getText().toString();
        final String passwords = password.getText().toString();
        if (TextUtils.isEmpty(usernames)) {
            username.setError("Kolom Ini Wajib di isi");
            username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(passwords)) {
            password.setError("Kolom Ini Wajib di isi");
            password.requestFocus();
            return;
        }
        dialog.show();
        AndroidNetworking.post(URLs.LOGIN)
                .addBodyParameter("username", usernames)
                .addBodyParameter("password", passwords)
                .setTag("Login Prosses")
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        try {
                            message = response.getString("messages");
                            JSONObject arr = response.getJSONObject("data");
                            Credential credential = new Credential(
                                    arr.getString("id_user"),
                                    arr.getString("username"),
                                    arr.getString("nama")
                            );
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(credential);
                            Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
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
                                Toasty.error(getApplicationContext(), response.getString("messages"), Toast.LENGTH_SHORT, true).show();
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
        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                .setContentText("Koneksi Bermasalah !")
                .setConfirmText("Ulangi")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        try {
                            AndroidNetworking.cancelAll();
                            login();
                            sweetAlertDialog.cancel();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .show();
    }
}