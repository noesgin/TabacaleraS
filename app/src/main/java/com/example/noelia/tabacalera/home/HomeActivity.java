package com.example.noelia.tabacalera.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.noelia.tabacalera.R;
import com.example.noelia.tabacalera.events.EventsActivity;
import com.example.noelia.tabacalera.gallery.GalleryActivity;
import com.example.noelia.tabacalera.news.NewsActivity;
import com.example.noelia.tabacalera.workshops.WorkshopsActivity;


public class HomeActivity extends AppCompatActivity {


    static TextView mDotsText[];
    private int mDotsCount;
    private LinearLayout mDotsLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        vistas();

        //here we create the gallery and set our adapter created before
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new HomeImageAdapter(this));


        mDotsLayout = (LinearLayout) findViewById(R.id.image_count);
        //here we count the number of images we have to know how many dots we need
        mDotsCount = gallery.getAdapter().getCount();

        //here we create the dots
        //as you can see the dots are nothing but "."  of large size
        mDotsText = new TextView[mDotsCount];

        //here we set the dots
        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(this);
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(40);
            mDotsText[i].setTypeface(null, Typeface.BOLD);
            mDotsText[i].setTextColor(android.graphics.Color.GRAY);
            mDotsLayout.addView(mDotsText[i]);

        }

        //when we scroll the images we have to set the dot that corresponds to the image to White and the others
        //will be Gray
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int pos, long l) {

                for (int i = 0; i < mDotsCount; i++) {
                    HomeActivity.mDotsText[i]
                            .setTextColor(Color.GRAY);

                }

                HomeActivity.mDotsText[pos]
                        .setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });


    }

    private void vistas() {
        String classNames[] = {"Talleres", "Eventos", "Galeria", "Noticias"};

        int[] listImages = {R.drawable.pencil, R.drawable.calendar, R.drawable.image,R.drawable.news};

        //ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        ListAdapter adapter = new HomeListAdapter(this, classNames, listImages);

        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textPosition = String.valueOf(parent.getItemAtPosition(position));

                if (position == 0) {
                    String[] names = getResources().getStringArray(R.array.taller_titulos); // TODO: Recogerlos de un servicio, no de recursos
                    String[] schedules = getResources().getStringArray(R.array.taller_horarios);
                    int[] images = {
                            R.drawable.workshop_radio,
                            R.drawable.workshop_malabares,
                            R.drawable.workshop_telas,
                            R.drawable.workshop_danza,
                            R.drawable.workshop_circo};

                    Intent intent = new Intent(HomeActivity.this, WorkshopsActivity.class);
                    intent.putExtra(WorkshopsActivity.NAMES, names);
                    intent.putExtra(WorkshopsActivity.SCHEDULES, schedules);
                    intent.putExtra(WorkshopsActivity.IMAGES, images);
                    intent.putExtra(WorkshopsActivity.TYPE, WorkshopsActivity.class.getSimpleName());
                    startActivity(intent);
                } else if (position == 1) {
                    String[] names = getResources().getStringArray(R.array.eventos_titulos); // TODO: Recogerlos de un servicio, no de recursos
                    String[] schedules = getResources().getStringArray(R.array.eventos_fechas);
                    int[] images = {
                            R.drawable.workshop_radio,
                            R.drawable.workshop_malabares,
                            R.drawable.workshop_telas,
                            R.drawable.workshop_danza,
                            R.drawable.workshop_circo};

                    Intent intent = new Intent(HomeActivity.this, WorkshopsActivity.class);
                    intent.putExtra(WorkshopsActivity.NAMES, names);
                    intent.putExtra(WorkshopsActivity.SCHEDULES, schedules);
                    intent.putExtra(WorkshopsActivity.IMAGES, images);
                    intent.putExtra(WorkshopsActivity.TYPE, EventsActivity.class.getSimpleName());
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(HomeActivity.this, GalleryActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(HomeActivity.this, NewsActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}
