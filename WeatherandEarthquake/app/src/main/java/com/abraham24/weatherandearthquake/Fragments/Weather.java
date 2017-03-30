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
import com.abraham24.weatherandearthquake.KumpulanGson2;
import com.abraham24.weatherandearthquake.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Weather extends Fragment {
RecyclerView recweather;
    RequestQueue requestweather;
    StringRequest stringweather;
    KumpulanGson2 kumpulangson2;

    public Weather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        recweather = (RecyclerView)view.findViewById(R.id.recyclerweather);
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        recweather.setLayoutManager(linear);

        String urlweather = "http://ibacor.com/api/bmkg?view=provinsi&k=a279f8bcfd07ea74d1992024d765749e";

        requestweather = Volley.newRequestQueue(getActivity());

        stringweather = new StringRequest(Request.Method.GET, urlweather, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson2 = gsonBuilder.create();
                kumpulangson2 = gson2.fromJson(response, KumpulanGson2.class);

                AdapterListProvinsi provinsi = new AdapterListProvinsi(getActivity(),kumpulangson2.kumpulanprovinsi);
                recweather.setAdapter(provinsi);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           Toast.makeText(getActivity(), "Maaf Internet Lambat", Toast.LENGTH_SHORT).show();
            }
        });
        requestweather.add(stringweather);


        // Inflate the layout for this fragment
        return view;
    }

}
