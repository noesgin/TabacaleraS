package com.example.noelia.tabacalera.workshops;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.noelia.tabacalera.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorkshopsActivity extends AppCompatActivity {

    private static final String url = "http://tabacalera.net23.net/workShopsQuery.php";

    public static final String NAMES = "names";
    public static final String SCHEDULES = "schedules";
    public static final String IMAGES = "images";
    public static final String TYPE = "type";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
 /*   private List<WorkshopsDataProvider> list = new ArrayList<>();*/
    private ArrayList<WorkshopsDataProvider> list = new ArrayList<>();
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

//    private String[] names, schedules;
//    private int[] images = {
//            R.drawable.workshop_radio,
//            R.drawable.workshop_malabares,
//            R.drawable.workshop_telas,
//            R.drawable.workshop_danza,
//            R.drawable.workshop_circo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

/*       String type = getIntent().getStringExtra(TYPE);*/
        setContentView(R.layout.activity_workshops);

/*     recyclerView = (RecyclerView) findViewById(R.id.recycler_view);*/

//        names = getResources().getStringArray(R.array.taller_titulos); // TODO: Recogerlos de un servicio, no de recursos
//        schedules = getResources().getStringArray(R.array.taller_horarios);

/*        String[] names = (String[]) getIntent().getSerializableExtra(NAMES);
        String[] schedules = (String[]) getIntent().getSerializableExtra(SCHEDULES);
        int[] images = (int[]) getIntent().getSerializableExtra(IMAGES);

        int i = 0;
        for (String name : names) {
            if (i < images.length) {
                WorkshopsDataProvider dataProvider = new WorkshopsDataProvider(images[i], schedules[i], name, "Lore Ipsum tralara");
                list.add(dataProvider);
                i++;
            }
        }
        adapter = new WorkshopsRecyclerAdapter((ArrayList<WorkshopsDataProvider>) list, this, type);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true); // TODO: Cambiar a false cuando recojamos de un servicio web
        recyclerView.setClickable(true);*/

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        showInfo();
    }

    private void showInfo() {

        String type = getIntent().getStringExtra(TYPE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando datos...");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new WorkshopsRecyclerAdapter(list, this, type);
        recyclerView.setAdapter(adapter);
        recyclerView.setClickable(true);

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i<response.length(); i++){

                    try {
                        JSONObject object = response.getJSONObject(i);
                        String iconUrl = object.getString("icon"); //nombre en la tabla
                        String name = object.getString("name");
                        String schedule = object.getString("schedule");
                        String description = object.getString("description");
                        String imageUrl = object.getString("pic");

                        list.add(new WorkshopsDataProvider(
                                iconUrl, name, schedule, description, imageUrl
                        ));

                    } catch (JSONException e) {
                        Toast.makeText(WorkshopsActivity.this,""+e, Toast.LENGTH_LONG).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

    }
}