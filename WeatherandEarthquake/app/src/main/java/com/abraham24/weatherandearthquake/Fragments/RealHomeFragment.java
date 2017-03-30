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

import com.abraham24.weatherandearthquake.MenuUtama;
import com.abraham24.weatherandearthquake.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealHomeFragment extends Fragment {
StringRequest str;
    RequestQueue que;
RecyclerView recycler;
    ArrayList<HashMap<String, String>> list_data;
    public RealHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_real_home, container, false);



        recycler = (RecyclerView) view.findViewById(R.id.recyclerhome);
        LinearLayoutManager linearmanager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearmanager);



        String urlinfogempaterkini = "http://ibacor.com/api/bmkg?view=gempa-terkini&k=a279f8bcfd07ea74d1992024d765749e";
        que = Volley.newRequestQueue(getActivity());

        list_data = new ArrayList<>();

        str = new StringRequest(Request.Method.GET, urlinfogempaterkini, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("data");
                  //  JSONArray array2 = object.getJSONArray("aaa");

                    for (int a = 0; a < array.length(); a++) {
                        JSONObject json = array.getJSONObject(a);
                        //parameter HashMap utk menangkap variabel JSON
                        HashMap<String, String> map = new HashMap<String, String>();
            //pengambilan variabel JSON
                        map.put("waktu", json.getString("waktu"));
                        map.put("lintang_bujur", json.getString("lintang_bujur"));
                        map.put("magnitudo", json.getString("magnitudo"));
                        map.put("kedalaman", json.getString("kedalaman"));
                        map.put("wilayah", json.getString("wilayah"));
                        map.put("img", json.getString("img"));
            //memasukkan data ke ArrayList memakai variabel list_data
                        list_data.add(map);
                        //membuat method baru di kelas baru utk menampung data dari recyclerview
                        CustomListAdapter adapter = new CustomListAdapter(getActivity(), list_data);
                        //memasukkan data ke recyclerview dari kelas CustomListAdapter.java
                        recycler.setAdapter(adapter);
                    }

                } catch (JSONException e) {

                    Toast.makeText(getActivity(), "Gagal parsing JSON", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "Maaf Internet Lambat", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                // KelasLain kelas = new KelasLain();
                //utk melempar data
//                param.put("id_kategori", kelas.getIdkategori());

                return param;
            }



            // Inflate the layout for this fragment

        };
        que.add(str);




        return view;


    }

}
