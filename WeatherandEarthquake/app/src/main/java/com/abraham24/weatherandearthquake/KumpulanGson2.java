package com.abraham24.weatherandearthquake;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KumpulanGson2 {

    @SerializedName("data")
    public List<KumpulanProvinsi> kumpulanprovinsi;

    public class KumpulanProvinsi {
        @SerializedName("nama")
        public String namaprovinsi;
        @SerializedName("id")
        public String idprovinsi;

    }

}

