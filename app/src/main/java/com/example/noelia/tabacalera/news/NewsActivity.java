package com.example.noelia.tabacalera.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.noelia.tabacalera.R;

public class NewsActivity extends AppCompatActivity {
    ViewPager viewPager;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
    /*    adapter = new NewsAdapter(this);
        viewPager.setAdapter(adapter);*/

        viewPager.setAdapter(new NewsAdapter(this));
    }
}
