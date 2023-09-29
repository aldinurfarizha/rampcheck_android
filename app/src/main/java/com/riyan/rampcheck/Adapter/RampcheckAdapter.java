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
import com.riyan.rampcheck.Model.ModelRampcheck;
import com.riyan.rampcheck.R;

import java.util.ArrayList;

public class RampcheckAdapter extends RecyclerView.Adapter<RampcheckAdapter.DataViewHolder> {

    private ArrayList<ModelRampcheck> dataList;
    Context context;
    public RampcheckAdapter(ArrayList<ModelRampcheck> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_data_rampcheck, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Context context=holder.item.getContext();
        String status="";
        if(dataList.get(position).getStatus().equals("1")){
            status="Laik Jalan";
        }
        if(dataList.get(position).getStatus().equals("2")){
            status="Peringatan / Perbaiki";
        }
        if(dataList.get(position).getStatus().equals("3")){
            status="Dilarang Operasional";
        }
        if(dataList.get(position).getStatus().equals("4")){
            status="Tilang & Dilarang Operasional";
        }
        holder.id_rampcheck.setText(dataList.get(position).getId_rampcheck());
        holder.tanggal_pemeriksaan.setText(dataList.get(position).getTanggal());
        holder.bus.setText(dataList.get(position).getBus());
        holder.penguji.setText(dataList.get(position).getPenguji());
        holder.trayek.setText(dataList.get(position).getTrayek());
        holder.status.setText(status);
        holder.nomor.setText(dataList.get(position).getNomor());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), Detail.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                final String id_rampcheck = dataList.get(position).getId_rampcheck();
                intent.putExtra("id_rampcheck", id_rampcheck);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        private TextView id_rampcheck, tanggal_pemeriksaan, bus, sopir, penguji, trayek, status, nomor;
        private CardView item;
        public DataViewHolder(View itemView) {
            super(itemView);
            id_rampcheck=(TextView) itemView.findViewById(R.id.id_rampcheck);
            tanggal_pemeriksaan=(TextView) itemView.findViewById(R.id.tanggal_pemeriksaan);
            bus = (TextView) itemView.findViewById(R.id.bus);
            sopir = (TextView) itemView.findViewById(R.id.sopir);
            item = (CardView) itemView.findViewById(R.id.item);
            penguji = (TextView) itemView.findViewById(R.id.penguji);
            trayek = (TextView) itemView.findViewById(R.id.trayek);
            status = (TextView) itemView.findViewById(R.id.status);
            nomor = (TextView) itemView.findViewById(R.id.nomor);
        }
    }
}