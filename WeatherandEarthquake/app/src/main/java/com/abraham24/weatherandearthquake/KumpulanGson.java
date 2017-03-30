package com.abraham24.weatherandearthquake;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KOCHOR on 3/25/2017.
 */

public class KumpulanGson {
    @SerializedName("data")
    public List<KumpulanData>kumpulandata;




    public class KumpulanData {
        @SerializedName("waktu")
        public String waktudirasakan;

        @SerializedName("lintang_bujur")
        public String lintangbujurdirasakan;

        @SerializedName("magnitudo")
        public String magnitudodirasakan;

        @SerializedName("kedalaman")
        public String kedalamangempadirasakan;

        @SerializedName("wilayah")
        public String wilayahgempadirasakan;

        @SerializedName("img")
        public String gambargempadirasakan;

        @SerializedName("dirasakan")
        public List<String>dirasakan = null;

        public KumpulanData withDirasakan(List<String> dirasakan) {
            this.dirasakan = dirasakan;
            return this;
        }


    }


}
