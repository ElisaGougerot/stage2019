package com.example.joseph.ghostbutlerapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joseph.ghostbutlerapp.R;

public class ObjectActivity extends Activity {

    private String objectType;

    private ImageView image;
    private TextView title;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        image = findViewById(R.id.object_image);
        title = findViewById(R.id.object_title);

        objectType = getIntent().getStringExtra("ObjectType");

        if(objectType.equals("lampe")){
            image.setImageResource(R.drawable.lampe);
            title.setText("Lampe");
        }else if( objectType.equals("prise")){
            image.setImageResource(R.drawable.prise);
            title.setText("Prise");
        }else{
            image.setImageResource(R.drawable.cafe);
            title.setText("Cafe");
        }

    }

}
