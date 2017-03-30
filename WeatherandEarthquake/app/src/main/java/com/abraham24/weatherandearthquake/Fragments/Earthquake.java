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
//    ArrayList<HashMap<String,String>>list_gempa;
    KumpulanGson kumpulangsonclass;

    public Earthquake() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_earthquake,container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerearthquake);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        String infogempayangdirasakan = "http://ibacor.com/api/bmkg?view=gempa-dirasakan&k=a279f8bcfd07ea74d1992024d765749e";

        requestQueue = Volley.newRequestQueue(getActivity());

  //      list_gempa =new ArrayList<> ();

        stringRequest = new StringRequest(Request.Method.GET, infogempayangdirasakan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                kumpulangsonclass = gson.fromJson(response,KumpulanGson.class);
           //    KumpulanGson datas = gson.fromJson(response,KumpulanGson.class);


//                try {
//                    JSONObject object = new JSONObject(response);
//                    JSONArray array = object.getJSONArray("data");
//
//
//                    for (int a = 0; a < array.length(); a++) {
//                        JSONObject json = array.getJSONObject(a);
//                        JSONArray array2 = json.getJSONArray("dirasakan");
//
//                        for (int e = 0 ; e <array2.length();e++){
//                         String tangkap = array2.getString(e);
//                        }
//
//                        //parameter HashMap utk menangkap variabel JSON
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        //pengambilan variabel JSON
//                        map.put("waktu", json.getString("waktu"));
//                        map.put("lintang_bujur", json.getString("lintang_bujur"));
//                        map.put("magnitudo", json.getString("magnitudo"));
//                        map.put("kedalaman", json.getString("kedalaman"));
//                        map.put("wilayah", json.getString("wilayah"));
//                        map.put("img", json.getString("img"));
//                        //memasukkan data ke ArrayList memakai variabel list_data
//                        list_gempa.add(map);




                        //membuat method baru di kelas baru utk menampung data dari recyclerview
                        AdapterListGempa adapter = new AdapterListGempa(getActivity(), kumpulangsonclass.kumpulandata);
                        //memasukkan data ke recyclerview dari kelas CustomListAdapter.java
                        recyclerView.setAdapter(adapter);


//                } catch (JSONException e) {
//
//                    Toast.makeText(getActivity(), "Gagal parsing JSON", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
            }
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
