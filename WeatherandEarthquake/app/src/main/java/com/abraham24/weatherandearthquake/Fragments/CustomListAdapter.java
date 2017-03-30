package com.abraham24.weatherandearthquake.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abraham24.weatherandearthquake.DetailActivity;
import com.abraham24.weatherandearthquake.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KOCHOR on 3/23/2017.
 */

class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String, String>> list_data;
    public CustomListAdapter(FragmentActivity activity, ArrayList<HashMap<String, String>> list_data) {
        this.context = activity;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
//String urlgambar = "http://ibacor.com/api/bmkg?view=gempa-terkini&k=a279f8bcfd07ea74d1992024d765749e";
        Glide
                .with(context)
                .load( list_data.get(position).get("img"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageItem);

        holder.tekslintang.setText("Lintang Bujur : " + list_data.get(position).get("lintang_bujur"));
        holder.tekskedalaman.setText("Kedalaman Gempa : " + list_data.get(position).get("kedalaman"));
        holder.teksmagnitudo.setText("Magnitudo : " + list_data.get(position).get("magnitudo"));
        holder.tekswilayah.setText("Wilayah : " + list_data.get(position).get("wilayah"));
        holder.texkwaktu.setText("Waktu : " + list_data.get(position).get("waktu"));

        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(v.getContext(), DetailActivity.class);
                a.putExtra("img",list_data.get(position).get("img"));
                a.putExtra("lintang_bujur",list_data.get(position).get("lintang_bujur"));
                a.putExtra("kedalaman",list_data.get(position).get("kedalaman"));
                a.putExtra("magnitudo",list_data.get(position).get("magnitudo"));
                a.putExtra("wilayah",list_data.get(position).get("wilayah"));
                a.putExtra("waktu",list_data.get(position).get("waktu"));

                v.getContext().startActivity(a);

            }
        });



    }



    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tekswilayah,tekskedalaman,teksmagnitudo,tekslintang,texkwaktu;
        ImageView imageItem;
        CardView cardItem;

        public ViewHolder(View itemView) {
            super(itemView);

            tekswilayah = (TextView)itemView.findViewById(R.id.textwilayah);
            tekskedalaman = (TextView)itemView.findViewById(R.id.textkedalamangempa);
            teksmagnitudo = (TextView)itemView.findViewById(R.id.textmagnitudo);
            tekslintang = (TextView)itemView.findViewById(R.id.textlintang);
            texkwaktu = (TextView)itemView.findViewById(R.id.textwaktu);
            //image
            imageItem = (ImageView) itemView.findViewById(R.id.imageitem);
            //card
            cardItem = (CardView) itemView.findViewById(R.id.carditem);
        }
    }
}
