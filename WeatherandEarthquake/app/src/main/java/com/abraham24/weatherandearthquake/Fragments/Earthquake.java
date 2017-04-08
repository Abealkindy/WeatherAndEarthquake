package com.abraham24.weatherandearthquake.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abraham24.weatherandearthquake.KumpulanGson;
import com.abraham24.weatherandearthquake.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Earthquake extends Fragment {
RecyclerView recyclerView;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    //penganalan Kelas KumpulanGson
    KumpulanGson kumpulangsonclass;

    public Earthquake() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_earthquake,container,false);
        //Inisialisasi/Casting widget RecyclerView
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerearthquake);
        //Mengatur tampilan List/RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //setLayoutManager dari pengaturan tampilan dia atas
        recyclerView.setLayoutManager(linearLayoutManager);
        //menyimpan url ke dalam String
        String infogempayangdirasakan = "http://ibacor.com/api/bmkg?view=gempa-dirasakan&k=a279f8bcfd07ea74d1992024d765749e";
        //setting RequestQueue
        requestQueue = Volley.newRequestQueue(getActivity());

 
        //instansiasi StringRequest dengan Method GET + url dari String sebelumnya
        stringRequest = new StringRequest(Request.Method.GET, infogempayangdirasakan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //menampilkan response JSON di Log Debug
                Log.d("response", response);
                //Instansiasi builder gson 
                GsonBuilder builder = new GsonBuilder();
                //untuk membuat GSON sebagai JSON parser
                Gson gson = builder.create();
                //pengambilan parsing GSON dari kelas yang terdapat parsing
                kumpulangsonclass = gson.fromJson(response,KumpulanGson.class);
           //    KumpulanGson datas = gson.fromJson(response,KumpulanGson.class);







                        //membuat method baru di kelas baru utk menampung data dari recyclerview
                        AdapterListGempa adapter = new AdapterListGempa(getActivity(), kumpulangsonclass.kumpulandata);
                        //memasukkan data ke recyclerview dari kelas CustomListAdapter.java
                        recyclerView.setAdapter(adapter);



            }
            //pengambilan aksi jika Volley Error mengambil data
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "Maaf Internet Lambat", Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
requestQueue.add(stringRequest);

        return v;

    }


}
