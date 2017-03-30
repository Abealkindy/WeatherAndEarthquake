package com.abraham24.weatherandearthquake;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
String lintang,wilayah,waktu,magnitudo,kedalaman;
    int gambar;
    TextView tMagnitudo, tKedalaman, tWaktu, tWilayah, tLintang;
    ImageView gambardetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gambardetail = (ImageView)findViewById(R.id.imageView3);
        tKedalaman = (TextView)findViewById(R.id.textKedalaman);
        tMagnitudo = (TextView)findViewById(R.id.textMagnitudo);
        tWaktu = (TextView)findViewById(R.id.textWaktu);
        tWilayah = (TextView)findViewById(R.id.textWilayah);
        tLintang = (TextView)findViewById(R.id.textLintang);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent tangkap = getIntent();

      lintang =  tangkap.getStringExtra("lintang_bujur");
       wilayah = tangkap.getStringExtra("wilayah");
       waktu = tangkap.getStringExtra("waktu");
       magnitudo = tangkap.getStringExtra("magnitudo");
       kedalaman = tangkap.getStringExtra("kedalaman");
       gambar =  tangkap.getIntExtra("img",0);

        gambardetail.setImageResource(gambar);

        tLintang.setText("Lintang Bujur : " + lintang);
        tKedalaman.setText("Kedalaman Gempa : " + kedalaman);
        tWaktu.setText("Waktu : " + waktu);
        tMagnitudo.setText("Magnitudo : " + magnitudo);
        tWilayah.setText("Wilayah : " + wilayah);

    }

}
