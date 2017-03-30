package com.abraham24.weatherandearthquake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.abraham24.weatherandearthquake.Fragments.Earthquake;
import com.abraham24.weatherandearthquake.Fragments.RealHomeFragment;
import com.abraham24.weatherandearthquake.Fragments.Weather;

public class MenuUtama extends AppCompatActivity {

 //   private static final String TAG = MenuUtama.class.getSimpleName();
private Fragment fragment;
  private  FragmentManager fragmanager;
 private  BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        setTitle("");

        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
       // navigation.inflateMenu(R.menu.navigation);
        fragmanager = getSupportFragmentManager();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.navigation_home:
                fragment = new RealHomeFragment();
                        setTitle("Gempa Terkini");

                break;
                    case R.id.navigation_dashboard:

                        fragment = new Earthquake();
                        setTitle("Gempa Dirasakan");

                        break;
                    case R.id.navigation_notifications:
                        fragment = new Weather();
                        setTitle("Cuaca Terkini");

                        break;
                }
                final FragmentTransaction transaction = fragmanager.beginTransaction();
                transaction.replace(R.id.content,fragment).commit();



                return true;
            }

        });
    }

}
