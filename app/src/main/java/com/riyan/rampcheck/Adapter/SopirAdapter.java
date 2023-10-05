package com.riyan.rampcheck.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.riyan.rampcheck.Check.Detail;
import com.riyan.rampcheck.Check.StageOne;
import com.riyan.rampcheck.Model.ModelRampcheck;
import com.riyan.rampcheck.Model.ModelSopir;
import com.riyan.rampcheck.R;

import java.util.ArrayList;

public class SopirAdapter extends RecyclerView.Adapter<SopirAdapter.DataViewHolder> {

    private ArrayList<ModelSopir> dataList;
    Context context;
    public SopirAdapter(ArrayList<ModelSopir> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_data_sopir, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Context context=holder.item.getContext();
        holder.nama.setText(dataList.get(position).getNama_sopir());
        holder.nomor_telepon.setText(dataList.get(position).getTelepon());
        holder.alamat.setText(dataList.get(position).getAlamat());
        holder.tanggal_lahir.setText(dataList.get(position).getTgl_lahir());
        holder.umur.setText(dataList.get(position).getUmur());
        holder.nomor.setText(dataList.get(position).getNomor());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), StageOne.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                final String id_bus = dataList.get(position).getId_bus();
                final String id_sopir = dataList.get(position).getId_sopir();
                intent.putExtra("id_bus", id_bus);
                intent.putExtra("id_sopir",id_sopir);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        private TextView nama, nomor_telepon, alamat, tanggal_lahir, umur, nomor;
        private CardView item;
        public DataViewHolder(View itemView) {
            super(itemView);
            nama=(TextView) itemView.findViewById(R.id.nama_sopir);
            nomor_telepon=(TextView) itemView.findViewById(R.id.nomor_telepon);
            alamat = (TextView) itemView.findViewById(R.id.alamat);
            tanggal_lahir = (TextView) itemView.findViewById(R.id.tanggal_lahir);
            item = (CardView) itemView.findViewById(R.id.item);
            umur = (TextView) itemView.findViewById(R.id.umur);
            nomor = (TextView) itemView.findViewById(R.id.nomor);
        }
    }
}