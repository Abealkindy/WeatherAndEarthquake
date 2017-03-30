package com.abraham24.weatherandearthquake.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abraham24.weatherandearthquake.KumpulanGson;
import com.abraham24.weatherandearthquake.KumpulanGson2;
import com.abraham24.weatherandearthquake.R;
import com.abraham24.weatherandearthquake.Weather2;

import java.util.List;

/**
 * Created by KOCHOR on 3/27/2017.
 */

class AdapterListProvinsi extends RecyclerView.Adapter <AdapterListProvinsi.ViewHolder> {
    public List<KumpulanGson2.KumpulanProvinsi>kumpulanProvinsis;
    Context context;






    public AdapterListProvinsi(FragmentActivity activity, List<KumpulanGson2.KumpulanProvinsi> kumpulanprovinsi) {
        kumpulanProvinsis = kumpulanprovinsi;
        context = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistprovinsi,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.teksprovinsi.setText(kumpulanProvinsis.get(position).namaprovinsi);

        holder.cardprovinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(v.getContext(),Weather2.class);
                String idprovinsis = kumpulanProvinsis.get(position).idprovinsi;
                b.putExtra("id",idprovinsis);

                v.getContext().startActivity(b);
            }
        });

    }



    @Override
    public int getItemCount() {
        return kumpulanProvinsis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teksprovinsi;
        CardView cardprovinsi;

        public ViewHolder(View itemView) {
            super(itemView);
            teksprovinsi = (TextView)itemView.findViewById(R.id.textnamaprovinsi);
            cardprovinsi = (CardView)itemView.findViewById(R.id.carditemprovinsi);


        }
    }
}
