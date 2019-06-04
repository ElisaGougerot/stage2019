package com.example.joseph.ghostbutlerapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.joseph.ghostbutlerapp.Adapters.ConnectedObjectAdapter;
import com.example.joseph.ghostbutlerapp.R;
import com.example.joseph.ghostbutlerapp.dataClasses.ConnectedObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ObjectsByTypeActivity extends Activity {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ConnectedObjectAdapter adapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects_type);

        recyclerView = findViewById(R.id.type_recycler);

        final ArrayList<ConnectedObject> objects = new ArrayList<>();

        String url = "https://192.168.0.119:16564/api/v1/directory/types";
        StringRequest
                stringRequest
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray json  = new JSONArray(response);
                            Log.i("JSON LENTH", String.valueOf(json.length()));
                            for (int i =0 ; i < json.length(); i++){
                                ConnectedObject object = new ConnectedObject(ConnectedObject.LIGHT, json.getString(i));
                                objects.add(object);
                            }


                            // use a linear layout manager
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);

                            // specify an adapter (see also next example)
                            ConnectedObjectAdapter adapter = new ConnectedObjectAdapter(objects, getApplicationContext(),recyclerView);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERREUR VOLLEY", error.toString());
                    }

                }){
        };
        //requestQueue.add(stringRequest);

    }

}
