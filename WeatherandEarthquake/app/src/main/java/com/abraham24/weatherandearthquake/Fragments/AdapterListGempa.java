package com.abraham24.weatherandearthquake.Fragments;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abraham24.weatherandearthquake.KumpulanGson;
import com.abraham24.weatherandearthquake.R;
import com.bumptech.glide.Glide;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KOCHOR on 3/24/2017.
 */

class AdapterListGempa extends RecyclerView.Adapter<AdapterListGempa.ViewHolder> {

public List<KumpulanGson.KumpulanData> tangkaplistpertama;
    Context context1;







    public AdapterListGempa(FragmentActivity activity, List<KumpulanGson.KumpulanData> kumpulandata) {
        context1 = activity;
        tangkaplistpertama = kumpulandata;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistearthquake, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context1)
                .load(tangkaplistpertama.get(position).gambargempadirasakan)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageitem2);
     //   String[] dgsg
        for (int a = 0; a < tangkaplistpertama.get(position).dirasakan.size(); a++){
//            holder.textDirasakan.setText("Gempa Dirasakan : " + tangkaplistpertama.get(position).dirasakan.toArray());

            Log.d("dirasakan " + position + " index " + a, tangkaplistpertama.get(position).dirasakan.get(a));

        }
       String[] dataDirasakan = tangkaplistpertama.get(position).dirasakan.toArray(new String[0]);
        Log.d("String dirasakan" + position, Arrays.toString(dataDirasakan));
        ArrayAdapter<String> dirasakanListAdapter = new ArrayAdapter<String>(context1,android.R.layout.simple_expandable_list_item_1, dataDirasakan);
        dirasakanListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //  List diii = tangkaplistpertama.get(a).dirasakan;
        //   for (int d = 0 ; d <diii.size();d ++)
        //     Log.d("dirasakan", String.valueOf(diii.get(d)));
     //   dgsg[a] = String.valueOf(tangkaplistpertama.get(a).dirasakan);
        holder.listDirasakan.setAdapter(dirasakanListAdapter);
        holder.textKedalaman2.setText("Kedalaman Gempa: " + tangkaplistpertama.get(position).kedalamangempadirasakan);
        holder.textWilayah2.setText("Wilayah Gempa : " + tangkaplistpertama.get(position).wilayahgempadirasakan);
        holder.textWaktu2.setText("Waktu Gempa : " + tangkaplistpertama.get(position).waktudirasakan);
        holder.textLintang2.setText("Lintang Bujur : " + tangkaplistpertama.get(position).lintangbujurdirasakan);
        holder.textAmplitudo2.setText("Magnitudo : " + tangkaplistpertama.get(position).magnitudodirasakan);

        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }




    @Override
    public int getItemCount() {
        return tangkaplistpertama.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textWaktu2, textWilayah2, textAmplitudo2, textKedalaman2, textLintang2;
        Spinner listDirasakan;
        CardView cardView2;
        ImageView imageitem2;


        public ViewHolder(View itemView) {
            super(itemView);

            textWaktu2 = (TextView)itemView.findViewById(R.id.textWaktu2);
            textWilayah2 = (TextView)itemView.findViewById(R.id.textWilayah2);
            textLintang2 = (TextView)itemView.findViewById(R.id.textLintang2);
            textAmplitudo2 = (TextView)itemView.findViewById(R.id.textMagnitudo2);
            textKedalaman2 = (TextView)itemView.findViewById(R.id.textKedalaman2);
            listDirasakan = (Spinner) itemView.findViewById(R.id.lvdirasakan);
            imageitem2 = (ImageView)itemView.findViewById(R.id.imageitem2);
            cardView2 = (CardView)itemView.findViewById(R.id.carditemearthquake);

        }
    }
}
