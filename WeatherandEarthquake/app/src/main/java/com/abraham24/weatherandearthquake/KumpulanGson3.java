package com.abraham24.weatherandearthquake;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KOCHOR on 3/28/2017.
 */

public class KumpulanGson3 {

    @SerializedName("prakiraan")
    public List<KumpulanPrakiraan> prakiraan;

    public class KumpulanPrakiraan {
        @SerializedName("kota")
        public String kota;
        @SerializedName("sekarang")
        public Sekarang sekarang;
        @SerializedName("besok")
        public Besok besok;

        private class Sekarang {
            @SerializedName("tanggal")
            public String tanggal;
            @SerializedName("suhu")
            public Suhu suhu;
            @SerializedName("cuaca")
            public Cuaca cuaca;
            @SerializedName("kelembaban")
            public Kelembaban kelembaban;

            private class Suhu {
                @SerializedName("min")
                public String minSuhu1;
                @SerializedName("max")
                public String maxSuhu1;


            }

            private class Cuaca {
                @SerializedName("siang")
                public String siang1;
                @SerializedName("malam")
                public String malam1;
                @SerializedName("dinihari")
                public String dinihari1;

            }

            private class Kelembaban {
                @SerializedName("min")
                public String minKelembaban1;
                @SerializedName("max")
                public String maxKelembaban1;
            }
        }

        private class Besok {
            @SerializedName("tanggal")
            public String tanggal2;
            @SerializedName("kota")
            public String kota2;
            @SerializedName("cuaca")
            public Cuaca2 cuaca2;
            @SerializedName("suhu")
            public Suhu2 suhu2;
            @SerializedName("kelembaban")
            public Kelembaban2 kelembaban2;

            private class Cuaca2 {
                @SerializedName("pagi")
                public String pagi;
                @SerializedName("siang")
                public String siang2;
                @SerializedName("malam")
                public String malam2;
                @SerializedName("dinihari")
                public String dinihari2;
            }

            private class Suhu2 {
                @SerializedName("min")
                public String minSuhu2;
                @SerializedName("max")
                public String maxSuhu2;

            }

            private class Kelembaban2 {
                @SerializedName("min")
                public String minKelembaban2;
                @SerializedName("max")
                public String maxKelembaban2;
            }
        }
    }


}
