package com.example.metro.volleyexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button click;
    private TextView location_name,location_id,lat,lng;
    private RequestQueue requestQueue;
    private StringRequest jsonObjectRequest;

    private RecyclerView my_recycler_view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_recycler_view = (RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        my_recycler_view.setLayoutManager(layoutManager);
        my_recycler_view.setHasFixedSize(true);

        click = (Button) findViewById(R.id.click);
        location_name = (TextView) findViewById(R.id.location_name);
        location_id = (TextView) findViewById(R.id.location_id);
        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        requestQueue = Volley.newRequestQueue(this);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonObjectRequest = new StringRequest(Request.Method.POST, Constants.URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Parkinglist> arrayList = new ArrayList<Parkinglist>();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println("Location_name "+response);
                            location_name.setText(String.valueOf(jsonObject.get("Status")));
                            if(String.valueOf(jsonObject.get("Status")).equals("true"))
                            {
                               JSONArray array = jsonObject.getJSONArray("LocationMaster");
                                for(int i=0;i<array.length();i++)
                                {
                                    JSONObject jsonObject1 = array.getJSONObject(i);
                                    Parkinglist parkinglist = new Parkinglist();
                                    parkinglist.setLocationName(jsonObject1.getString("LocationName"));
                                    parkinglist.setLocationID(jsonObject1.getString("LocationID"));
                                    parkinglist.setLatitude(jsonObject1.getString("Latitude"));
                                    parkinglist.setLongitude(jsonObject1.getString("Longitude"));
                                    arrayList.add(parkinglist);
                                    location_name.setText(jsonObject1.getString("LocationName"));
                                    location_id.setText(jsonObject1.getString("LocationID"));
                                    lat.setText(jsonObject1.getString("Latitude"));
                                    lng.setText(jsonObject1.getString("Longitude"));
                                }

                            }

                            adapter = new RecycleAdapter(arrayList);
                            my_recycler_view.setAdapter(adapter);
                        }catch (Exception e ){e.printStackTrace();}


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("Latitude", "26.85");
                        map.put("Longitude", "75.80");
                        map.put("Radious", "6000");
                        return map;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        return map;
                    }
                };

                requestQueue.add(jsonObjectRequest);
            }
        });




//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("Latitude", "26.85");
//                        map.put("Longitude", "75.80");
//                        map.put("Radious", "6000");
//                        return map;
//                    }
//
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String> map = new HashMap<String, String>();
//
//
//                        return map;
//                    }



    }
}
