package com.example.joseph.ghostbutlerapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.joseph.ghostbutlerapp.Adapters.ConnectedObjectAdapter;
import com.example.joseph.ghostbutlerapp.R;
import com.example.joseph.ghostbutlerapp.dataClasses.ConnectedObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlActivity extends Activity {

    RequestQueue requestQueue;

    RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control2);


        recyclerView = findViewById(R.id.recycler);

        requestQueue  = Volley.newRequestQueue(this);

        /**String url = "https://192.168.0.119:16564/api/v1/directory/test";
        StringRequest
                stringRequest
                = new StringRequest(
                Request.Method.PUT,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Lumieres : ", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERREUR VOLLEY", error.toString());
                    }

                }){
             @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("name", "Alif");
                    params.put("domain", "http://itsalif.info");

                    return params;
                }
        };
        requestQueue.add(stringRequest); **/


        final ArrayList<ConnectedObject> objects = new ArrayList<>();

        String url = "https://192.168.0.119:16564/api/v1/directory/list?type=light";
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
        requestQueue.add(stringRequest);




    }

    public void openLampe(){

        Intent intent = new Intent(getApplicationContext(), ObjectActivity.class);
        intent.putExtra("ObjectType", "lampe");
        startActivity(intent);

    }

    public void openPrise(){
        Intent intent = new Intent(getApplicationContext(), ObjectActivity.class);
        intent.putExtra("ObjectType", "prise");
        startActivity(intent);
    }

    public void openCafe(){
        Intent intent = new Intent(getApplicationContext(), ObjectActivity.class);
        intent.putExtra("ObjectType", "cafe");
        startActivity(intent);

    }

}
