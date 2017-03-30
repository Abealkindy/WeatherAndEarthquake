package com.abraham24.weatherandearthquake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.abraham24.weatherandearthquake.Fragments.Weather;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Weather2 extends AppCompatActivity {
RecyclerView recyclerprovinsi;
    RequestQueue reqprov;
    StringRequest strprov;
    KumpulanGson3 kumpulanGson3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather2);

        recyclerprovinsi = (RecyclerView)findViewById(R.id.recyclerprovinsi);
        LinearLayoutManager linearmanager = new LinearLayoutManager(Weather2.this);
        recyclerprovinsi.setLayoutManager(linearmanager);

        Intent a = getIntent();
        String tangkapid = a.getStringExtra("id");


        String urlprovinsi = "http://ibacor.com/api/bmkg?view=cuaca&id="+tangkapid+"&k=a279f8bcfd07ea74d1992024d765749e";

        reqprov = Volley.newRequestQueue(Weather2.this);

        strprov = new StringRequest(Request.Method.GET, urlprovinsi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                GsonBuilder gsonBuilder22 = new GsonBuilder();
                Gson gson22 = gsonBuilder22.create();
                kumpulanGson3 = gson22.fromJson(response, KumpulanGson3.class);

                AdapterListCuaca adapterListCuaca = new AdapterListCuaca(Weather2.this, kumpulanGson3.prakiraan);
                recyclerprovinsi.setAdapter(adapterListCuaca);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Weather2.this, "Maaf Internet Lambat", Toast.LENGTH_SHORT).show();
            }
        });

        reqprov.add(strprov);
    }
}
