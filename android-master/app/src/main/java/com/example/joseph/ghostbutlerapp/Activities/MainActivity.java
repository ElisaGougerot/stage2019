package com.example.joseph.ghostbutlerapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.joseph.ghostbutlerapp.R;

public class MainActivity extends Activity {

	private Button control;
	private Button tuto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		control = findViewById(R.id.control);
		tuto= findViewById(R.id.tuto);

		control.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				controlPressed();
			}
		});

		tuto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				tutoPressed();
			}
		});
	}

	private void controlPressed(){
		Intent intent = new Intent(getApplicationContext(), ControlActivity.class);
		startActivity(intent);
	}

	private void tutoPressed(){
		Intent intent = new Intent(getApplicationContext(), TutoActivity.class);
		startActivity(intent);
	}
}
