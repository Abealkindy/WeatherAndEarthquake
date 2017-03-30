package com.abraham24.weatherandearthquake;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.List;

/**
 * Created by KOCHOR on 3/28/2017.
 */
  class AdapterListCuaca extends RecyclerView.Adapter<AdapterListCuaca.ViewHolder> {

    Context context;
    public List<KumpulanGson3.KumpulanPrakiraan>prakiraanList;

    public AdapterListCuaca(Weather2 listener, List<KumpulanGson3.KumpulanPrakiraan> prakiraan) {

        context = listener;
        prakiraanList = prakiraan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistcuaca,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.textKota.setText(prakiraanList.get(position).kota);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent a = new Intent(v.getContext(),DetailCuaca.class);
//                a.putExtra("namakota",prakiraanList.get(position).kota);
//                v.getContext().startActivity(a);
            }
        });


    }



    @Override
    public int getItemCount() {
        return prakiraanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textKota;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.carditemweather);
            textKota = (TextView)itemView.findViewById(R.id.textnamakota);
        }
    }
}
