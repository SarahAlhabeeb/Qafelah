package com.example.sara.qafelah;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {
    TextView startTxt;
    Button menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        startTxt= (TextView) findViewById(R.id.start);
        menuBtn= (Button) findViewById(R.id.btnmenu);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ithra-light-webfont.ttf");
        startTxt.setTypeface(type);

        menuBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Int = new Intent(getApplicationContext(),Menu.class);
                startActivity(Int);
                finish();

            }

        });

    }
}
