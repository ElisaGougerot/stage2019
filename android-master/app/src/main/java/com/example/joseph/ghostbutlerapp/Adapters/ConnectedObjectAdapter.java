package com.example.joseph.ghostbutlerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.joseph.ghostbutlerapp.R;
import com.example.joseph.ghostbutlerapp.dataClasses.ConnectedObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectedObjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int OBJECT = 0;

    private Context context;
    private ArrayList<ConnectedObject> objects;
    private int position;

    public ConnectedObjectAdapter(ArrayList<ConnectedObject> objects, Context context, RecyclerView recyclerView){
        this.context = context;
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mainHolder = null;
        if (viewType == OBJECT){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.cellule_objet, parent, false);
            mainHolder = new ObjectCelluleHolder(view);

        }

        return mainHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ConnectedObject currentObject = objects.get(position);

        if(holder instanceof ObjectCelluleHolder){
            ObjectCelluleHolder holder1 = (ObjectCelluleHolder) holder;
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RequestQueue requestQueue  = Volley.newRequestQueue(context);
                    String url = "https://192.168.0.119:16564/api/v1/directory/test";
                    StringRequest
                            stringRequest
                            = new StringRequest(
                            Request.Method.PUT,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

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
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", currentObject.getName());
                            params.put("type", "light");

                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);

                    Toast.makeText(context, "envoyé", Toast.LENGTH_LONG).show();
                }
            });

            holder1.display(currentObject);
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    @Override
    public int getItemViewType(int position) {
        return OBJECT;
    }


    public class ObjectCelluleHolder extends RecyclerView.ViewHolder{

        private final TextView objectName;
        private final ImageView objectImage;

        public ObjectCelluleHolder(View itemView) {
            super(itemView);

            objectName = itemView.findViewById(R.id.object_name);
            objectImage = itemView.findViewById(R.id.object_image);

        }

        public void display(ConnectedObject connectedObject){
            objectName.setText(connectedObject.getName());

            switch (connectedObject.getType()){
                case ConnectedObject.LIGHT:

                    break;

                case ConnectedObject.MUSIC:
                    break;

                case ConnectedObject.GAMEPAD:
                    break;

                case ConnectedObject.TEXTTOSPOEECH:
                    break;

                case ConnectedObject.OUTLET:
                    break;
            }
        }
    }

}
