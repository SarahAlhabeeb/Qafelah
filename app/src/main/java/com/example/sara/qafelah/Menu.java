package com.example.sara.qafelah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button profileBtn ;
    Button aboutBtn ;
    Button menuB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profileBtn = (Button) findViewById(R.id.btnProfile);
        aboutBtn = (Button) findViewById(R.id.btnAbout);
        menuB=(Button) findViewById(R.id.menub);

       // profileBtn.setOnClickListener(new View.OnClickListener() {

            //public void onClick(View v) {
              //  Intent Int = new Intent(getApplicationContext(),Profile.class);
             //   startActivity(Int);
             //   finish();

         //   }

      //  });

        aboutBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Int = new Intent(getApplicationContext(),About.class);
                startActivity(Int);
                finish();

            }

        });

        menuB.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Int = new Intent(getApplicationContext(),MainPageActivity.class);
                startActivity(Int);
                finish();

            }

        });



    }


}

