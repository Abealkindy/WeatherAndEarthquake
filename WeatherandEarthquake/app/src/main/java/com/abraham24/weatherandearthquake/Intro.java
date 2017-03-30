package com.abraham24.weatherandearthquake;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abraham24.weatherandearthquake.R;

public class Intro extends AppCompatActivity {
    ViewPager viewPager;
    Button btnSkip, btnNext;
    LinearLayout layoutDots;
    Pref pref;
    TextView [] dots;
    int [] layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen tampilan
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        pref =  new Pref(this);
        if (!pref.isFirstTimeLaunch()){
            launchHome();
        }
        viewPager = (ViewPager)findViewById(R.id.vPager);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnSkip = (Button)findViewById(R.id.btnSkip);
        layoutDots = (LinearLayout)findViewById(R.id.layoutDots);

        layouts = new int[]{
                R.layout.layout_slide_1,
                R.layout.layout_slide_2,
                R.layout.layout_slide_3,
                R.layout.layout_slide_4,
        };

       addBottomDots(0);
//       changeStatusBarColor();

        myPagerAdapter adapter = new myPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                if (position+1 == layouts.length){
                    btnNext.setText(getString(R.string.start));
                    btnSkip.setVisibility(View.GONE);
                }else{
                    btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHome();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else{
                    launchHome();
                }
            }
        });
    }
    private int getItem(int i) {
        return viewPager.getCurrentItem()+i;
    }

    //inner class myPagerAdapter
    private class myPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;
        public myPagerAdapter() {
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);

        }
        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

// sini di ralat lagi
    private void launchHome() {
        pref.setFirstLaunched(false);
        startActivity(new Intent(getApplicationContext(), MenuUtama.class));
        finish();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor() {
        Window window =  getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//       window.setStatusBarColor(Color.TRANSPARENT);
    }
    private void addBottomDots(int i) {
        dots = new TextView[layouts.length];
        int colorActive [] = getResources().getIntArray(R.array.array_dot_activ);
        int colorNonActive [] = getResources().getIntArray(R.array.array_dot_noactiv);
        layoutDots.removeAllViews();
        for (int a=0; a<dots.length; a++){
            dots[a] = new TextView(this);
            dots[a].setText(Html.fromHtml("&#8226"));
            dots[a].setTextSize(35);
            dots[a].setTextColor(colorNonActive[i]);
            layoutDots.addView(dots[a]);
        }
        if (dots.length > 0){
            dots[i].setTextColor(colorActive[i]);
        }
    }


    private class Pref {

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        Context _context;

        int PRIVATE_MODE = 0;
        private static final String PREF_NAME = "androidhive-welcome";

        private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

        public Pref(Context context) {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }

        public void setFirstLaunched(boolean isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
            editor.commit();
        }

        public boolean isFirstTimeLaunch() {

            return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
        }
    }
    }

