package com.example.ravonda.cita495;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class home_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        final Button button = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(home_screen.this, MapsActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(home_screen.this, GalleryScreen.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(home_screen.this, HelpScreen.class));
            }
        });
    }
}
