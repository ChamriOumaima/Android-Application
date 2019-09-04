package com.example.wildtechnologie.gpfemergency;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private ViewPager nSlideViewPager;
    private LinearLayout nDotLayout;

    private TextView[] nDots;

    private SliderAdapter sliderAdapter;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Cliquez sur un slide", Toast.LENGTH_LONG).show();


        nSlideViewPager = (ViewPager) findViewById(R.id.slideviewpager);
        nDotLayout = (LinearLayout) findViewById(R.id.dots);

        sliderAdapter = new SliderAdapter(this);

        nSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        nSlideViewPager.addOnPageChangeListener(viewListener);

    }

    public void addDotsIndicator(int position) {
        nDots = new TextView[3];
        nDotLayout.removeAllViews();

        for (int i = 0; i < nDots.length; i++) {
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226"));
            nDots[i].setTextSize(35);
            nDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            nDotLayout.addView(nDots[i]);
        }

        if (nDots.length > 0) {
            nDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
